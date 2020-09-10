package ru.bersenev.miner.jframe;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

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
        return Column.values().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return  resultList.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return Column.values()[column].getStr();
    }


    private enum Column {
        NAME("ник"),
        KOL_POB("кол побед"),
        TIME("время");

        private String str;

        Column(String str) {
            this.str = str;

        }

        public String getStr() {
            return str;

        }

        @Override
        public String toString() {
            return str;
        }
    }

}



