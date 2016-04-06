package itp341.mai.johnathan.a8;


import java.util.Calendar;
import java.util.Date;

public class Note {

    private String mTitle;
    private String mContent;
    private Date mDate;

    public Note() {
        mTitle = "";
        mContent = "";
        mDate = null;
    }

    public Note(String title, String content, Date date) {
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

    public void setDate(Date date) {
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
