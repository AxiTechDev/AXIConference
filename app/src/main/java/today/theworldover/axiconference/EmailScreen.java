package today.theworldover.axiconference;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
       // String email = edtTextEmail.getText().toString();

        openDB();
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
