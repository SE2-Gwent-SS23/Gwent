package at.moritzmusel.gwent.network.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.moritzmusel.gwent.model.Card;

// FIXME remove unused methods for brevity
public class GameState implements Serializable {
    //TODO last parameter @board should be the game info storage type
    public static GameState UNITIALIZED = new GameState(0, 0, 0, false);
    private final int localPlayer;
    private final int playerTurn;
    private final int playerWon;
    private final boolean isOver;

    public GameState(int localPlayer, int playerTurn, int playerWon, boolean isOver){
        this.localPlayer = localPlayer;
        this.playerTurn = playerTurn;
        this.playerWon = playerWon;
        this.isOver = isOver;
    }

    String myDeck;
    String opponentDeck;
    List<Card> myHand;
    List<Card> opponentHand;
    List<Card> myGrave;
    List<Card> opponentGrave;
    List<Card> weather;
    List<Card> myClose; // 3. Reihe
    Boolean myWeatherClose;
    List<Card> myRanged; // 4. Reihe
    Boolean myWeatherRanged;
    List<Card> opponentClose; // 2. Reihe
    Boolean opponentWeatherClose;
    List<Card> opponentRanged; // 1. Reihe
    Boolean opponentWeatherRanged;

    Card myLeader;
    Boolean usedMyLeader;
    Card opponentLeader;
    Boolean usedOpponentLeader;

    public int calculateMyPoints() {
        int sum = 0;
        for (Card c : this.myClose) {
            sum += c.getStrength();
        }
        for (Card c : this.myRanged) {
            sum += c.getStrength();
        }
        return sum;
    }

    public int calculateOpponentPoints() {
        int sum = 0;
        for (Card c : this.opponentClose) {
            sum += c.getStrength();
        }
        for (Card c : this.opponentRanged) {
            sum += c.getStrength();
        }
        return sum;
    }

    public void applySun() {
        this.weather.clear();
    }

    public void swapPlayer() {
        List<Card> tempOpponentHand = new ArrayList<>(this.opponentHand);
        List<Card> tempOpponentGrave = new ArrayList<>(this.opponentGrave);
        List<Card> tempOpponentClose = new ArrayList<>(this.opponentClose);
        List<Card> tempOpponentRanged = new ArrayList<>(this.opponentRanged);

        this.opponentHand = new ArrayList<>(this.myHand);
        this.opponentGrave = new ArrayList<>(this.myGrave);
        this.opponentClose = new ArrayList<>(this.myClose);
        this.opponentRanged = new ArrayList<>(this.myRanged);

        this.myHand = new ArrayList<>(tempOpponentHand);
        this.myGrave = new ArrayList<>(tempOpponentGrave);
        this.myClose = new ArrayList<>(tempOpponentClose);
        this.myRanged = new ArrayList<>(tempOpponentRanged);

        String tempOpponentDeck = this.opponentDeck;
        Boolean tempOpponentWeatherClose = this.opponentWeatherClose;
        Boolean tempOpponentWeatherRanged = this.opponentWeatherRanged;
        Card tempOpponentLeader = this.opponentLeader;
        Boolean tempUsedOpponentLeader = this.usedOpponentLeader;

        this.opponentDeck = this.myDeck;
        this.opponentWeatherClose = this.myWeatherClose;
        this.opponentWeatherRanged = this.myWeatherRanged;
        this.opponentLeader = this.myLeader;
        this.usedOpponentLeader = this.usedMyLeader;

        this.myDeck = tempOpponentDeck;
        this.myWeatherClose = tempOpponentWeatherClose;
        this.myWeatherRanged = tempOpponentWeatherRanged;
        this.myLeader = tempOpponentLeader;
        this.usedMyLeader = tempUsedOpponentLeader;

    }


    public void addToMyHand(Card card) {
        this.myHand.add(card);
    }

    public void removeFromMyHand(Card card) {
        this.myHand.remove(card);
    }

    public void addToOpponentHand(Card card) {
        this.opponentHand.add(card);
    }

    public void removeFromOpponentHand(Card card) {
        this.opponentHand.remove(card);
    }

    public void addToMyGrave(Card card) {
        this.myGrave.add(card);
    }

    public void removeFromMyGrave(Card card) {
        this.myGrave.remove(card);
    }

    public void addOpponentToGrave(Card card) {
        this.opponentGrave.add(card);
    }

    public void removeFromOpponentGrave(Card card) {
        this.opponentGrave.remove(card);
    }

    public void addToWeather(Card card) {
        this.weather.add(card);
    }

    public void removeFromWeather(Card card) {
        this.weather.remove(card);
    }

    public void addToMyClose(Card card) {
        this.myClose.add(card);
    }

    public void removeFromMyClose(Card card) {
        this.myClose.remove(card);
    }

    public void addToMyRanged(Card card) {
        this.myRanged.add(card);
    }

    public void removeFromMyRanged(Card card) {
        this.myRanged.remove(card);
    }

    public void addToOpponentClose(Card card) {
        this.opponentClose.add(card);
    }

