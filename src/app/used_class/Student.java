package app.used_class;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    int MAX_COUNT_BOOK = 100;
    final SimpleStringProperty StudentID;
    final SimpleStringProperty Password;

    Student (String studentID, String password, String[] bookID){
        StudentID = new SimpleStringProperty(studentID);
        Password = new SimpleStringProperty(password);
    }
}
