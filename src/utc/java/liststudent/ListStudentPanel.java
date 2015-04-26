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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.xml.crypto.Data;
import utc.java.database.AdjustData;
import utc.java.database.Candidate;
import utc.java.database.GetData;
import utc.java.sort.SortData;

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

    private final JPanel pNorth = new JPanel(new FlowLayout(1, 10, 10));
    private final JPanel pSouth = new JPanel(new FlowLayout(1, 40, 10));

    private final TableModelCustom tbmCustom = new TableModelCustom();

    private final Dimension dmsButton = new Dimension(100, 25);

    private ArrayList<Candidate> arrCandidate = new ArrayList<Candidate>();
    private ArrayList<Candidate> arrCurrentFiltre = new ArrayList<Candidate>();
    private Vector allData = new Vector();

    private Connection connect;

//    private final DefaultComboBoxModel modelCbb = new DefaultComboBoxModel(tbmCustom.columns);
    public ListStudentPanel(Connection conn, int level) throws SQLException {
        setLayout(new BorderLayout());
        connect = conn;
        refreshDatabase();

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
                setVisible(false);
                //visible true panel information .dat cac panel la public static
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
                try {
                    actionUpdateButtonListener();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ListStudentPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                actionDeleteButtonListener();

            }
        });

        //button refresh
        btRefresh = new JButton("REFRESH");
        btRefresh.setPreferredSize(dmsButton);
        btRefresh.setFocusable(false);
        btRefresh.setIcon(new ImageIcon("refresh.png"));
        btRefresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshDatabase();
                    tbmCustom.setTableModel(allData, tbStudent);
                } catch (SQLException ex) {
                    Logger.getLogger(ListStudentPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
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
                String colName = tbStudent.getColumnName(col);

                tbmCustom.setTableModel(SortData.getDataSorted(arrCurrentFiltre, colName), tbStudent);
            }

        });
        tbmCustom.setTableModel(allData, tbStudent);

        JScrollPane scrPane = new JScrollPane(tbStudent, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JViewport jvp = new JViewport();
        jvp.setView(new RowNumberHeader(tbStudent));
        scrPane.setRowHeader(jvp);
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

    /**
     * Hien thi danh sach sau khi loc
     *
     * @param keySearch
     * @param typeSearch
     * @throws SQLException
     */
    public void refreshListFiltred(String keySearch, String typeSearch) throws SQLException {
        arrCurrentFiltre.clear();
        arrCurrentFiltre.addAll(Filtre.filtreData(arrCandidate, typeSearch, keySearch));
        Vector dataTable = GetData.toString(arrCurrentFiltre);
        tbmCustom.setTableModel(dataTable, tbStudent);
    }

    /**
     * Tuy vao muc do truy cap ma se cho user su dung nhung chuc nang khac nhau
     *
     * @param level
     */
    private void adjustUI(int level) {
        switch (level) {
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

    /**
     * Get lai toan bo data tu databasew
     *
     * @throws SQLException
     */
    private void refreshDatabase() throws SQLException {
        arrCandidate.clear();
        arrCandidate.addAll(GetData.getAllCandidatesInformation(connect));
        arrCurrentFiltre.clear();
        arrCurrentFiltre.addAll(arrCandidate);
        allData = GetData.toString(arrCandidate);
    }

    public void actionUpdateButtonListener() throws SQLException {
        try {
            int rowSelected = tbStudent.getSelectedRow();
            String idSelected = tbStudent.getModel().getValueAt(rowSelected, 0).toString();
            Candidate dataRow = GetData.getOneCandidatesInformation(connect, idSelected);
            System.out.println(dataRow.getId() + "\t" + dataRow.getFullName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select the candidate you want update !","Notify", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void actionDeleteButtonListener(){
        try {
            int rowSelected = tbStudent.getSelectedRow();
            String idSelected = tbStudent.getModel().getValueAt(rowSelected, 0).toString();
            int clicked = JOptionPane.showConfirmDialog(null, "Do you really want to delete this candidate("+idSelected+") ?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(clicked == JOptionPane.YES_OPTION){
                AdjustData.deleteCandidate(connect, idSelected);
                refreshDatabase();
                tbmCustom.setTableModel(allData, tbStudent);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select the candidate you want delete !","Notify", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
