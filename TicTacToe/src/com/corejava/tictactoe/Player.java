package com.corejava.tictactoe;

public class Player implements java.io.Serializable{
    private static final long serialVersionUID = -487255151798231086L;
    public enum PlayerType {
        HUMAN, AI;
    }
    private char symbol;
    private PlayerType playerType;
    
    public Player(PlayerType playerType) {
        this.playerType = playerType;
    }
    
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public PlayerType getPlayerType() {
        return playerType;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Player other = (Player) obj;
        return getSymbol() == other.getSymbol();
    }   
}
