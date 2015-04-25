/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc.java.mainapp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.GridBagConstraints.CENTER;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tdt.Object.Candidate;
import utc.java.add.PanelAdd;
import utc.java.database.DatabaseConnection;
import utc.java.database.GetData;

/**
 *
 * @author admin
 */
public class FrameMain implements ActionListener{
    private JFrame fMain;
    private JPanel pToolbar;
    private JPanel pTool;
    private JPanel pTable;
    private JPanel pTable1;
    private JPanel pTable2;
    private JPanel pTable3;
    private JPanel pTable4;
    private JButton bList;
    private JButton bInfo;
    private JButton bQuerry;
    private JButton bAbout;   
    
    private Connection con ;
    
    private PanelAdd p2;
    
    public FrameMain(){
        initComponent();
    }
    
    private void initComponent(){
        DatabaseConnection dbc = new DatabaseConnection();
        GetData gd = new GetData();
        try {
            con = DatabaseConnection.getDatabaseConnection();
        } catch (SQLException ex) {
            System.out.println("Error!");        
        }
        
        fMain = new JFrame("Student manager");
        pToolbar = new JPanel();
        pTool = new JPanel(new FlowLayout(CENTER,16,30));
        bList = new JButton("List Student");
        bInfo = new JButton("Info");
        bQuerry = new JButton("Querry");
        bAbout = new JButton("About");
        pTable = new JPanel(new CardLayout());
        pTable1 = new JPanel();
        pTable2 = new JPanel();
        pTable3 = new JPanel();
        pTable4 = new JPanel();

        p2 = new PanelAdd();
                
        fMain.setLayout(new BorderLayout());
        fMain.setPreferredSize(new Dimension(800,600));        
        fMain.setSize(800, 600);
        fMain.setLocationRelativeTo(null);
        fMain.setResizable(false);
        
        pToolbar.setBackground(new Color(53, 96, 182));
        pToolbar.setPreferredSize(new Dimension(43,80));
        fMain.add(pToolbar,BorderLayout.NORTH);
        
        pTool.setBackground(new Color(234,239,247));
        pTool.setPreferredSize(new Dimension(160,320));
        
        bList.setFocusable(false);
        bList.setPreferredSize(new Dimension(130,40));
        bList.addActionListener(this);
        pTool.add(bList);
        
        //bInfo.setBorder(new javax.swing.border.LineBorder(Color.GRAY, 1, true));
        bInfo.setFocusable(false);
        bInfo.setPreferredSize(new Dimension(130,40));
        bInfo.addActionListener(this);
        pTool.add(bInfo);

        //bQuerry.setBorder(new javax.swing.border.LineBorder(Color.GRAY, 1, true));
        bQuerry.setFocusable(false);
        bQuerry.setPreferredSize(new Dimension(130,40));
        bQuerry.addActionListener(this);
        pTool.add(bQuerry);

        //bAbout.setBorder(new javax.swing.border.LineBorder(Color.GRAY, 1, true));
        bAbout.setFocusable(false);
        bAbout.setPreferredSize(new Dimension(130,40));
        bAbout.addActionListener(this);
        pTool.add(bAbout);
        
        fMain.add(pTool,BorderLayout.WEST);
        
        pTable1.setBackground(Color.white);
        JLabel l1 = new JLabel("table 1");
        pTable1.add(l1);
        pTable.add(pTable1,"card1");
        openPanel(pTable1, pTable2, pTable3, pTable4);
        
//        pTable2.setBackground(Color.white);
        pTable2 = p2.getPanelAdd();
        pTable.add(pTable2,"card2");
        
        pTable3.setBackground(Color.white);
        JLabel l3 = new JLabel("table 3");
        pTable3.add(l3);
        pTable.add(pTable3,"card3");
        
        pTable4.setBackground(Color.white);
        JLabel l4 = new JLabel("table 4");
        pTable4.add(l4);
        pTable.add(pTable1,"card4");

        fMain.add(pTable,BorderLayout.CENTER);
        
        
        fMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fMain.setVisible(true);
    }
    
    public static void main(String[] args) {
        FrameMain fm = new FrameMain();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton a = (JButton) e.getSource();
        if(a==bList) openPanel(pTable1,pTable2,pTable3,pTable4); 
        if(a==bInfo) openPanel(pTable2,pTable1,pTable3,pTable4); 
        if(a==bQuerry) openPanel(pTable3,pTable2,pTable1,pTable4); 
        if(a==bAbout) openPanel(pTable4,pTable2,pTable3,pTable1); 
    }
    
    private void openPanel(JPanel open,JPanel p1,JPanel p2,JPanel p3){
        open.setVisible(true);
        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
    }
}
