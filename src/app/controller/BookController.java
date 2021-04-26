package app.controller;

import app.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookController extends Login {

    public Label seatLable;
    public Label sutentidLable;
    public TextField cIDText;
    public DatePicker datePicker;
    public Label locationLable;
    public Label budLable;
    public Button bookSureButton;
    public TextField orderText;

    public BookController() throws SQLException {
    }

    @FXML
    void initialize() {
        sutentidLable.setText(oneBook.getStudentID());
        cIDText.setText(oneBook.getClassroomID());
        datePicker.setValue(LocalDate.parse(oneBook.getBookDate()));
        locationLable.setText(oneBook.getLocation());
        seatLable.setText(String.valueOf(oneBook.getSeatMount()));
        budLable.setText(oneBook.getBuilding());
        orderText.setText(String.valueOf(oneBook.getBookOrder()));
    }

    @FXML
        //book sure start
    void bookClick(ActionEvent event) throws IOException, SQLException {
        String info = "目标不存在";
        if (database.bookInset(oneBook)) {
            info = "succeed";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info, new ButtonType("确定", ButtonBar.ButtonData.YES));
        alert.setHeaderText(null);
        alert.setTitle("Succeed");
        alert.show();
        Stage stage = (Stage) bookSureButton.getScene().getWindow();
        stage.close();
    }
}
