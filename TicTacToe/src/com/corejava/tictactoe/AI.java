package com.corejava.tictactoe;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class AI {
    private static final char MIN_PLAYER = Symbols.X; 
    private static final char MAX_PLAYER = Symbols.O;

    private static List<Point> possibleMoves(char[][] board) {
        List<Point> result = new ArrayList<>();
        if (evaluateBoard(board) != 0) {
            return result;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Symbols.SPACE) {
                    result.add(new Point(i, j));
                }
            }
        }
        return result;
    }

    private static int evaluateBoard(char[][] board ) {
        char winner = Symbols.SPACE;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] != Symbols.SPACE) {
                if ((board[i][0] == board[i][1] && board[i][0] == board[i][2])
                        || (board[0][i] == board[1][i] && board[0][i] == board[2][i])) {
                    winner = board[i][i];
                }
            }
        }
        char valueInCenter = board[1][1];
        if (valueInCenter != Symbols.SPACE){
            if ((board[0][0] == valueInCenter && board[2][2] == valueInCenter)
                    || (board[0][2] == valueInCenter && board[2][0] == valueInCenter)) {
                winner = valueInCenter;
            }
        }
        if (winner == MAX_PLAYER) {
            return 1;
        }
        if (winner == MIN_PLAYER) {
            return -1;
        }
        return 0;
    }

    public static int[] minimax(char player, char[][] board, int alpha, int beta) {
        List<Point> nextMoves = possibleMoves(board);
        int score = -10;
        int bestRow = -1;
        int bestColumn = -1;

        if (nextMoves.isEmpty()) {
            score = evaluateBoard(board);
            return new int[] { score, bestRow, bestColumn };
        } 
        else {
            for (Point point : nextMoves) {
                board[point.x][point.y] = player;
                if (player == MAX_PLAYER) {
                    score = minimax(MIN_PLAYER, board, alpha, beta)[0];
                    if (score > alpha) {
                        alpha = score;
                        bestRow = point.x;
                        bestColumn = point.y;
                    } 
                }
                else {
                    score = minimax(MAX_PLAYER, board, alpha, beta)[0];
                    if (score < beta) {
                        beta = score;
                        bestRow = point.x;
                        bestColumn = point.y;
                    }
                }
                board[point.x][point.y] = Symbols.SPACE;

                if (alpha >= beta) {
                    break;
                }
            }
            return new int[] { (player == MAX_PLAYER) ? alpha : beta, bestRow, bestColumn };
        }
    }
}
