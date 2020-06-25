/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Rafan
 */
public class DB {
    public static Connection connect() throws SQLException{
        
        String url = "jdbc:mysql://localhost:3306/banco_algcarros"; 
        Properties prop = new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password", "admin");
        prop.setProperty("serverTimeZone", "UTC");
        prop.setProperty("useSSL", "false");
        return DriverManager.getConnection(url, prop);
    }
}
