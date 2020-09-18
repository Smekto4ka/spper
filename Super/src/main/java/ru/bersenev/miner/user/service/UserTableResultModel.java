package ru.bersenev.miner.user.service;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableResultModel extends AbstractTableModel {


    private List<Object[]> resultList;

    public UserTableResultModel(List<Object[]> userList) {
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
        return resultList.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return Column.values()[column].getStr();
    }


    private enum Column {
        NAME("Nik"),
        KOL_BOMB("kol bomb"),
        LENGTH("length"),
        TIME("time"),
        RATING("rating");

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

