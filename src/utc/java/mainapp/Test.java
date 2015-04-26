/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utc.java.mainapp;

import java.sql.Connection;
import java.sql.SQLException;
import utc.java.database.AdjustData;
import utc.java.database.DatabaseConnection;

/**
 *
 * @author Ngô
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        Connection connect = DatabaseConnection.getDatabaseConnection();
        int code = AdjustData.insertProvince(connect, "Lao Cai");
        System.out.println(code);
    }
}
