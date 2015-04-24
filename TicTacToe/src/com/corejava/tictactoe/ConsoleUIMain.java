package com.corejava.tictactoe;

import java.io.IOException;

import com.corejava.tictactoe.Player.PlayerType;

public class ConsoleUIMain {
    private static Player player1;
    private static Player player2;
    private static GameLogic gameLogic;
    
    // run the program without arguments if want to play with AI
    // run the program with one argument or more arguments for multiplay
    public static void main(String[] args) {
        player1 = new Player(PlayerType.HUMAN);
        if (args.length == 0) {
            player2 = new Player(PlayerType.AI);
        }
        else {
            player2 = new Player(PlayerType.HUMAN);
        }
        gameLogic = new GameLogic(player1, player2);
        try {
            ConsoleUI.play(gameLogic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
