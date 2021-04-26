package app.controller;

import app.used_class.Book;
import app.used_class.Classroom;
import app.used_class.WindowsControl;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class MainController extends LoginController {

    //exception
    public MainController() throws SQLException {
        super();
    }

    //fxml element
    //search part start
    public TableView<Classroom> tableView1;
    public Button exitButton;
    public Button reloginButton;
    public ComboBox SearchComBox;
    public TextField SearchText;
    public Button SearchButton;
    public Button add1Button;
    public ComboBox SearchComBox2;
    public TextField SearchText2;
    public Button add2Button;
    public ComboBox SearchComBox3;
    public TextField SearchText3;
    public VBox vBox2;
    public VBox vBox1;
    public TableColumn<Classroom, String> tableColumn1;
    public TableColumn<Classroom, String> tableColumn2;
    public TableColumn<Classroom, String> tableColumn3;
    public TableColumn<Classroom, Integer> tableColumn4;
    public TableColumn<Classroom, String> tableColumn5;
    public TableColumn<Classroom, Integer> tableColumn6;
    public TableColumn<Classroom, Boolean> tableColumn7;

    public TableView<Classroom> tableView2;
    public TextField aText4;
    public ComboBox acomboBox2;
    public ComboBox acomboBox3;
    public DatePicker dataPicker5;
    public ComboBox<String> acomboBox6;
    public TableColumn<Classroom, String> avColumn1;
    public TableColumn<Classroom, String> avColumn2;
    public TableColumn<Classroom, String> avColumn3;
    public TableColumn<Classroom, Integer> avColumn4;
    public TableColumn<Classroom, String> avColumn5;
    public TableColumn<Classroom, String> avColumn6;
    public TableColumn<Classroom, Boolean> avColumn7;
    public Button startButton;

    public TableView<Book> tableView3;
    public TableColumn<Book, String> hcolumn0;
    public TableColumn<Book, String> hcolumn1;
    public TableColumn<Book, String> hcolumn2;
    public TableColumn<Book, String> hcolumn3;
    public TableColumn<Book, Integer> hcolumn4;
    public TableColumn<Book, String> hcolumn5;
    public TableColumn<Book, Integer> hcolumn6;
    public TableColumn<Book, Boolean> hcolumn7;
    public TableColumn<Book, Boolean> hcolumn8;
    public Tab histab;

    @FXML
    void initialize() {
        SearchText.setText("");
        SearchText2.setText("");
        SearchText3.setText("");
        SearchComBox.setValue("default");
        SearchComBox2.setValue("default");
        SearchComBox3.setValue("default");
    }
    //exitClick start
    public void exitClick(ActionEvent actionEvent) throws SQLException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        database.close();
    }
    //exitClick end

    //relogin start
    public void reLogin(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) reloginButton.getScene().getWindow();
        stage.close();
        goLogin(true);

    }

    //relogin end
    @FXML
    //searchClick Start
    void show1(ActionEvent actionEvent) {
        vBox1.setVisible(true);
        add2Button.setVisible(true);
    }

    @FXML
    void show2(ActionEvent actionEvent) {
        vBox2.setVisible(true);
    }

    @FXML
    void searchClick(ActionEvent actionEvent) throws SQLException {
        String[] choose = {SearchComBox.getValue().toString(), SearchComBox2.getValue().toString(),SearchComBox3.getValue().toString()};
        String[] text = {SearchText.getText(), SearchText2.getText(), SearchText3.getText()};
        SearchComBox.setTooltip(new Tooltip("Select one item"));
        tableView1.setItems(null);
        ObservableList<Classroom> data = FXCollections.observableArrayList(database.roomFind(choose, text));
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("classroomID"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("location"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("building"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("seatMount"));
        tableColumn5.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumn6.setCellValueFactory(new PropertyValueFactory<>("order"));
        tableColumn7.setCellValueFactory(features -> new SimpleBooleanProperty(features.getValue() != null));
        // create a cell value factory with an add button for each row in the table.
        tableColumn7.setCellFactory(personBooleanTableColumn -> new AddPersonCell(tableView1, 7));
        tableView1.setItems(data);
    }
    //searchClick End
    //search part end

    //available part start
    public void startClick(ActionEvent actionEvent) throws SQLException {
        String location = null;
        String building = null;
        int seatMount = 0;
        LocalDate date = null;
        int order = 0;
        acomboBox2.setTooltip(new Tooltip("choose a location"));
        acomboBox3.setTooltip(new Tooltip("choose a building"));
        acomboBox6.setTooltip(new Tooltip("choose a building"));
        aText4.setTooltip(new Tooltip("enter the mount of seats needed"));
        dataPicker5.setTooltip(new Tooltip("choose a date"));
        acomboBox6.setTooltip(new Tooltip("choose a class order"));
        acomboBox6.setValue("0");
        location = acomboBox2.getValue().toString();
        building = acomboBox3.getValue().toString();
        if (!aText4.getText().equals("")) {
            seatMount = Integer.parseInt(aText4.getText());
        }
        date = dataPicker5.getValue();
        order = Integer.parseInt(acomboBox6.getValue());
        tableView2.setItems(null);
        ObservableList<Classroom> data = FXCollections.observableArrayList(database.roomSelect(location, building, seatMount, date, order));
        avColumn1.setCellValueFactory(new PropertyValueFactory<>("classroomID"));
        avColumn2.setCellValueFactory(new PropertyValueFactory<>("location"));
        avColumn3.setCellValueFactory(new PropertyValueFactory<>("building"));
        avColumn4.setCellValueFactory(new PropertyValueFactory<>("seatMount"));
        avColumn5.setCellValueFactory(new PropertyValueFactory<>("date"));
        avColumn6.setCellValueFactory(new PropertyValueFactory<>("order"));
        avColumn7.setCellValueFactory(features -> new SimpleBooleanProperty(features.getValue() != null));
        avColumn7.setCellFactory(classroomBooleanTableColumn -> new AddPersonCell(tableView2, 7));
        tableView2.setItems(data);
    }

    //available part end

    //history start

    public void historyLookup(Event event) throws SQLException {
        tableView3.setItems(null);
        ObservableList<Book> data = FXCollections.observableArrayList(database.historyShow(viewableSID));
        hcolumn0.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        hcolumn1.setCellValueFactory(new PropertyValueFactory<>("classroomID"));
        hcolumn2.setCellValueFactory(new PropertyValueFactory<>("location"));
        hcolumn3.setCellValueFactory(new PropertyValueFactory<>("building"));
        hcolumn4.setCellValueFactory(new PropertyValueFactory<>("seatMount"));
        hcolumn5.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
        hcolumn6.setCellValueFactory(new PropertyValueFactory<>("bookOrder"));
        hcolumn7.setCellValueFactory(new PropertyValueFactory<>("returned"));
        hcolumn8.setCellValueFactory(features -> new SimpleBooleanProperty(features.getValue() != null));
        hcolumn8.setCellFactory(bookBooleanTableColumn -> new returnCell(tableView3,9));
        tableView3.setItems(data);
    }
    //history end

    //A table cell containing a button for adding a new person.
    private static class AddPersonCell extends TableCell<Classroom, Boolean> {
        final Button addButton = new Button("Book");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        AddPersonCell(final TableView<Classroom> table, int location) {
            paddedButton.setPadding(new Insets(location));
            paddedButton.getChildren().add(addButton);
            addButton.setOnMousePressed(mouseEvent -> buttonY.set(mouseEvent.getScreenY()));
            addButton.setOnAction(actionEvent -> {
                table.getSelectionModel().select(getTableRow().getIndex());
                table.getSelectionModel().select(getIndex());
                Classroom classroom = table.getSelectionModel().getSelectedItem();
                oneBook.setClassroomID(classroom.getClassroomID());
                oneBook.setStudentID(viewableSID);
                oneBook.setLocation(classroom.getLocation());
                oneBook.setBuilding(classroom.getBuilding());
                oneBook.setSeatMount(classroom.getSeatMount());
                oneBook.setBookDate(classroom.getDate());
                oneBook.setBookOrder(classroom.getOrder());
                oneBook.setReturned(false);
                oneBook.setBookID(getNowTime());
                try {
                    goBook(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }

    private static class returnCell extends TableCell<Book, Boolean> {
        final Button addButton = new Button("Return");
        final StackPane paddedButton = new StackPane();
        final DoubleProperty buttonY = new SimpleDoubleProperty();
        returnCell(final TableView<Book> table, int location) {
            paddedButton.setPadding(new Insets(location));
            paddedButton.getChildren().add(addButton);
            addButton.setOnMousePressed(mouseEvent -> buttonY.set(mouseEvent.getScreenY()));
            addButton.setOnAction(actionEvent -> {
                table.getSelectionModel().select(getTableRow().getIndex());
                table.getSelectionModel().select(getIndex());
                Book book = table.getSelectionModel().getSelectedItem();
                oneBook.setClassroomID(book.getClassroomID());
                oneBook.setStudentID(viewableSID);
                oneBook.setLocation(book.getLocation());
                oneBook.setBuilding(book.getBuilding());
                oneBook.setSeatMount(book.getSeatMount());
                oneBook.setBookDate(book.getBookDate());
                oneBook.setBookOrder(book.getBookOrder());
                oneBook.setReturned(true);
                oneBook.setBookID(book.getBookID());
                try {
                    goReturn(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }


    public static Stage goBook(boolean check) throws IOException {
        WindowsControl windowsBook = new WindowsControl("Book", "app/view/book.fxml", 400, 400);
        windowsBook.open();
        return windowsBook.getStage();
    }

    public static Stage goReturn(boolean check) throws IOException {
        WindowsControl windowsBook = new WindowsControl("Book", "app/view/returned.fxml", 400, 400);
        windowsBook.open();
        return windowsBook.getStage();
    }

    public static String getNowTime() {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String sDate = format1.format(date).replace("-. :", "");
        return sDate;
    }
}

