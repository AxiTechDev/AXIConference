package today.theworldover.axiconference;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static today.theworldover.axiconference.DBAdapter.COL_ROWID;

/**
 * Created by william on 11/12/14.
 */
public class EmailScreen extends Activity{

    DBAdapter myDb;
    EditText edtTextName;
    EditText edtTextEmail;
    //SeeRecords recSet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_screen);

        edtTextName = (EditText) findViewById(R.id.name);
        edtTextEmail = (EditText) findViewById(R.id.email_address);

        //String name = edtTextName.getText().toString();
        //String email = edtTextEmail.getText().toString();
        Button submit = (Button) findViewById(R.id.submit_button);
        openDB();
        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                sendEmail();
                // after sending the email, clear the fields
                edtTextName.setText("");
                edtTextEmail.setText("");

                AddRecord();
                finish();
            }
        });



    }

    protected void sendEmail() {
        String[] recipient = {edtTextEmail.getText().toString() };
        File fileLocation = new File ("/mnt/sdcard/Pictures/puffin6.jpg");
        Uri U = Uri.fromFile(fileLocation);
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        email.setType("message/jpg");

        email.putExtra(Intent.EXTRA_EMAIL, recipient);
        email.putExtra(Intent.EXTRA_SUBJECT, "Its that thing you were looking for...");
        email.putExtra(Intent.EXTRA_TEXT, "Hey hey, I can't believe it worked!/n/n");
        email.putExtra(Intent.EXTRA_STREAM, U);

        Log.i("Finished sending email...", "");
        try {
            startActivity(Intent.createChooser(email, "Choose an email client from..."));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(EmailScreen.this, "No email client found.", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(EmailScreen.this, getString(R.string.thank_you), Toast.LENGTH_LONG).show();
    }

    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }

    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }

    private void closeDB() {
        myDb.close();
    }

    public void submit_it(View view) {
        AddRecord();

        String name = edtTextName.getEditableText().toString();
        String email = edtTextEmail.getEditableText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "that thing you wanted");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hey there, I can't believe it worked!" );

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EmailScreen.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(EmailScreen.this, getString(R.string.thank_you), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        //onStop();
        startActivity(intent);
    }

    public void AddRecord() {
        //displayText("Clicked add record!");
        //EditText edtTextName = (EditText) findViewById(R.id.name);
        //EditText edtTextEmail = (EditText) findViewById(R.id.email_address);
        String name = edtTextName.getEditableText().toString();
        String email = edtTextEmail.getEditableText().toString();
        int rate = 3;

        long newId = myDb.insertRow(name, email, rate);

        String thisEntry = "Record " + newId + " Name: " + name + " Email: " + email + " Arbitrary rating: " + rate;
        Log.v("Hey billyyy", "" + thisEntry);

        // Query for the record we just added.
        // Use the ID:
        Cursor cursor = myDb.getRow(newId);
        //displayRecordSet(cursor);
    }

    //this is a test
    /*private void displayText(String message) {
        TextView textView = (TextView) findViewById(R.id.textDisplay);
        textView.setText(message);
    }

    public void displayRecordSet(Cursor cursor) {
        String message = "";
        // populate the message from the cursor

        // Reset cursor to start, checking to see if there's data:
        if (cursor.moveToFirst()) {
            do {
                // Process the data:
                int id = cursor.getInt(COL_ROWID);
                String name = cursor.getString(DBAdapter.COL_NAME);
                String email = cursor.getString(DBAdapter.COL_EMAIL);
                int rating = cursor.getInt(DBAdapter.COL_RATING);

                // Append data to the message:
                message += "id=" + id
                        +", name=" + name
                        +", email=" + email
                        +", rating=" + rating
                        +"\n";
            } while(cursor.moveToNext());
            //Toast.makeText(EmailScreen.this, message, Toast.LENGTH_LONG).show();
            Log.v("test", message);
        }

        // Close the cursor to avoid a resource leak.
        cursor.close();

        displayText(message);
    }*/

}
