/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utc.java.liststudent;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * Copyright by http://codersontrang.com/
 */
class RowNumberHeader extends JTable {

    protected JTable mainTable;

    public RowNumberHeader (JTable table){
        super();
        mainTable = table;
        setModel(new RowNumberTableModel());
        setPreferredScrollableViewportSize(getMinimumSize());
        setRowSelectionAllowed(false);
        JComponent renderer = (JComponent)getDefaultRenderer(Object.class);
        LookAndFeel.installColorsAndFont(renderer, "TableHeader.background", 
                                         "TableHeader.foreground", "TableHeader.font");
        LookAndFeel.installBorder(this, "TableHeader.cellBorder");
    }

    @Override
    public int getRowHeight(int row){
        return mainTable.getRowHeight();
    }

    class RowNumberTableModel extends AbstractTableModel{

        public int getRowCount() {
            return mainTable.getModel().getRowCount();
        }

        public int getColumnCount() {
            return 1;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return new Integer(rowIndex+1);
        }
    }
    
}
