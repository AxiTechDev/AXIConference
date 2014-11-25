package today.theworldover.axiconference;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import static today.theworldover.axiconference.DBAdapter.COL_ROWID;

/**
 * Created by william on 11/17/14.
 * This class is incomplete and not being used.
 */
public class Schedule extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

       /*
        share = (Button) findViewById(share_button);
        share.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            }
        });
*/
    }



//    private void displayText(String message) {
//        TextView textView = (TextView) findViewById(R.id.textDisplay);
//        textView.setText(message);
//    }

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
                String grade = cursor.getString(DBAdapter.COL_GRADE);
                int rating = cursor.getInt(DBAdapter.COL_RATING);

                // Append data to the message:
                message += "id=" + id
                        +", name=" + name
                        +", email=" + email
                        +", grade=" + grade
                        +", rating=" + rating
                        +"\n";
            } while(cursor.moveToNext());
            //Toast.makeText(EmailScreen.this, message, Toast.LENGTH_LONG).show();
        }

        // Close the cursor to avoid a resource leak.
        cursor.close();

        //displayText(message);
    }
}
