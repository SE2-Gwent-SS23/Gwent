package at.moritzmusel.gwent.network.model;

import java.util.ArrayList;
import java.util.List;

import at.moritzmusel.gwent.network.data.GameState;

public class GwentGame {
    //TODO change logic
    private static final int players = 2;
    private static final int boardSize = 2;
    private static final int winningCount = 2;
    public int playerTurn = 1;
    public int playerWon = 0;
    public boolean isOver = false;
    public List<GameState> gameStates = new ArrayList<>();

    public GwentGame() {
    }

    private void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    private void setPlayerWon(int playerWon) {
        this.playerWon = playerWon;
    }

    private void setOver(boolean over) {
        isOver = over;
    }

    //TODO change 2nd parameter and implement body
    public void play(int player, GameState gameState) {
        if (player < 1 || player > players) throw new IllegalArgumentException("Invalid player");
        if (player != playerTurn) throw new IllegalArgumentException("Wrong turn");
        if (isOver) throw new IllegalArgumentException("Game over");

    }

    //TODO logic for winning
    private boolean hasPlayerWon(int player) {
        if (player < 1 || player > players) return false;
        return false;
    }
}