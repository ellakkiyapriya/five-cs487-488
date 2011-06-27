/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation.guiForVirtualTrading;

import business.virtualTrading.Order;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dinh
 */
public class OrderTableModel extends AbstractTableModel{
    Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
    };

    boolean[] canEdit = new boolean [] {
        false, false, false, false, false
    };

    private String[] columnNames = {
        "Buy/Sell", "Symbol", "Price", "Volume", "Value"
    };

    private ArrayList<Object[]> data = new ArrayList<Object[]>();


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

        @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    public void addRow(Order order) {
        Object[] newRow = convertOrder(order);
        data.add(newRow);
    }

    public void insertRow(int rowIndex, Order order) {
        Object[] newRow = convertOrder(order);
        data.add(rowIndex, newRow);
    }

    public void deleteRow(int rowIndex) {
        data.remove(rowIndex);
    }

    public void deleteRows(int[] rowIndices) {
        int count = 0;
        for (int rowIndex : rowIndices) {
            data.remove(rowIndex - count);
            ++count;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex] = aValue;
    }

    public void setData(ArrayList<Order> orderByDate) {
        data.clear();

        for (Order order : orderByDate) {
            addRow(order);
        }

    }

    private Object[] convertOrder(Order order) {
        Object[] newRow = new Object[columnNames.length];
        if (order.getOrderType())
            newRow[0] = "Buy";
        else
            newRow[0] = "Sell";
        
        newRow[1] = order.getAsset().getSymbol();
        newRow[2] = order.getPrice();
        newRow[3] = order.getVolume();
        newRow[4] = order.getValue();
        return newRow;
    }

}
