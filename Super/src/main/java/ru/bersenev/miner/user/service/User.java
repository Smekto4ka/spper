package ru.bersenev.miner.user.service;

import java.util.List;

public class User {
    private String name;
    private int length;
    private int kolBomb;
    private int percent;
    private int nomRadioButton;
    private List<Result> reselt;

    public User(String name, int length, int kolBomb, int percent, int nomRadioButton,List<Result> reselt) {
        this.length = length;
        this.kolBomb = kolBomb;
        this.name = name;
        this.percent = percent;
        this.nomRadioButton = nomRadioButton;
        this.reselt = reselt;
    }

    public List<Result> getReseltTables() {
        return reselt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getKolBomb() {
        return kolBomb;
    }

    public void setKolBomb(int kolBomb) {
        this.kolBomb = kolBomb;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getNomRadioButton() {
        return nomRadioButton;
    }

    public void setNomRadioButton(int nomRadioButton) {
        this.nomRadioButton = nomRadioButton;
    }



    public void setReselt(List<Result> reselt) {
        this.reselt = reselt;
    }
    public long getTime(){

        for(Result res : reselt){
            if (res.getKolBomb() == kolBomb && res.getLength() == length){
                return res.getTime();
            }
        }
        return 0;
    }
}
