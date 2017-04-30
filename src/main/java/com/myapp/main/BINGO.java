package com.myapp.main;

import com.myapp.service.BingoCard;
import com.myapp.service.Lot;

public class BINGO {
    public static void main(String arg[]) {
        playBingoByCLI();
    }

    private static void playBingoByCLI() {
        BingoCard bingoCard = new BingoCard();
        Lot lot = new Lot();
        System.out.println("* Game Start *");
        bingoCard.getCell(2, 2).ifPresent(cell -> {
            cell.setWasOpened(true);
        });
        bingoCard.printBoardContent();

        int castCount = 0;
        int castedNum;
        while (!bingoCard.isBingo()) {
            castedNum = lot.cast();
            bingoCard.openCell(castedNum);
            bingoCard.printBoardContent();
            castCount++;
        }
        System.out.println("** Game Clear :) **");
        System.out.println("castCount = " + castCount);
    }
}
