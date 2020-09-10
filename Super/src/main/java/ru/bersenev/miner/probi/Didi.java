package ru.bersenev.miner.probi;

public enum Didi {
    VIKI {
        @Override
        int mir(int a, int b) {
            return a+b;
        }
    },


    MIMI {
        @Override
        int mir(int a, int b) {
            return 0;
        }
    },
    KIKI{
        @Override
        int mir(int a, int b) {
            return 22;
        }
    };
    abstract int mir(int a , int b);

    }