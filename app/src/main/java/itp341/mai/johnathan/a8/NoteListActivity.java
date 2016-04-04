package itp341.mai.johnathan.a8;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Formatter;

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

        // ArrayAdapter Set Up
        mNoteList = NoteSingleton.get(getApplicationContext()).getNoteList();
        itemsAdapter = new ArrayAdapter<Note>(this, R.layout.list_item, R.id.simpleListItemNote, mNoteList);
        mListViewNotes.setAdapter(itemsAdapter);





    }
}

class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

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
        noteDate.setText(f.format("%tD", note.getDate()).toString());

        return convertView;
    }
}