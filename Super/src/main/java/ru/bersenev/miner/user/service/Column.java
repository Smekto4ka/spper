package ru.bersenev.miner.user.service;

public enum Column {
    NAME("mame"),
    KOL_POB("kol pob"),
    TIME("time");

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
