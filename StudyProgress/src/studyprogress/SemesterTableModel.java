/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studyprogress;
import javax.swing.*;
import java.awt.*;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ausr
 */
public class SemesterTableModel extends AbstractTableModel {
    private String[] columnnames;
    private Object[][] data;
    
    public SemesterTableModel(Object[][] data, String[] columnnames) {
        this.data = data;
        this.columnnames = columnnames;
    }
    
    
    public int getColumnCount() {
        return columnnames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int column) {
        return columnnames[column];
    }

    public Object getValueAt(int row, int column) {
        return data[row][column];
    }
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
    public void setData(Object[][] data) {
        this.data = data;
        this.fireTableDataChanged();
    }
}
