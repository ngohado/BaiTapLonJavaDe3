/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utc.java.mainapp;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.UIManager;
import utc.java.database.Candidate;
import utc.java.database.DatabaseConnection;
import utc.java.database.GetData;
import utc.java.liststudent.ListStudentPanel;

/**
 *
 * @author Ngô
 */
public class MainFrame {
    public static void main(String[] args) throws SQLException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JFrame mainFrame = new JFrame("Students Management Software");
        mainFrame.setSize(new Dimension(600, 400));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Connection connect = DatabaseConnection.getDatabaseConnection();
        ArrayList<Candidate> arr = GetData.getAllCandidatesInformation(connect);
        ListStudentPanel studentPanel = new ListStudentPanel(arr,3);
        mainFrame.add(studentPanel);
        mainFrame.setVisible(true);
   
    }
}