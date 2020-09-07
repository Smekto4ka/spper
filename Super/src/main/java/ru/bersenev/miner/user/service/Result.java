package ru.bersenev.miner.user.service;

public class Result {
    private int id;
    private int length;
    private int kolBomb;
    private long time;
    private String user;


    public Result(int id,int length, int kolBomb, long time, String user){
        this.id = id;
        this.length=length;
        this.kolBomb = kolBomb;
        this.time = time;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public int getKolBomb() {
        return kolBomb;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
