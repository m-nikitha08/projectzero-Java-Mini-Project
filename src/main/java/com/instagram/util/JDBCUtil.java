package com.instagram.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    private static final String URL =
           // "jdbc:mysql://localhost:3306/instagram_db";// add your url//
            "jdbc:sqlserver://DESKTOP-AQ37V8C\\SQLEXPRESS;databaseName=SocialMediaApp;integratedSecurity=true;trustServerCertificate=true";

   // private static final String USERNAME =
           // "root";//enter your username here//

    //private static final String PASSWORD =
           // "";//enter your password here //

//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(
//                URL,
//                USERNAME,
//                PASSWORD
//        );
//    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL
        );
    }
}