    public void removeFromOpponentClose(Card card) {
        this.opponentClose.remove(card);
    }

    public void addToOpponentRanged(Card card) {
        this.opponentRanged.add(card);
    }

    public void removeFromOpponentRanged(Card card) {
        this.opponentRanged.remove(card);
    }

    public String getMyDeck() {
        return myDeck;
    }

    public void setMyDeck(String myDeck) {
        this.myDeck = myDeck;
    }

    public String getOpponentDeck() {
        return opponentDeck;
    }

    public void setOpponentDeck(String opponentDeck) {
        this.opponentDeck = opponentDeck;
    }

    public List<Card> getMyHand() {
        return myHand;
    }

    public void setMyHand(List<Card> myHand) {
        this.myHand = myHand;
    }

    public List<Card> getOpponentHand() {
        return opponentHand;
    }

    public void setOpponentHand(List<Card> opponentHand) {
        this.opponentHand = opponentHand;
    }

    public List<Card> getMyGrave() {
        return myGrave;
    }

    public void setMyGrave(List<Card> myGrave) {
        this.myGrave = myGrave;
    }

    public List<Card> getOpponentGrave() {
        return opponentGrave;
    }

    public void setOpponentGrave(List<Card> opponentGrave) {
        this.opponentGrave = opponentGrave;
    }

    public List<Card> getWeather() {
        return weather;
    }

    public void setWeather(List<Card> weather) {
        this.weather = weather;
    }

    public List<Card> getMyClose() {
        return myClose;
    }

    public void setMyClose(List<Card> myClose) {
        this.myClose = myClose;
    }

    public Boolean getMyWeatherClose() {
        return myWeatherClose;
    }

    public void setMyWeatherClose(Boolean myWeatherClose) {
        this.myWeatherClose = myWeatherClose;
    }

    public List<Card> getMyRanged() {
        return myRanged;
    }

    public void setMyRanged(List<Card> myRanged) {
        this.myRanged = myRanged;
    }

    public Boolean getMyWeatherRanged() {
        return myWeatherRanged;
    }

    public void setMyWeatherRanged(Boolean myWeatherRanged) {
        this.myWeatherRanged = myWeatherRanged;
    }

    public List<Card> getOpponentClose() {
        return opponentClose;
    }

    public void setOpponentClose(List<Card> opponentClose) {
        this.opponentClose = opponentClose;
    }

    public Boolean getOpponentWeatherClose() {
        return opponentWeatherClose;
    }

    public void setOpponentWeatherClose(Boolean opponentWeatherClose) {
        this.opponentWeatherClose = opponentWeatherClose;
    }

    public List<Card> getOpponentRanged() {
        return opponentRanged;
    }

    public void setOpponentRanged(List<Card> opponentRanged) {
        this.opponentRanged = opponentRanged;
    }

    public Boolean getOpponentWeatherRanged() {
        return opponentWeatherRanged;
    }

    public void setOpponentWeatherRanged(Boolean opponentWeatherRanged) {
        this.opponentWeatherRanged = opponentWeatherRanged;
    }

    public Card getMyLeader() {
        return myLeader;
    }

    public void setMyLeader(Card myLeader) {
        this.myLeader = myLeader;
    }

    public Boolean getUsedMyLeader() {
        return usedMyLeader;
    }

    public void setUsedMyLeader(Boolean usedMyLeader) {
        this.usedMyLeader = usedMyLeader;
    }

    public Card getOpponentLeader() {
        return opponentLeader;
    }

    public void setOpponentLeader(Card opponentLeader) {
        this.opponentLeader = opponentLeader;
    }

    public Boolean getUsedOpponentLeader() {
        return usedOpponentLeader;
    }

    public void setUsedOpponentLeader(Boolean usedOpponentLeader) {
        this.usedOpponentLeader = usedOpponentLeader;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "localPlayer=" + localPlayer +
                ", playerTurn=" + playerTurn +
                ", playerWon=" + playerWon +
                ", isOver=" + isOver +
                ", myDeck='" + myDeck + '\'' +
                ", opponentDeck='" + opponentDeck + '\'' +
                ", myHand=" + myHand +
                ", opponentHand=" + opponentHand +
                ", myGrave=" + myGrave +
                ", opponentGrave=" + opponentGrave +
                ", weather=" + weather +
                ", myClose=" + myClose +
                ", myWeatherClose=" + myWeatherClose +
                ", myRanged=" + myRanged +
                ", myWeatherRanged=" + myWeatherRanged +
                ", opponentClose=" + opponentClose +
                ", opponentWeatherClose=" + opponentWeatherClose +
                ", opponentRanged=" + opponentRanged +
                ", opponentWeatherRanged=" + opponentWeatherRanged +
                ", myLeader=" + myLeader +
                ", usedMyLeader=" + usedMyLeader +
                ", opponentLeader=" + opponentLeader +
                ", usedOpponentLeader=" + usedOpponentLeader +
                '}';
    }
}
