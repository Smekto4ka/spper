package ru.bersenev.miner.user.service;

public class Result {
    private int id;
    private int length;
    private int kolBomb;
    private long time;
    private User user;



    public Result(int id,int length, int kolBomb, long time, User user){
        this.id = id;
        this.length=length;
        this.kolBomb = kolBomb;
        this.time = time;
        this.user = user;
    }
    public Result(int id,int length, int kolBomb, long time){
        this.id = id;
        this.length=length;
        this.kolBomb = kolBomb;
        this.time = time;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
