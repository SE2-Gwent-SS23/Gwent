package at.moritzmusel.gwent.network.model;


import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameState {
    //TODO last parameter @board should be the game info storage type
    public static GameState UNITIALIZED = new GameState(0, 0, 0, false, Collections.emptyList());
    private int localPlayer;
    private int playerTurn;
    private int playerWon;
    private boolean isOver;
    private List<List<Integer>> board;

    public GameState(int localPlayer, int playerTurn, int playerWon, boolean isOver, List<List<Integer>> board) {
        this.localPlayer = localPlayer;
        this.playerTurn = playerTurn;
        this.playerWon = playerWon;
        this.isOver = isOver;
        this.board = board;
    }

    public int getLocalPlayer() {
        return localPlayer;
    }

    public void setLocalPlayer(int localPlayer) {
        this.localPlayer = localPlayer;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getPlayerWon() {
        return playerWon;
    }

    public void setPlayerWon(int playerWon) {
        this.playerWon = playerWon;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public List<List<Integer>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Integer>> board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return localPlayer == gameState.localPlayer && playerTurn == gameState.playerTurn && playerWon == gameState.playerWon && isOver == gameState.isOver && board.equals(gameState.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localPlayer, playerTurn, playerWon, isOver, board);
    }

    @Override
    public String toString() {
        return "GameState{" +
                "localPlayer=" + localPlayer +
                ", playerTurn=" + playerTurn +
                ", playerWon=" + playerWon +
                ", isOver=" + isOver +
                ", board=" + board +
                '}';
    }
}