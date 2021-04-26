package app.used_class;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Classroom {
    public SimpleStringProperty classroomID;
    public SimpleStringProperty location;
    public SimpleStringProperty building;
    public SimpleIntegerProperty seatMount;
    public SimpleStringProperty date;
    public SimpleIntegerProperty order;

    public Classroom(String classroomID, String location, String building, int seatMount, String day, int order){
        this.classroomID = new SimpleStringProperty(classroomID);
        this.location = new SimpleStringProperty(location);
        this.building = new SimpleStringProperty(building);
        this.seatMount = new SimpleIntegerProperty(seatMount);
        this.date = new SimpleStringProperty(day);
        this.order = new SimpleIntegerProperty(order);;
    }

    public String getClassroomID() {
        return classroomID.get();
    }
    public void setClassroomID(String classroomID) {
        this.classroomID.set(classroomID);
    }

    public String getLocation() {
        return location.get();
    }
    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getBuilding() {
        return building.get();
    }
    public void setBuilding(String building) {
        this.building.set(building);
    }

    public int getSeatMount(){
        return seatMount.get();
    }
    public void setSeatMount(int seatMount){
        this.seatMount.set(seatMount);
    }

    public String getDate(){
        return date.get();
    }
    public void setDate(String date){
        this.date.set(date);
    }

    public int getOrder(){
        return this.order.get();
    }
    public void setOrder(int order){
        this.order.set(order);
    }
}
