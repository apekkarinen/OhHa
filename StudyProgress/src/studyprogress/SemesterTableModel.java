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
 * @author Antti Pekkarinen
 */
public class SemesterTableModel extends AbstractTableModel {
    private String[] columnnames;
    private Object[][] data;
    
    /**
     * Class constructor.
     * @param data Data to be displayed in this model's JTable.
     * @param columnnames Column names for the JTable.
     */
    public SemesterTableModel(Object[][] data, String[] columnnames) {
        this.data = data;
        this.columnnames = columnnames;
    }
    /**
     * Counts the columns of this JTableModel's JTable.
     * @return Number of columns in this model's JTable.
     */
    @Override
    public int getColumnCount() {
        return columnnames.length;
    }
    /**
     * Counts the rows of this JTableModel's JTable.
     * @return Number of rows in this model's JTable.
     */
    @Override
    public int getRowCount() {
        return data.length;
    }
    /**
     * Returns the name of a specified column in this model's JTable.
     * @param column The column index.
     * @return The name of the column at column.
     */
    @Override
    public String getColumnName(int column) {
        return columnnames[column];
    }
    /**
     * Gets a value from the JTable specified by the indices row and column.
     * @param row The row index of the value.
     * @param column The column index of the value.
     * @return The value at row, column.
     */
    @Override
    public Object getValueAt(int row, int column) {
        try {
            return data[row][column];
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * Returns the class of value in the specified column.
     * @param column The index of the column.
     * @return The class if the value at index column.
     */
    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
    /**
     * Updates the data of this model's JTable.
     * @param data New data for this model's JTable.
     */
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
