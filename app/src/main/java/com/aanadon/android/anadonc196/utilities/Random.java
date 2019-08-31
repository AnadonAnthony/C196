package com.aanadon.android.anadonc196.utilities;

public class Random {

    public static final java.util.Random _Rand  = new java.util.Random(System.currentTimeMillis());

    private static final Random _Instance = new Random();

    public static Random getInstance() {
        return _Instance;
    }

    private Random() {
    }
}
