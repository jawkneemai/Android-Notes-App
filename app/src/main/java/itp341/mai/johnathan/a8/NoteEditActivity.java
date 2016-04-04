package itp341.mai.johnathan.a8;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class NoteEditActivity extends AppCompatActivity {

    // Widgets
    EditText mEditTextTitle;
    EditText mEditTextDescription;
    Button mButtonSaveNote;
    Button mButtonDeleteNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        // Widgets
        mEditTextTitle = (EditText) findViewById(R.id.editTextNoteTitle);
        mEditTextDescription = (EditText) findViewById(R.id.editTextNoteBody);
        mButtonSaveNote = (Button) findViewById(R.id.buttonSaveNote);
        mButtonDeleteNote = (Button) findViewById(R.id.buttonDeleteNote);

        mButtonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating new Note Object
                Note temp = new Note();
                Calendar c = Calendar.getInstance();
                temp.setTitle(mEditTextTitle.getText().toString());
                temp.setContent(mEditTextDescription.getText().toString());
                temp.setDate(c);

                // Add new Note Object to list and end activity
                NoteSingleton.get(getApplicationContext()).addNote(temp);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        mButtonDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }
}
