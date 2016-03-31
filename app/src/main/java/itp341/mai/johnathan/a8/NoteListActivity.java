package itp341.mai.johnathan.a8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    // Widgets
    Button mButtonAdd;
    ListView mListViewNotes;

    ArrayList<Note> mNoteList;
    ArrayAdapter<Note> itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        // Widgets
        mButtonAdd = (Button) findViewById(R.id.buttonAdd);
        mListViewNotes = (ListView) findViewById(R.id.listViewNotes);
        mNoteList = NoteSingleton.get(getApplicationContext()).getNoteList();

        // ArrayAdapter Set Up
        itemsAdapter = new ArrayAdapter<Note>(this, R.layout.list_item, mNoteList);
        mListViewNotes.setAdapter(itemsAdapter);


    }
}
