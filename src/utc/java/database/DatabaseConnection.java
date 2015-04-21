/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utc.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ngo Hado
 */
public class DatabaseConnection {
    
    /**
     * Phương thức lấy kết nối Database
     * @return
     * @throws SQLException 
     */
    public static Connection getDatabaseConnection() throws SQLException{
        String userName = "sa";
        String passWord = "123456";
        String databaseName = "QuanLyThiSinh";
        String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName="+databaseName;
        Connection dbConnect = DriverManager.getConnection(connectionURL, userName, passWord);
        return dbConnect ;
    }
    
}
