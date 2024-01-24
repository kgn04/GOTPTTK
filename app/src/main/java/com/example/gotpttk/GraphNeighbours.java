package com.example.gotpttk;

import android.util.Pair;

public class GraphNeighbours {
    private final Pair<Integer, Integer> indexes;
    private final int length;
    private final int uphills;

    public GraphNeighbours(int index1, int index2, int length, int uphills) {
        indexes = new Pair<>(index1, index2);
        this.length = length;
        this.uphills = uphills;
    }

    public Pair<Integer, Integer> getIndexes() {
        return indexes;
    }

    public int getLength() {
        return length;
    }

    public Integer getUphills() {
        return uphills;
    }
}
