package ru.bersenev.miner.jframe;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private final String[] header = {"name"," kolPob " , "time"};

    private List<Object[]> resultList;

    public UserTableModel(List<Object[]> userList) {
        this.resultList = userList;
    }

    @Override
    public int getRowCount() {
        return this.resultList.size();
    }

    @Override
    public int getColumnCount() {
        return this.header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return  resultList.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
}

