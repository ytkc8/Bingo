package com.myapp.service;

import com.myapp.domain.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BingoCard {
    private static final int ballNum = 75;
    private static final int pieceCellNum = 5;
    private List<Cell> cells;

    public BingoCard() {
        Random random = new Random();
        List<Integer> settedNum = new ArrayList<>();
        cells = new ArrayList<>(pieceCellNum * pieceCellNum);
        for (int i = 0; i < pieceCellNum; i ++) {
            for (int j = 0; j < pieceCellNum; j++) {
                while (true) {
                    int fieldNum = random.nextInt(ballNum);
                    if (!settedNum.contains(fieldNum)) {
                        settedNum.add(fieldNum);
                        cells.add(new Cell(fieldNum + 1, j, i));
                        break;
                    }
                }
            }
        }
    }

    public List<Cell> getCells() {
        return cells;
    }

    boolean isExist(int vertical, int horizontal) {
        if (vertical >= 0 && pieceCellNum > vertical) {
            if (horizontal >= 0 && pieceCellNum > horizontal) {
                return true;
            }
        }
        return false;
    }

    Optional<Cell> getCell(int vertical, int horizontal) {
        if (isExist(vertical, horizontal)) {
            return Optional.of(this.cells.get(horizontal * pieceCellNum + vertical));
        }
        return Optional.empty();
    }

    public void printBoardContent() {
        for (int i = 0; i < pieceCellNum; i++) {
            for (int j = 0; j < pieceCellNum; j++) {
                getCell(j, i).ifPresent(cell -> {
                    System.out.print("[" + cell.getFieldNum() + "]\t");
                });
            }
            System.out.println();
        }
        System.out.println();
    }
}
