package ru.bersenev.miner.probi;

public class Probi {
    public static void main(String[] args) {
        Didi didi = Didi.VIKI;
        System.out.println(didi.mir(20,20));

        System.out.println(didi.ordinal());
        System.out.println(Didi.values()[0].mir(0,0));
        System.out.println(Didi.valueOf("VIKI"));
        System.out.println(didi.values());
    }

}
