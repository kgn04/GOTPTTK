package com.example.gotpttk;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlin.Triple;

public class Graph {
    private final List<GraphNeighbours> neighboursList;
    private List<Integer> chosen;
    private int summaryLength;
    private int summaryUphills;
    private List<Integer> availableToChoose;

    public Graph() {
        neighboursList = Arrays.asList(
                new GraphNeighbours(0, 3, 0, 0),
                new GraphNeighbours(1, 2, 0, 0),
                new GraphNeighbours(1, 3, 0, 0),
                new GraphNeighbours(2, 6, 0, 0),
                new GraphNeighbours(3, 4, 0, 0),
                new GraphNeighbours(3, 8, 0, 0),
                new GraphNeighbours(4, 6, 0, 0),
                new GraphNeighbours(4, 8, 0, 0),
                new GraphNeighbours(4, 9, 0, 0),
                new GraphNeighbours(5, 6, 0, 0),
                new GraphNeighbours(5, 7, 0, 0),
                new GraphNeighbours(5, 14, 0, 0),
                new GraphNeighbours(6, 7, 0, 0),
                new GraphNeighbours(6, 15, 0, 0),
                new GraphNeighbours(7, 15, 0, 0),
                new GraphNeighbours(8, 9, 0, 0),
                new GraphNeighbours(8, 11, 0, 0),
                new GraphNeighbours(8, 12, 0, 0),
                new GraphNeighbours(9, 10, 0, 0),
                new GraphNeighbours(9, 12, 0, 0),
                new GraphNeighbours(9, 13, 0, 0),
                new GraphNeighbours(9, 14, 0, 0),
                new GraphNeighbours(11, 12, 0, 0),
                new GraphNeighbours(12, 13, 0, 0),
                new GraphNeighbours(13, 14, 0, 0),
                new GraphNeighbours(13, 17, 0, 0),
                new GraphNeighbours(14, 15, 0, 0),
                new GraphNeighbours(14, 16, 0, 0),
                new GraphNeighbours(14, 17, 0, 0),
                new GraphNeighbours(14, 18, 0, 0),
                new GraphNeighbours(15, 16, 0, 0),
                new GraphNeighbours(15, 19, 0, 0),
                new GraphNeighbours(15, 21, 0, 0),
                new GraphNeighbours(16, 18, 0, 0),
                new GraphNeighbours(17, 18, 0, 0),
                new GraphNeighbours(18, 19, 0, 0),
                new GraphNeighbours(19, 20, 0, 0),
                new GraphNeighbours(20, 21, 0, 0)
        );
        chosen = new ArrayList<>();
        summaryLength = 0;
        summaryUphills = 0;
        availableToChoose = new ArrayList<>();
        for (int i=0; i<=21; i++)
            availableToChoose.add(i);
    }

    private List<Triple<Integer, Integer, Integer>> getNeighbours(int index) {
        List<Triple<Integer, Integer, Integer>> result = new ArrayList<>();
            for (GraphNeighbours neighbours : neighboursList) {
                int neighboursIndex = -1;
                if (neighbours.getIndexes().first == index)
                    neighboursIndex = neighbours.getIndexes().second;
                else if (neighbours.getIndexes().second == index)
                    neighboursIndex = neighbours.getIndexes().first;
                if (neighboursIndex != -1)
                    result.add(new Triple<>(neighboursIndex, neighbours.getLength(), neighbours.getUphills()));
            }
        return result;
    }

    private Pair<Integer, Integer> getValuesFromIndexes(int index1, int index2) {
        for (GraphNeighbours neighbours : neighboursList) {
            if ((neighbours.getIndexes().first == index1 && neighbours.getIndexes().second == index2)
            || (neighbours.getIndexes().second == index1 && neighbours.getIndexes().first == index2))
                return new Pair<>(neighbours.getLength(), neighbours.getUphills());
        }
        return null;
    }

    public void chooseStop(int index) {
        boolean added = false;
        if (availableToChoose.contains(index)) {
            try {
                Pair<Integer, Integer> valuesToAdd = getValuesFromIndexes(chosen.get(chosen.size() - 1), index);
                summaryLength += valuesToAdd.first;
                summaryUphills += valuesToAdd.second;
            } catch (IndexOutOfBoundsException ignored) {}
            chosen.add(index);
            availableToChoose = new ArrayList<>();
            for (Triple<Integer, Integer, Integer> neighboursInfo : getNeighbours(index))
                if (!chosen.contains(neighboursInfo.component1()))
                    availableToChoose.add(neighboursInfo.component1());
            added = true;
        }
        if (chosen.get(chosen.size() - 1) == index && !added) {
            chosen.remove(chosen.size() - 1);
            try {
                Pair<Integer, Integer> valuesToSubtract = getValuesFromIndexes(chosen.get(chosen.size() - 1), index);
                summaryLength -= valuesToSubtract.first;
                summaryUphills -= valuesToSubtract.second;
                availableToChoose = new ArrayList<>();
                for (Triple<Integer, Integer, Integer> neighboursInfo : getNeighbours(chosen.get(chosen.size() - 1)))
                    if (!chosen.contains(neighboursInfo.component1()))
                        availableToChoose.add(neighboursInfo.component1());
            } catch (IndexOutOfBoundsException e) {
                availableToChoose = new ArrayList<>();
                for (int i=0; i<=21; i++)
                    availableToChoose.add(i);
            }
        }
    }

    public List<Integer> getChosen() {
        return chosen;
    }

    public int getSummaryLength() {
        return summaryLength;
    }

    public int getSummaryUphills() {
        return summaryUphills;
    }

    public List<Integer> getAvailableToChoose() {
        return availableToChoose;
    }

    public int getPoints() {
        int points = summaryLength / 1000 + summaryUphills / 100;
        if (summaryLength % 1000 > 500)
            points += 1;
        if (summaryUphills % 100 > 50)
            points += 1;
        return points;
    }
}
