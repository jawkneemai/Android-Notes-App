package itp341.mai.johnathan.a8;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;

public class NoteListActivity extends AppCompatActivity {

    // Widgets
    Button mButtonAdd;
    ListView mListViewNotes;

    // Data
    ArrayList<Note> mNoteList;
    ArrayAdapter<Note> itemsAdapter;

    // Constants
    private final String TAG = NoteListActivity.class.getName();
    static final int requestCodeAddNote = 1;
    static final int requestCodeEditNote = 2;
    public static final String EXTRA_TEMPNOTE_TITLE = "itp341.mai.johnathan.a6.tempnote.title";
    public static final String EXTRA_TEMPNOTE_BODY = "itp341.mai.johnathan.a6.tempnote.body";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        // Widgets
        mButtonAdd = (Button) findViewById(R.id.buttonAdd);
        mListViewNotes = (ListView) findViewById(R.id.listViewNotes);

        // ArrayAdapter Set Up
        mNoteList = NoteSingleton.get(getApplicationContext()).getNoteList();
        itemsAdapter = new ArrayAdapter<Note>(this, R.layout.list_item, R.id.simpleListItemNote, mNoteList);
        mListViewNotes.setAdapter(itemsAdapter);
        registerForContextMenu(mListViewNotes);


        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NoteEditActivity.class);
                startActivityForResult(i, requestCodeAddNote);
            }
        });

        mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), NoteEditActivity.class);
                i.putExtra(NoteEditActivity.EXTRA_ITEMPOSITION, position);
                startActivityForResult(i, requestCodeEditNote);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == requestCodeAddNote || requestCode == requestCodeEditNote) { // If note was added succesfully, refresh list
                // Creating new Note Object
                Note temp = new Note();

                // Calendar
                Date c = Calendar.getInstance().getTime();
                Log.d(TAG, String.valueOf(c));

                // Inserting new Note into model
                temp.setTitle(data.getStringExtra(EXTRA_TEMPNOTE_TITLE));
                temp.setContent(data.getStringExtra(EXTRA_TEMPNOTE_BODY));
                temp.setDate(c);

                // Add new Note Object to list and end activity
                NoteSingleton.get(getApplicationContext()).addNote(temp);
                itemsAdapter.notifyDataSetChanged();
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            itemsAdapter.notifyDataSetChanged();
        } // else don't have to do anything.
    }

    // Context Menu Delete Methods
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.menuDeleteNote:
                NoteSingleton.get(getApplicationContext()).removeNote(info.position); // info.position gets index of item on listview
                itemsAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}

class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }
    private final String TAG = NoteListActivity.class.getName();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = NoteSingleton.get(getContext()).getNote(position);
        Formatter f = new Formatter();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView noteTitle = (TextView) convertView.findViewById(R.id.simpleListItemNote);
        TextView noteDate = (TextView) convertView.findViewById(R.id.simpleListItemDate);

        noteTitle.setText(note.getTitle());
        Log.d(TAG, note.getDate().toString());
        noteDate.setText(note.getDate().toString());

        return convertView;
    }
}