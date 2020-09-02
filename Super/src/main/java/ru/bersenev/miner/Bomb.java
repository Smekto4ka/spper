package ru.bersenev.miner;

public class Bomb {
    private int width; // schirina
    private int height; // visota

    public Bomb (int height , int width){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
