package com.corejava.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.corejava.tictactoe.Player.PlayerType;
import com.corejava.tictactoe.UserInput.InputType;

public class ConsoleUI {
    
    public static void play(GameLogic gameLogic) throws IOException {
        welcomeMessage();
        do {
            visualizeBoard(gameLogic.getBoard());
            if (gameLogic.getCurrentPlayer().getPlayerType().equals(PlayerType.AI)) {
                gameLogic.playAI();
                System.out.println();
            }
            else {
            UserInput userInput = fetchInput();
            String message = gameLogic.playHuman(userInput);
            if (message != null) {
                System.out.println(message);
            }
            if (userInput.getType().equals(UserInput.InputType.QUIT)) {
                return;
            }
            }
        } while (gameLogic.getState().equals(GameState.IN_PROGRESS));
        
        visualizeBoard(gameLogic.getBoard());
        System.out.println(gameLogic.getState().getMessage());
    }
    
    
    private static void visualizeBoard(char[][] board) {
        for (char[] row: board) {
            for (char element: row) {
                System.out.print("|" + element);
            }
            System.out.print("|" + System.lineSeparator());
        }
    }
    
    private static void welcomeMessage() {
        System.out.printf("To insert coordinates insert \"row column\". "
                + "For example: \"0 0\" for the first field.%n"
                + "Insert:%n"
                + "u - undo, r - redo,%n"
                + "n - start a new game, q - quit the game,%n"
                + "s - save the game if you want to play it later, l - load saved game%n");
    }
    
    public static UserInput fetchInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            input = bufferedReader.readLine();
            if (input.equals("n")) {
                return new UserInput(InputType.NEWGAME);
            }
            if (input.equals("q")) {
                return new UserInput(InputType.QUIT);
            }
            if (input.equals("s")) {
                return new UserInput(InputType.SAVE);
            }
            if (input.equals("l")) {
                return new UserInput(InputType.LOAD);
            }
            if (input.equals("u")) {
                return new UserInput(InputType.UNDO);
            }
            if (input.equals("r")) {
                return new UserInput(InputType.REDO);
            }
            if (checkInput(input)) {
                int i = Integer.parseInt(input.split(" ")[0]);
                int j = Integer.parseInt(input.split(" ")[1]);
                return new UserInput(i, j);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new UserInput(InputType.INVALID);
    }

    private static boolean checkInput(String input) {
        return input.matches("^\\d+ \\d+$");
    }
}
