package com.myapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lot {
    private static final int ballNum = 75;
    private List<Integer> dequeue;
    private Random random;

    public Lot() {
        dequeue = new ArrayList<>();
        random = new Random();
    }

    public List<Integer> getDequeue() {
        return dequeue;
    }

    public int cast() {
        int leftNum;
        while (true) {
            leftNum = random.nextInt(ballNum);
            if (!dequeue.contains(leftNum)) {
                dequeue.add(leftNum);
                return leftNum;
            }
        }
    }
}
