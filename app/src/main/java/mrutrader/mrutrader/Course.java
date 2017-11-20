package mrutrader.mrutrader;

/**
 * Created by Khaled on 2017-11-19.
 */

public class Course {

    private String userID;
    public String courseName;
    private int price;
    private boolean textBook;
    private boolean lectureNotes;
    private boolean tutoring;


    public Course() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Course(String userID, String courseName,int price, boolean textBook, boolean lectureNotes, boolean tutoring) {
        this.userID = userID;
        this.courseName = courseName;
        this.price = price;
        this.textBook = textBook;
        this.lectureNotes = lectureNotes;
        this.tutoring = tutoring;
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