package ru.bersenev.miner.jframe;

public class CastomButton extends javax.swing.JButton {
    private int width; // schirina
    private int height; // visota

    public CastomButton (int height , int width ){
        this.height = height;
        this.width = width;
    }
    public int getCastomWidth(){
        return width;
    }
    public int getCastomHeight(){
        return height;
    }
}
