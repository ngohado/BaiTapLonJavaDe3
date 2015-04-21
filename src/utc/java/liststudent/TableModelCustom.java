/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utc.java.liststudent;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ngô
 */
public class TableModelCustom {
    public Vector<String> columns = new Vector<String>();
    
    public TableModelCustom(){
        columns.add("ID");
        columns.add("Full Name");
        columns.add("Province");
        columns.add("Date Of Birth");
        columns.add("Gender");
        columns.add("Unit");
        columns.add("Math");
        columns.add("Physics");
        columns.add("Chemistry");
        columns.add("English");
        columns.add("Area");
    }
    
    public void setTableModel(Vector data,JTable table) {        
        TableModel model = new DefaultTableModel(data, columns);        
        table.setModel(model);    
        
        TableColumnAdjuster tbAdjuster = new TableColumnAdjuster(table);
        tbAdjuster.adjustColumns();

    }
}
