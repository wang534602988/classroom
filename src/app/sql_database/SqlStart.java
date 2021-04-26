package app.sql_database;

import app.used_class.Book;
import app.used_class.Classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SqlStart {
    String sql; // 用于后面对应不同的sql语句
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Conn conn;
    Connection connection;
    int MAX_MOUNT_RECORD = 100;
    //
    Classroom[] classrooms = new Classroom[MAX_MOUNT_RECORD];
    int i = 0;
    String bookID;
    String studentID;
    String classroomID;
    String location;
    String building;
    int seatMount;
    String date;
    int order;
    boolean returned;


    public SqlStart(String databaseName, String user, String password) throws SQLException {
        //建立连接
        conn = new Conn(); // 获取一个connection
        //Connection connection = conn.getConnection("new_schema","root","");
        connection = conn.getConnection(databaseName, user, password);
    }

    public void close() throws SQLException {
        connection.close();
        conn.close();
    }
    public boolean idCheck(String studentID, String password) throws SQLException {
        boolean check = false;
        sql = "SELECT studentid,password FROM student WHERE studentid = ? and password = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentID);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            check = true;
        }
        resultSet.close();
        return check;
    }

    public Classroom[] roomFind(String[] choose, String[] contain) throws SQLException {
        classrooms[0] = new Classroom("0", "0", "0", 0, "0", 0);
        String delimiter = " LIKE ? ";
        String dig = "";
        sql = "SELECT  * FROM Classroom WHERE available = 1";
        for (int j = 0; j < contain.length; j++) {
            if (!choose[j].equals("default")) {
                if (choose[j].equals("seatMount")) {
                    delimiter = " >=  ? ";
                }
                dig += " and " + choose[j] + delimiter;
            }
        }
        sql = sql + dig;
        preparedStatement = connection.prepareStatement(sql);
        for (int j = 0; j < choose.length; j++) {
            if (!choose[j].equals("default")) {
                if (choose[j].equals("seatMount") || choose[j].equals("Order")) {
                    preparedStatement.setInt(j+1, Integer.parseInt(contain[j]));
                } else {
                    preparedStatement.setString(j + 1, contain[j]);
                }
            }
        }
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            classroomID = resultSet.getString("classroomid");
            location = resultSet.getString("location");
            building = resultSet.getString("building");
            seatMount = resultSet.getInt("seatmount");
            date = String.valueOf(resultSet.getDate("date"));
            order = resultSet.getInt("classorder");
            classrooms[i] = new Classroom(classroomID, location, building, seatMount, date, order);
            i++;
        }
        resultSet.close();
        i = 0;
        dig = "";
        sql = "";
        return classrooms;
    }

    public Classroom[] roomSelect(String location, String building, int seatMount, LocalDate date, int order) throws SQLException {
        i = 0;
        classrooms[0] = new Classroom("0", "0", "0", 0, "0", 5);
        boolean[] bool = new boolean[4];
        String sdate;
        if (date != null) {
            sdate = date.toString();
        } else {
            sdate = null;
        }
        String sseatMount;
        if (seatMount != 0) {
            sseatMount = String.valueOf(seatMount);
        } else {
            sseatMount = null;
        }
        String sorder;
        if (order != 0) {
            sorder = String.valueOf(order);
        } else {
            sorder = null;
        }
        String slocation = null;
        if (location != null) {
            slocation = "\"" + location + "\"";
        }
        String sbuilding = null;
        if (building != null) {
            sbuilding = "\"" + location + "\"";
        }
        String[] items = {slocation, sbuilding, sseatMount, sdate, sorder};
        String[] column = {"location", "building", "seatmount", "date", "order"};
        sql = "SELECT  * FROM Classroom WHERE classroomid is not null and available = 1";
        for (int j = 0; j < 5; j++) {
            if (items[i] != null) {
                sql = sql + " AND " + "" + column[i] + " LIKE " + items[i];
            }
        }
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            this.classroomID = resultSet.getString("classroomid");
            this.location = resultSet.getString("location");
            this.building = resultSet.getString("building");
            this.seatMount = resultSet.getInt("seatmount");
            this.date = String.valueOf(resultSet.getDate("date"));
            this.order = resultSet.getInt("classorder");
            classrooms[i] = new Classroom(this.classroomID, this.location, this.building, this.seatMount, this.date, this.order);
            i++;
        }
        resultSet.close();
        i = 0;
        return classrooms;
    }

    public Book[] historyShow(String studentID) throws SQLException {
        Book[] books = new Book[MAX_MOUNT_RECORD];
        sql = "select book.bookid, book.classroomid, book.studentid, classroom.location, classroom.building, classroom.seatmount, book.date,book.classorder, book.bookid, book.returned" +
                " from classroom,book" +
                " where classroom.classroomid = book.classroomid and book.studentid = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentID);
        resultSet = preparedStatement.executeQuery();
        i = 0;
        while (resultSet.next()) {
            bookID = resultSet.getString("bookid");
            classroomID = resultSet.getString("classroomid");
            studentID = resultSet.getString("studentid");
            location = resultSet.getString("location");
            building = resultSet.getString("building");
            seatMount = resultSet.getInt("seatmount");
            date = String.valueOf(resultSet.getDate("date"));
            order = resultSet.getInt("classorder");
            returned = resultSet.getBoolean("returned");
            books[i] = new Book(bookID, classroomID, studentID, location, building, seatMount, date, order, returned);
            i++;
        }
        i = 0;
        resultSet.close();
        return books;
    }

    public boolean bookInset(Book book) throws SQLException {
        boolean bol;
        sql = "select studentid from student where studentid = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, book.getStudentID());
        resultSet = preparedStatement.executeQuery();
        boolean bo11 = false;
        while (resultSet.next()) {
            bo11 = (resultSet.getString("studentid") != null);
        }
        sql = "select classroomid from classroom where classroomid = ? and date = ? and classorder = ? and available = 1";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, book.getClassroomID());
        preparedStatement.setString(2, book.getBookDate());
        preparedStatement.setInt(3, book.getBookOrder());
        resultSet = preparedStatement.executeQuery();
        boolean bo12 = false;
        while (resultSet.next()) {
            bo12 = (resultSet.getString("classroomid") != null);
        }
        bol = bo11 && bo12;
        if (bol) {
            sql = "insert into book (bookid, studentid, classroomid, date, classorder, returned) values (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getBookID());
            preparedStatement.setString(2, book.getStudentID());
            preparedStatement.setString(3, book.getClassroomID());
            preparedStatement.setString(4, book.getBookDate());
            preparedStatement.setInt(5, book.getBookOrder());
            int myInt = book.getReturned() ? 1 : 0;
            preparedStatement.setInt(6, myInt);
            preparedStatement.execute();
            sql = "update classroom set available = 0 where classroomid = ? and date = ? and classorder = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getClassroomID());
            preparedStatement.setString(2, book.getBookDate());
            preparedStatement.setInt(3, book.getBookOrder());
            preparedStatement.execute();
        }

        return bol;
    }

    public boolean bookReturn(Book book) throws SQLException {
        boolean bol = false;
        sql = "select bookid from book where bookid = ? and returned = 0";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, book.getBookID());
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            bol = (resultSet.getString("bookid") != null);
        }
        if (bol) {
            sql = "update book set returned = 1 where bookid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getBookID());
            preparedStatement.execute();
            sql = "update classroom set available = 1 where classroomid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getClassroomID());
            preparedStatement.execute();
        }
        return bol;
    }
}
        /*//test1,query
        sql = "select * from stu";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String traString = resultSet.getString("id");
            System.out.println(traString);
        }
        resultSet.close();

         */
