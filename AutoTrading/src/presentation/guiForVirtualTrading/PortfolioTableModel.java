/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation.guiForVirtualTrading;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import business.virtualTrading.PortfolioEntry;

/**
 *
 * @author Dinh
 */
public class PortfolioTableModel extends AbstractTableModel{
    Class[] types = new Class [] {
        java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
    };
    
    boolean[] canEdit = new boolean [] {
        true, true, false, true, false
    };
    
    private String[] columnNames = {
        "Symbol", "Buy Price", "Current Price", "Volume", "Gain/Loss (%)"
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

    private Object[] convertPortfolioEntry(PortfolioEntry portfolioEntry) {
        Object[] newRow = new Object[columnNames.length];
        newRow[0] = portfolioEntry.getAsset().getSymbol();
        newRow[1] = portfolioEntry.getBuyPrice();
        newRow[2] = portfolioEntry.getCurrentPrice();
        newRow[3] = portfolioEntry.getVolume();
        newRow[4] = portfolioEntry.getProfit();
        return newRow;
    }

    public void addRow(PortfolioEntry portfolioEntry) {
        Object[] newRow = convertPortfolioEntry(portfolioEntry);
        data.add(newRow);
    }

    public void insertRow(int rowIndex, PortfolioEntry portfolioEntry) {
        Object[] newRow = convertPortfolioEntry(portfolioEntry);
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

    public void deleteAllData() {
        data.clear();
    }

    public void setData(ArrayList<PortfolioEntry> portfolioEntryList) {
        data.clear();

        for (PortfolioEntry portfolioEntry : portfolioEntryList) {
            addRow(portfolioEntry);
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex] = aValue;
    }

}
