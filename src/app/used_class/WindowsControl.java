package app.used_class;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class WindowsControl {
    String title = "Refault";
    String fxml = "login.fxml";
    float height = 300;
    float width = 200;
    private Stage primaryStage;
    public WindowsControl(String title, String fxml, float height, float width) throws IOException {
        this.title = title;
        this.fxml = fxml;
        this.height = height;
        this.width = width;
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, width, height));
    }

    public void open() throws IOException {
        primaryStage.show();
    }

    public void  close(){
        primaryStage.hide();
    }

    public Stage getStage(){
        return primaryStage;
    }
}
