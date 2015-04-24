package com.corejava.tictactoe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

import com.corejava.tictactoe.Player.PlayerType;

public class GameLogic implements java.io.Serializable {
    public static final int BOARD_LENGTH = 3;
    private static final long serialVersionUID = -6377528631977534670L;
    private static final String GAMELOGIC_FILE_NAME_AI = "gameLogic_AI.ser";
    private static final String GAMELOGIC_FILE_NAME_MULTIPLY = "gameLogic_multiplay.ser";
    
    private  final Player player1;
    private  final Player player2;
    private Player currentPlayer;
    private final char[][] board;
    private GameState state;
    private String gameLogicFileName;
    
    public Stack<Memento> undoStack = new Stack<>();
    public Stack<Memento> redoStack = new Stack<>();
    
    public GameLogic(Player aPlayer1, Player aPlayer2) {
        this.board = (new char[BOARD_LENGTH][BOARD_LENGTH]);
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                board[i][j] = Symbols.SPACE;
            }
        }
        player1 = aPlayer1;
        player2 = aPlayer2;
        player1.setSymbol(Symbols.X);
        player2.setSymbol(Symbols.O);
        
        currentPlayer = player1;
        state = GameState.IN_PROGRESS;
        obtainFileName();
    }
    
    public GameLogic(GameLogic aGameLogic) {
        board = (new char[BOARD_LENGTH][BOARD_LENGTH]);
        copySourceToDestination(aGameLogic.board, board);
        player1 = aGameLogic.player1;
        player2 = aGameLogic.player2;
        currentPlayer = aGameLogic.currentPlayer;
        state = aGameLogic.getState();
        obtainFileName();
        
    }
    
    public char[][] getBoard() {
        return board;
    }
    
    public void setBoard(char[][] aBoard) {
        copySourceToDestination(aBoard, getBoard());
    }
    
    public GameState getState() {
        return state;
    }
    
    private boolean isInProgress() {
        return getState().equals(GameState.IN_PROGRESS);
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public String playHuman(UserInput userInput) {
        switch(userInput.getType()) {
            case NEWGAME: newGame(); break;
            case QUIT: return "The game is quitted"; 
            case UNDO: 
                if (!undo()) {
                    return "Can not undo!";
                }
                if (player2.getPlayerType().equals(PlayerType.AI)) {
                    undo();
                }
                break;
            case REDO:
                if (!redo()) {
                    return "Can not redo!";
                }
                if (player2.getPlayerType().equals(PlayerType.AI)) {
                    redo();
                }
                break;
            case SAVE:
                serialize();
                return "The game state is saved.";
            case LOAD:
                GameLogic temp = deserialize();
                if (temp != null) {
                    setBoard(temp.board);
                    currentPlayer = temp.currentPlayer;
                    state = temp.state;
                    break;
                }
                else {
                    return "There is no saved game state.";
                }
            case NORMAL:
                int row = userInput.getPoint().x;
                int column = userInput.getPoint().y;
                if (!isInBoard(row, column)) {
                    return "The coordinates are out of the board! Insert new coordinates!";
                }
                if (!isEmpty(row, column)) {
                    return "This field is already marked! Choose other field.";
                }
                playCoordinates(row, column);
                break;   
            default:
                return "Invalid output";
        }
        return null;
    }
    
    public void playAI() {
        int[] result = AI.minimax(currentPlayer.getSymbol(), getBoard(), Integer.MIN_VALUE, Integer.MAX_VALUE);
        playCoordinates(result[1], result[2]);
    }
    
    private String newGame() {
        for (int i = 0; i < BOARD_LENGTH; i++) {
            for (int j = 0; j < BOARD_LENGTH; j++) {
                getBoard()[i][j] = Symbols.SPACE;
            }
        }
        undoStack.clear();
        redoStack.clear();
        return null;
    }

    private boolean undo() {
        if(canUndo()) { 
            changePlayer();
            char[][] boardForRedo = (new char[BOARD_LENGTH][BOARD_LENGTH]);

            redoStack.push(new Memento(copiedBoard(getBoard(), boardForRedo)));
            copySourceToDestination(undoStack.pop().getBoardState(), getBoard());
            return true;
        }
        return false;
    }
    
    private boolean redo() {
        if (canRedo()) {
            changePlayer();
            char[][] boardForUndo = (new char[BOARD_LENGTH][BOARD_LENGTH]);
            undoStack.push(new Memento(copiedBoard(getBoard(), boardForUndo)));
            copySourceToDestination(redoStack.pop().getBoardState(), getBoard());
            
            return true;
        }
        return false;
    }
    
    private void serialize() {
        try (
            FileOutputStream outputStream = new FileOutputStream(gameLogicFileName);
            ObjectOutputStream out = new ObjectOutputStream(outputStream)) {
            out.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private GameLogic deserialize()  {
        try (FileInputStream inputStream = new FileInputStream(gameLogicFileName);
                ObjectInputStream in = new ObjectInputStream(inputStream)) {
            undoStack.clear();
            redoStack.clear();
            return (GameLogic) in.readObject();
        }catch (FileNotFoundException e) {
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void playCoordinates(int i, int j) {
        if(validateMove(i, j)){
            redoStack.clear();
            char[][] boardForUndo = (new char[BOARD_LENGTH][BOARD_LENGTH]);
            undoStack.push(new Memento(copiedBoard(getBoard(), boardForUndo)));
            getBoard()[i][j] = currentPlayer.getSymbol();
            outcome();
            changePlayer();
        }
    }
    
    private boolean validateMove(int i, int j) {
        return isInBoard(i, j) && isInProgress() && isEmpty(i, j);
    }
    
    private void outcome(){
        if(checkColumns() || checkRows() || checkMainDiagonal() || checkSecondDiagonal()){
            if(currentPlayer.equals(player1))
                state = GameState.PLAYER1_WINS;
            else 
                state =  GameState.PLAYER2_WINS;
        }
        if(hasNoEmptyUnits())
            state =  GameState.TIE;      
    }
    
    private void changePlayer() {
        currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1; 
    }
    
    private boolean isInBoard(int i, int j) {
        return i > -1 && j > -1 && i < BOARD_LENGTH && j < BOARD_LENGTH;
    }
    
    private boolean checkRows(){
        for(int i = 0; i < BOARD_LENGTH; i++){
            char value = getBoard()[i][0];
            if (value != Symbols.SPACE && getBoard()[i][1] == value && getBoard()[i][2] == value)
                return true;
        }
        return false;
    }
    
    private boolean checkColumns(){
        for(int j = 0; j < BOARD_LENGTH; j++){
            char value = getBoard()[0][j];
            if (value != Symbols.SPACE  && getBoard()[1][j] == value  && getBoard()[2][j] == value)
                return true;
        }
        return false;
    }
    
    private boolean checkMainDiagonal(){
        return getBoard()[0][0] != Symbols.SPACE && getBoard()[0][0] == getBoard()[1][1] &&
                                                    getBoard()[1][1] == getBoard()[2][2]; 
    }
    
    private boolean checkSecondDiagonal() {
        return getBoard()[0][2] != Symbols.SPACE && getBoard()[0][2] == getBoard()[1][1] &&
                                                getBoard()[1][1] == getBoard()[2][0];
    }
    
    private boolean hasNoEmptyUnits(){
        for(char[] row: getBoard()){
            for(char element: row){
                if(element == Symbols.SPACE)
                    return false;
            }
        }
        return true;
    }
    
    private boolean isEmpty(int i, int j) {
        return getBoard()[i][j] == Symbols.SPACE;
    }
    
    private char[][] copiedBoard(char[][] source, char[][] destination) {
        for (int y = 0; y < BOARD_LENGTH; y++) {
            for (int x = 0; x < BOARD_LENGTH; x++) {
                destination[y][x] = source[y][x];
            }
        }
        return destination;
    }
    
    private  void copySourceToDestination(char[][] source, char[][] destination) {
        for (int y = 0; y < BOARD_LENGTH; y++) {
            for (int x = 0; x < BOARD_LENGTH; x++) {
                destination[y][x] = source[y][x];
            }
        }
    }
    
    private class Memento implements java.io.Serializable{
      
        private static final long serialVersionUID = 3247332231451472921L;
        private final char[][] boardState;
        
        public Memento(char[][] boardState) {
            this.boardState = boardState;
        }
        
        public char[][] getBoardState() {
            return boardState;
        }
    }
    
    public boolean canUndo() {
        return !undoStack.isEmpty() && isInProgress();
    }
    
    public boolean canRedo() {
        return !redoStack.isEmpty() && isInProgress();
    }
    
    public boolean canLoad() {
        return Files.exists(Paths.get(gameLogicFileName));
    }
    
    public void obtainFileName() {
        if (player2.getPlayerType().equals(PlayerType.AI)) {
            gameLogicFileName = GAMELOGIC_FILE_NAME_AI;
        }else {
            gameLogicFileName = GAMELOGIC_FILE_NAME_MULTIPLY;
        }
    }
}