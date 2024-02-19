package jukebox.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilDb {

    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Driver loaded");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Jukebox","root", "System@123");
            //System.out.println("Connection established");
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


}
