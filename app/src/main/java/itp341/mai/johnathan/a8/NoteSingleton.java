package itp341.mai.johnathan.a8;


import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NoteSingleton {

    // Instance Variables
    private ArrayList<Note> mNoteList;

    private Context mAppContext;
    private static NoteSingleton sNoteList;

    private NoteSingleton(Context c) {
        mAppContext = c;
        mNoteList = new ArrayList<Note>();
        mNoteList.add(new Note("Welcome to Note Taker!", "Take notes anywhere.", Calendar.getInstance().getTime()));
        mNoteList.add(new Note("Created by Johnathan Mai.", "ITP361.", Calendar.getInstance().getTime()));
    }

    // Singleton Getter
    public static NoteSingleton get (Context c) {
        if (sNoteList == null) {
            sNoteList = new NoteSingleton(c.getApplicationContext());
        }
        return sNoteList;
    }

    // Get all notes
    public ArrayList<Note> getNoteList() {
        return mNoteList;
    }

    // Get single note given index
    public Note getNote(int index) {
        return mNoteList.get(index);
    }

    // Add a note to the list
    public void addNote(Note note) {
        mNoteList.add(note);
    }

    // Remove a note from the list
    public void removeNote(int index) {
        mNoteList.remove(index);
    }

}
