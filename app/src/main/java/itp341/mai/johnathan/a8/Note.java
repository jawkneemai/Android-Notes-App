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

    // Getters and Setters

    public void setTitle(String title) {
        mTitle = title;
    }
    public String getTitle() {
        return mTitle;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getContent() {
        return mContent;
    }

    public void setDate(Calendar date) {
        mDate = date;
    }

    public Calendar getDate() {
        return mDate;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
