/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc.java.liststudent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import utc.java.database.Candidate;
import utc.java.database.GetData;

/**
 *
 * @author Ngô
 */
public class ListStudentPanel extends JPanel {

    private final JButton btAdd;
    private final JButton btUpdate;
    private final JButton btDelete;
    private final JButton btFiltre;
    private final JButton btRefresh;

    private final JTextField tfKeySearch;

    private final JComboBox cbbTypeSearch;

    private JTable tbStudent;

    private final JPanel pNorth = new JPanel(new FlowLayout(1,10,10));
    private final JPanel pSouth = new JPanel(new FlowLayout(1, 40, 10));

    private final TableModelCustom tbmCustom = new TableModelCustom();

    private final Dimension dmsButton = new Dimension(100, 25);

    private ArrayList<Candidate> arrCandidate = new ArrayList<Candidate>();
    private Vector allData = new Vector();
    
//    private final DefaultComboBoxModel modelCbb = new DefaultComboBoxModel(tbmCustom.columns);
    
    public ListStudentPanel(ArrayList<Candidate> arrCdd,int level) {
        setLayout(new BorderLayout());
        arrCandidate = arrCdd ;
        allData = GetData.toString(arrCandidate);
         //main panel
        add(pNorth, BorderLayout.NORTH);
        add(pSouth, BorderLayout.SOUTH);
        
        //textfield
        tfKeySearch = new JTextField();
        tfKeySearch.setPreferredSize(new Dimension(250, 25));

        //combobox   
        DefaultComboBoxModel modelCbb = new DefaultComboBoxModel(tbmCustom.columns);
        cbbTypeSearch = new JComboBox(modelCbb);
        cbbTypeSearch.setFocusable(false);

        //button filtre
        btFiltre = new JButton("Filtre");
        btFiltre.setPreferredSize(dmsButton);
        btFiltre.setFocusable(false);
//        btFiltre.setVerticalTextPosition(SwingConstants.BOTTOM);
//        btFiltre.setHorizontalTextPosition(SwingConstants.CENTER);
        btFiltre.setIcon(new ImageIcon("filtre.png"));
        btFiltre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String type = cbbTypeSearch.getSelectedItem().toString();
                String key = tfKeySearch.getText();
                try {
                    refreshListFiltred(key, type);
                } catch (SQLException ex) {
                    Logger.getLogger(ListStudentPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }           
        });

        //button add
        btAdd = new JButton("ADD");
        btAdd.setPreferredSize(dmsButton);
        btAdd.setFocusable(false);
        btAdd.setIcon(new ImageIcon("add.png"));
        btAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshListFiltred(tfKeySearch.getText(), cbbTypeSearch.getSelectedItem().toString());
                } catch (SQLException ex) {
                    Logger.getLogger(ListStudentPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //button update
        btUpdate = new JButton("UPDATE");
        btUpdate.setPreferredSize(dmsButton);
        btUpdate.setFocusable(false);
        btUpdate.setIcon(new ImageIcon("update.png"));
        btUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        //button delete
        btDelete = new JButton("DELETE");
        btDelete.setPreferredSize(dmsButton);
        btDelete.setFocusable(false);
        btDelete.setIcon(new ImageIcon("delete.png"));
        btDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        
        btRefresh = new JButton("REFRESH");
        btRefresh.setPreferredSize(dmsButton);
        btRefresh.setFocusable(false);
        btRefresh.setIcon(new ImageIcon("refresh.png"));
        btRefresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tbmCustom.setTableModel(allData, tbStudent);
            }
        });

        //table
        tbStudent = new JTable();
        tbStudent.getTableHeader().setReorderingAllowed(false);
        tbStudent.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbStudent.getTableHeader().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int col = tbStudent.columnAtPoint(e.getPoint());
                System.out.println("Column at : " + tbStudent.getColumnName(col));
            }

        });
        tbmCustom.setTableModel(allData, tbStudent);

        JScrollPane scrPane = new JScrollPane(tbStudent, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrPane, BorderLayout.CENTER);

        //sub panel
        pNorth.add(new JLabel("Key Search : "));
        pNorth.add(tfKeySearch);
        pNorth.add(cbbTypeSearch);
        pNorth.add(btFiltre);

        pSouth.add(btAdd);
        pSouth.add(btUpdate);
        pSouth.add(btDelete);
        pSouth.add(btRefresh);
        
        adjustUI(level);
        
    }
    
    public void refreshListFiltred(String keySearch,String typeSearch) throws SQLException{
        Vector dataTable = GetData.toString(Filtre.filtreData(arrCandidate, typeSearch, keySearch));
        tbmCustom.setTableModel(dataTable, tbStudent);
    }
    
    private void adjustUI(int level){
        switch(level){
            case 1:
                //do nothing
                break;
                
            case 2:
                btAdd.setEnabled(false);
                btDelete.setEnabled(false);
                break;
                
            case 3:
                btAdd.setEnabled(false);
                btDelete.setEnabled(false);
                btUpdate.setEnabled(false);
                break;
        }
    }
    
}
