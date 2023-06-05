package at.moritzmusel.gwent.model;

import at.moritzmusel.gwent.network.data.GameState;

public class ReturnCard {

    private GameState gameState;
    private Card card;

    public ReturnCard(GameState gameState, Card card) {
        this.gameState = gameState;
        this.card = card;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Card getCard() {
        return card;
    }
}
