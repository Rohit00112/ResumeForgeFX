package com.snapgramfx.resumeforgefx.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class for database operations.
 */
public class DatabaseUtil {
    private static final String DB_PROPERTIES_FILE = "database.properties";
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;
    private static String dbDriver;
    
    static {
        try {
            loadDatabaseProperties();
            // Load the database driver
            Class.forName(dbDriver);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Load database properties from the properties file.
     * 
     * @throws IOException if the properties file cannot be read
     */
    private static void loadDatabaseProperties() throws IOException {
        Properties props = new Properties();
        InputStream inputStream = DatabaseUtil.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE);
        
        if (inputStream != null) {
            props.load(inputStream);
            dbUrl = props.getProperty("db.url");
            dbUser = props.getProperty("db.user");
            dbPassword = props.getProperty("db.password");
            dbDriver = props.getProperty("db.driver");
            inputStream.close();
        } else {
            throw new IOException("Database properties file '" + DB_PROPERTIES_FILE + "' not found in the classpath");
        }
    }
    
    /**
     * Get a database connection.
     * 
     * @return a Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
    
    /**
     * Close a database connection.
     * 
     * @param connection the Connection object to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
