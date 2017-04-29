package com.myapp.main;

import com.myapp.service.BingoCard;

public class BINGO {
    public static void main(String arg[]) {
        playBingoByCLI();
    }

    private static void playBingoByCLI() {
        BingoCard bingoCard = new BingoCard();
        bingoCard.printBoardContent();
    }
}
