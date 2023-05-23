//package org.Database;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class DatabaseConfiguration
//{
//
//
//
//    Connection c;
//    Statement s;
//    DatabaseConfiguration(){
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "c##proiectPAO", "password");
//            s = c.createStatement();
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//}
package org.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfiguration {
    private static Connection connection;

    private DatabaseConfiguration() {
    }

    public static Connection getDatabaseConnection() {
        if (connection == null) {
            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "C##MDSUSER", "password");
                Statement s = connection.createStatement();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeDatabaseConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
