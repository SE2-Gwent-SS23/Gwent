package at.moritzmusel.gwent.network.model;

import java.util.ArrayList;
import java.util.List;

public class GwentGame {
    //TODO change logic
    private static int players = 2;
    private static int boardSize = 2;
    private static int winningCount = 2;
    public int playerTurn = 1;
    public int playerWon = 0;
    public boolean isOver = false;
    public List<Integer> board = new ArrayList<>(boardSize);

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
    public void play(int player, int[] position) {
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