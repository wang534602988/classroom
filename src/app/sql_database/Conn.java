package app.sql_database;

//导入包

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 数据库连接
 */
class Conn {
    String databaseName;
    String user;
    String password;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url;
    Connection con = null;

    Conn() {
    }

    Connection getConnection(String databaseName, String user, String password) {
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;
        url = "jdbc:mysql://localhost:3306/" + databaseName + "?&useSSL=false&serverTimezone=UTC";
        //jdbc驱动
        try {
            //注册JDBC驱动程序
            Class.forName(driver);
            //建立连接
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("+" + "数据库连接失败");
        }
        return con;
    }

    void close() throws SQLException {
        con.close();
    }
}

