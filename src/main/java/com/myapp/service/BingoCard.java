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
        this.cells = new ArrayList<>(pieceCellNum * pieceCellNum);
        for (int i = 0; i < pieceCellNum; i++) {
            for (int j = 0; j < pieceCellNum; j++) {
                while (true) {
                    int fieldNum = random.nextInt(ballNum);
                    if (!settedNum.contains(fieldNum)) {
                        settedNum.add(fieldNum);
                        this.cells.add(new Cell(fieldNum + 1, j, i));
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
        if (0 <= vertical && vertical < pieceCellNum) {
            if (0 <= horizontal && horizontal < pieceCellNum) {
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

    Optional<Cell> findCell(int fieldNum) {
        return this.cells.stream()
                .filter(cell -> cell.getFieldNum() == fieldNum)
                .findFirst();
    }

    public void openCell(int fieldNum) {
        findCell(fieldNum).ifPresent(cell -> {
            cell.setWasOpened(true);
        });
    }

    public boolean isBingo() {
        for (int i = 0; i < pieceCellNum; i++) {
            if (isVerticalBingo(i)) return true;
            if (isHorizontalBingo(i)) return true;
        }
        if (isSlantingBingo()) return true;
        return false;
    }

    private boolean isVerticalBingo(int vertical) {
        List<Cell> openedCells = new ArrayList<>();
        for (int i = 0; i < pieceCellNum; i++) {
            getCell(vertical, i).filter(Cell::isWasOpened).ifPresent(cell -> {
                openedCells.add(cell);
            });
        }
        if (openedCells.size() >= 5) {
            return true;
        }
        return false;
    }

    private boolean isHorizontalBingo(int horizontal) {
        List<Cell> openedCells = new ArrayList<>();
        for (int i = 0; i < pieceCellNum; i++) {
            getCell(i, horizontal).filter(Cell::isWasOpened).ifPresent(cell -> {
                openedCells.add(cell);
            });
        }
        if (openedCells.size() >= 5) {
            return true;
        }
        return false;
    }

    private boolean isSlantingBingo() {
        if (getCell(0, 0).filter(Cell::isWasOpened).isPresent() &&
                getCell(1, 1).filter(Cell::isWasOpened).isPresent() &&
                getCell(2, 2).filter(Cell::isWasOpened).isPresent() &&
                getCell(3, 3).filter(Cell::isWasOpened).isPresent() &&
                getCell(4, 4).filter(Cell::isWasOpened).isPresent()
                ) {
            return true;
        } else if (getCell(4, 0).filter(Cell::isWasOpened).isPresent() &&
                getCell(3, 1).filter(Cell::isWasOpened).isPresent() &&
                getCell(2, 2).filter(Cell::isWasOpened).isPresent() &&
                getCell(1, 3).filter(Cell::isWasOpened).isPresent() &&
                getCell(0, 4).filter(Cell::isWasOpened).isPresent()
                ) {
            return true;
        }
        return false;
    }

    public void printBoardContent() {
        for (int i = 0; i < pieceCellNum; i++) {
            for (int j = 0; j < pieceCellNum; j++) {
                getCell(j, i).ifPresent(cell -> {
                    int fieldNum = cell.getFieldNum();
                    if (fieldNum < 10) {
                        System.out.print("[ " + fieldNum + "]\t");
                    } else {
                        System.out.print("[" + fieldNum + "]\t");
                    }
                });
            }
            System.out.println();
        }
        System.out.println();
    }
}
