package mrutrader.mrutrader;

/**
 * Created by Khaled on 2017-11-19.
 */

public class Course {

    public String courseID;
    public String userID;
    public String courseName;
    public int price;
    public boolean textBook;
    public boolean lectureNotes;
    public boolean tutoring;
    public String courseKey;
    public boolean trade;

    public Course() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Course(String userID, String courseName,int price, boolean textBook, boolean lectureNotes, boolean tutoring, boolean trade) {
        this.userID = userID;
        this.courseName = courseName;
        this.price = price;
        this.textBook = textBook;
        this.lectureNotes = lectureNotes;
        this.tutoring = tutoring;
        this.trade = trade;
    }


    public Course(String userID, String courseName,int price, boolean textBook,
                  boolean lectureNotes, boolean tutoring, String courseKey, boolean trade) {
        this.userID = userID;
        this.courseName = courseName;
        this.price = price;
        this.textBook = textBook;
        this.lectureNotes = lectureNotes;
        this.tutoring = tutoring;
        this.courseKey = courseKey;
        this.trade = trade;
    }


    public int getPrice (){
        return this.price;
    }

    public boolean getTextBook (){
        return this.textBook;
    }

    public boolean getLectureNotes (){
        return this.lectureNotes;
    }

    public boolean getTutoring(){
        return this.tutoring;
    }

    @Override
    public String toString() {
        return this.courseName + "\n $" + this.price;

    }

}