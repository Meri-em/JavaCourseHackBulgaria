package com.corejava.tictactoe;

import java.awt.Point;

public class UserInput {
    public enum InputType {
        NEWGAME, QUIT, UNDO, REDO, SAVE, LOAD, NORMAL, INVALID;
    }

    private Point inputPoint;
    private final InputType type;

    public UserInput(int x, int y) {
        inputPoint = new Point(x, y);
        type = InputType.NORMAL;
    }

    public UserInput(InputType type) {
        this.type = type;
    }

    public Point getPoint() {
        return inputPoint;
    }

    public InputType getType() {
        return this.type;
    }
}