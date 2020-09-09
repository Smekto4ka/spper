package ru.bersenev.miner.jframe;

import ru.bersenev.miner.hibernate.ResultTable;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private final String[] header = {"name", "kol igr"};

    private List<ResultTable> dataList;

    public UserTableModel(List<ResultTable> userList) {
        this.dataList = userList;
    }

    @Override
    public int getRowCount() {
        return this.dataList.size();
    }

    @Override
    public int getColumnCount() {
        return this.header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return dataList.get(rowIndex).getUser().getName();
        }
        if (columnIndex == 1) {
            return dataList.get(rowIndex).getUser().getResultTables().size();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
}

