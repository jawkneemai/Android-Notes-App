package itp341.mai.johnathan.a8;


import java.util.Calendar;

public class Note {

    private String mTitle;
    private String mContent;
    private Calendar mDate;

    public Note() {
        mTitle = "";
        mContent = "";
        mDate = null;
    }

    public Note(String title, String content, Calendar date) {
        mTitle = title;
        mContent = content;
        mDate = date;
    }

}
