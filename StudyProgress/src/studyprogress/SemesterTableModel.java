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
 *Custom table model class for the Semester data table in the graphical main menu screen.
 * @author ausr
 */
public class SemesterTableModel extends AbstractTableModel {
    private String[] columnnames;
    private Object[][] data;
    
    public SemesterTableModel(Object[][] data, String[] columnnames) {
        this.data = data;
        this.columnnames = columnnames;
    }
    
    @Override
    public int getColumnCount() {
        return columnnames.length;
    }
    @Override
    public int getRowCount() {
        return data.length;
    }
    @Override
    public String getColumnName(int column) {
        return columnnames[column];
    }
    @Override
    public Object getValueAt(int row, int column) {
        try {
            return data[row][column];
        } catch (Exception e) {
            return "";
        }
    }
    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
    public void setData(Object[][] data) {
        try {
            this.data = data;
            this.fireTableStructureChanged();
            this.fireTableDataChanged();
        }
        catch (Exception e) {
            
        }
    }
}
