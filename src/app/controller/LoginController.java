package app.controller;

import app.Login;
import app.used_class.WindowsControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController extends Login {

    public Button loginButton;
    public TextField idText;
    public PasswordField passwordText;
    public LoginController() throws SQLException {
    }
    //

    //CHECK
    @FXML
    void login_alert(ActionEvent actionEvent) throws IOException, SQLException {
        if (idText.getText() != null && passwordText.getText()!=null){
            String id = idText.getText();
            String password = passwordText.getText();
            viewableSID = id;
            if (database.idCheck(id, password)) {
                Stage stage = (Stage) passwordText.getScene().getWindow();
                stage.close();
                goMain(true);
            } else {
                createAlert();
            }
        }else {
            System.out.println("id or password can't be null!");
        }
    }

    void createAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "id or password wrong!", new ButtonType("确定", ButtonBar.ButtonData.YES));
        alert.setHeaderText(null);
        alert.setTitle("Welcome");
        alert.show();
    }

    public Stage goMain(boolean check) throws IOException {
        WindowsControl windowsMain = new WindowsControl("Main", "app/view/main.fxml", 400, 600);
        if (check) {
            windowsMain.open();
        } else {
            windowsMain.close();
        }
        return windowsMain.getStage();
    }

    public Stage goLogin(boolean check) throws IOException {
        WindowsControl windowsLogin = new WindowsControl("Login", "app/view/login.fxml", 200, 300);
        if (check) {
            windowsLogin.open();
        } else {
            windowsLogin.close();
        }
        return windowsLogin.getStage();
    }

}

