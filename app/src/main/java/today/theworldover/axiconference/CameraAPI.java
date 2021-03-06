package today.theworldover.axiconference;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by william on 11/21/14.
 */

public class CameraAPI extends Activity {

    ImageView iv;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_api);

        iv = (ImageView) findViewById(R.id.imageView);

        Button btn = (Button) findViewById(R.id.takePhoto);
        btn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                try {
                    startActivityForResult(intent, 0);

                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);


                }
                catch (NullPointerException e) {
                    Toast.makeText(CameraAPI.this, "Uggghhh", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button acceptBtn = (Button) findViewById(R.id.acceptPhoto);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /** Create a file Uri for saving an image */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image */
    private static File getOutputMediaFile(int type) {
        //To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        //This location works best if you want the images to be shared and persist

        //Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()) {
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        }  else {
            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            try {
                Bitmap theImage = (Bitmap) data.getExtras().get("data");
                iv.setImageBitmap(theImage);
            } catch (NullPointerException e) {
                Intent intentContest = new Intent(this, CameraAPI.class);
                startActivity(intentContest);
            }
        }
    }
}
/*

public class CameraAPI extends Activity implements SurfaceHolder.Callback{

    public Camera camera;
    MediaRecorder mediaRecorder;



    public void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contest);

        SurfaceView surface = (SurfaceView)findViewById(R.id.preview);
        SurfaceHolder holder = surface.getHolder();
        holder.addCallback(this);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(takePictureIntent);
        //holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void tackPhoto(View view){
        takePicture();

    }




    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }



    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        if (mediaRecorder == null){
            try{
                camera = camera.open();
                camera.setPreviewDisplay(holder);
                camera.startPreview();

            }catch (IOException e){
                Log.d("CAMERA", e.getMessage());
            }

        }
    }



    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        camera.stopPreview();
        camera.release();
    }

    public void takePicture(){
        camera.takePicture(shutterCallback, rawCallback, jpegCallback);

    }


    Camera.ShutterCallback shutterCallback= new Camera.ShutterCallback()
    {
        public void onShutter(){

        }
    };

    Camera.PictureCallback rawCallback = new Camera.PictureCallback(){
        public void onPictureTaken(byte[] data, Camera camera){

        }
    };

    Camera.PictureCallback jpegCallback = new Camera.PictureCallback(){
        public void onPictureTaken(byte[] data, Camera camera){
            FileOutputStream outStream = null;
            try{
                outStream = new FileOutputStream("/sdcard/Image.jpg");
                outStream.write(data);
                outStream.close();
            } catch (FileNotFoundException e){
                Log.d("CAMERA", e.getMessage());
            } catch (IOException e){
                Log.d("CAMERA", e.getMessage());
            }
        }
    };
}
*/
