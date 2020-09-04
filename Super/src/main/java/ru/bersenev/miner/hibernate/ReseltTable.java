package ru.bersenev.miner.hibernate;

import javax.persistence.*;
// long start = System.currentTimeMillis();
@Entity
@Table(name = "result")
public class ReseltTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column ( name = "length")
    private int length;
    @Column (name = "kolBomb")
    private int kolBomb;
    @Column (name = "time")
    private long time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_name")
    private UsersTable user;

    public ReseltTable(){}
    public ReseltTable(int length, int kolBomb, long time){
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

    public void setLength(int length) {
        this.length = length;
    }

    public int getKolBomb() {
        return kolBomb;
    }

    public void setKolBomb(int kolBomb) {
        this.kolBomb = kolBomb;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public UsersTable getUser() {
        return user;
    }

    public void setUser(UsersTable user) {
        this.user = user;
    }
    @Override
    public String toString (){
        return user.getName() + " pri length " + length + " i kol bomb" + kolBomb + " time : " + time;
    }
}
