/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utc.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public static void main(String[] args) throws SQLException {
        Connection connect = null ;
        try {
             connect = DatabaseConnection.getDatabaseConnection();
            
            ArrayList<Candidate> arr = GetData.getAllCandidatesInformation(connect);
            
            for(int i=0 ; i<arr.size() ; i++){
                Candidate a = arr.get(i);
                
                System.out.println("Candidate "+a.getId()+" :");
                System.out.println("Name : "+a.getFullName());
                System.out.println("Place : "+a.getProvince().getProvinceName());
                System.out.println("DateOfBirth : "+a.getDateOfBirh());
                if(a.getSex()){
                    System.out.println("Sex : Male");
                } else{
                    System.out.println("Sex : Female");
                }
                System.out.println("Math : "+a.getMathPoint());
                System.out.println("Physics : "+a.getPhysicalPoint());
                System.out.println("Chemistry : "+a.getChemistryPoint());
                System.out.println("English : "+a.getEnglishPoint());
                System.out.println("Unit : "+a.getUnit());
            }
            
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            connect.close();
        }
    }
}
