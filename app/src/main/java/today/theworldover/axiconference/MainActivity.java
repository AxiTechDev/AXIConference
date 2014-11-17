package today.theworldover.axiconference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static today.theworldover.axiconference.R.id.share_button;

/*
 * Steps to using the DB:
 * 1. [DONE] Instantiate the DB Adapter
 * 2. [DONE] Open the DB
 * 3. use get, insert, delete, .. to change data.
 * 4. [DONE]Close the DB
 */

public class MainActivity extends Activity {

    Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /*
        share = (Button) findViewById(share_button);
        share.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            }
        });
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void emailShare(View view) {
        Intent intent = new Intent(this, EmailScreen.class);
        startActivity(intent);
    }

    public void seeRecord(View view) {
        Intent intentRec = new Intent(this, SeeRecords.class);
        startActivity(intentRec);
    }
}
