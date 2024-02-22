package org.example;

public class Calculator {
    private long n = 0;

    public long add(long x) {
        n = n + x;
        return n;
    }

    public long sub(long x) {
        n = n - x;
        return n;
    }

    public long divide(long x){
        if(x==0){
            throw new IllegalArgumentException();
        }
        return n/x;
    }
}
