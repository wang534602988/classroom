package app;

import app.sql_database.SqlStart;
import app.used_class.Book;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Login extends Application {
    //
    public static String viewableSID = null;
    public static String DATABASE_NAME = "classroom_choose";
    public static String USER = "root";
    public static String PASSWORD = "";
    public static SqlStart database;
    public static Book oneBook = new Book(null, null, null, null, null, 0, null, 0, false);

    static {
        try {
            database = new SqlStart(DATABASE_NAME, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Login() throws SQLException {
    }

    //
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

