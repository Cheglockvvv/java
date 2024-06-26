package com.vorobey.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionUtil() {}
    public static Connection get() {
        try {
             return DriverManager.getConnection(
                     PropertiesUtil.get(URL_KEY),
                     PropertiesUtil.get(USERNAME_KEY),
                     PropertiesUtil.get(PASSWORD_KEY)
             );
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }
}
