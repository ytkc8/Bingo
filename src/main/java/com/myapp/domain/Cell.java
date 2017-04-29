package com.myapp.domain;

public class Cell {
    private int fieldNum;
    private int vertical;
    private int horizontal;
    boolean wasOpened;

    public Cell(int fieldNum, int vertical, int horizontal) {
        this.fieldNum = fieldNum;
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.wasOpened = false;
    }

    public int getFieldNum() {
        return fieldNum;
    }

    public void setFieldNum(int fieldNum) {
        this.fieldNum = fieldNum;
    }

    public int getVertical() {
        return vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public boolean isWasOpened() {
        return wasOpened;
    }

    public void setWasOpened(boolean wasOpened) {
        this.wasOpened = wasOpened;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (fieldNum != cell.fieldNum) return false;
        if (vertical != cell.vertical) return false;
        if (horizontal != cell.horizontal) return false;
        return wasOpened == cell.wasOpened;
    }

    @Override
    public int hashCode() {
        int result = fieldNum;
        result = 31 * result + vertical;
        result = 31 * result + horizontal;
        result = 31 * result + (wasOpened ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "fieldNum=" + fieldNum +
                ", vertical=" + vertical +
                ", horizontal=" + horizontal +
                ", wasOpened=" + wasOpened +
                '}';
    }
}
