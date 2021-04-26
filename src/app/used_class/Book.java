package app.used_class;

import javafx.beans.property.*;

import java.util.SimpleTimeZone;

public class Book {
    private final SimpleStringProperty bookID;
    private final SimpleStringProperty classroomID;
    private final SimpleStringProperty studentID;
    private final SimpleStringProperty location;
    private final SimpleStringProperty building;
    private final SimpleIntegerProperty seatMount;
    private final SimpleStringProperty  bookDate;
    private final SimpleIntegerProperty bookOrder;
    private final SimpleBooleanProperty returned;

    public Book(String bookID, String classroomId, String studentID, String location, String building, int seatMount, String bookDate, int bookOrder, boolean returned){
        this.bookID = new SimpleStringProperty(bookID);
        this.classroomID = new SimpleStringProperty(classroomId);
        this.studentID = new SimpleStringProperty(studentID);
        this.location = new SimpleStringProperty(location);
        this.building = new SimpleStringProperty(building);
        this.seatMount = new SimpleIntegerProperty(seatMount);
        this.bookDate= new SimpleStringProperty(bookDate);
        this.bookOrder = new SimpleIntegerProperty(bookOrder);
        this.returned = new SimpleBooleanProperty(returned);
    }

    public String getBookID(){
        return bookID.get();
    }
    public void setBookID(String str1){
        bookID.set(str1);
    }

    public String getClassroomID(){
        return classroomID.get();
    }
    public void setClassroomID(String str1){
        classroomID.set(str1);
    }

    public String getStudentID(){
        return studentID.get();
    }
    public void setStudentID(String str1){
        studentID.set(str1);
    }

    public String getLocation(){
        return location.get();
    }
    public void setLocation(String str1){
        location.set(str1);
    }

    public String getBuilding(){
        return building.get();
    }
    public void setBuilding(String str1){
        building.set(str1);
    }

    public int getSeatMount(){
        return seatMount.get();
    }
    public void setSeatMount(int i1){
        seatMount.set(i1);
    }

    public String getBookDate(){
        return bookDate.get();
    }
    public void setBookDate(String str1){
        bookDate.set(str1);
    }

    public int getBookOrder(){
        return bookOrder.get();
    }
    public void setBookOrder(int i1){
        bookOrder.set(i1);
    }

    public boolean getReturned(){
        return returned.get();
    }
    public void  setReturned(boolean bo1){
        returned.set(bo1);
    }
}