package c.sakshi.lab5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {
    int noteid = -1;

    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper = new DBHelper(sqLiteDatabase);

    public void saveMethod(View view) {
        EditText myTextField = (EditText) findViewById(R.id.myTextField3);
        String content = myTextField.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(MainActivity.usernameKey, "");
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (Main2Activity.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        }
        else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", noteid);

        if (noteid != -1) {
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            EditText myTextField = (EditText) findViewById(R.id.myTextField3);
            myTextField.setText(noteContent);
        }
    }
}
