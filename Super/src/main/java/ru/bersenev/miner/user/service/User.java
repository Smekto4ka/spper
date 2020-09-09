package ru.bersenev.miner.user.service;

import java.util.List;

public class User {
    private String name;
    private int length;
    private int kolBomb;
    private int percent;
    private int nomRadioButton;
    private List<Result> result;

    public User(String name, int length, int kolBomb, int percent, int nomRadioButton,List<Result> result) {
        this.length = length;
        this.kolBomb = kolBomb;
        this.name = name;
        this.percent = percent;
        this.nomRadioButton = nomRadioButton;
        this.result = result;
    }
    public User(String name, int length, int kolBomb, int percent, int nomRadioButton) {
        this.length = length;
        this.kolBomb = kolBomb;
        this.name = name;
        this.percent = percent;
        this.nomRadioButton = nomRadioButton;

    }

    public List<Result> getResultTables() {
        return result;
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



    public void setResult(List<Result> result) {
        this.result = result;
    }
    public long getTime(){

        for(Result res : result){
            if (res.getKolBomb() == kolBomb && res.getLength() == length){
                return res.getTime();
            }
        }
        return 0;
    }
}
