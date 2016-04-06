package itp341.mai.johnathan.a8;

import android.app.Activity;
import android.content.Intent;
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

    // Constants
    public static final String EXTRA_ITEMPOSITION = "itp341.mai.johnathan.a6.itemposition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        // Widgets
        mEditTextTitle = (EditText) findViewById(R.id.editTextNoteTitle);
        mEditTextDescription = (EditText) findViewById(R.id.editTextNoteBody);
        mButtonSaveNote = (Button) findViewById(R.id.buttonSaveNote);
        mButtonDeleteNote = (Button) findViewById(R.id.buttonDeleteNote);

        // Retrieve Intent, if there is
        Intent i = getIntent();
        int itemPosition = i.getIntExtra(EXTRA_ITEMPOSITION, -1);
        if (itemPosition != -1) { // if -1, that means this page was accessed by the Add Note button
            Note note = NoteSingleton.get(getApplicationContext()).getNote(itemPosition);
            mEditTextTitle.setText(note.getTitle());
            mEditTextDescription.setText(note.getContent());
            NoteSingleton.get(getApplicationContext()).removeNote(itemPosition); // Delete here and re-add after edited. this pushes note to most recent position
        }


        mButtonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent();
                newIntent.putExtra(NoteListActivity.EXTRA_TEMPNOTE_TITLE, mEditTextTitle.getText().toString());
                newIntent.putExtra(NoteListActivity.EXTRA_TEMPNOTE_BODY, mEditTextDescription.getText().toString());
                setResult(Activity.RESULT_OK, newIntent);
                finish();

            }
        });

        mButtonDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nothing needs to happen when a new note is cancelled.
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }
}
