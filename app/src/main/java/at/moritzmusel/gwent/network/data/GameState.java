package at.moritzmusel.gwent.network.data;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import at.moritzmusel.gwent.model.Card;
import at.moritzmusel.gwent.model.CardGenerator;

public class GameState implements Serializable {
    //TODO last parameter @board should be the game info storage type
    public static GameState UNITIALIZED = new GameState();
    private static final String TAG = "GameViewActivity";
    private String myDeck;
    private String opponentDeck;
    private List<Card> myHand;
    private List<Card> opponentHand;
    private List<Card> myGrave;
    private List<Card> opponentGrave;
    private List<Card> weather;
    private List<Card> myClose; // 3. Reihe
    private List<Card> myRanged; // 4. Reihe
    private List<Card> opponentClose; // 2. Reihe
    private List<Card> opponentRanged; // 1. Reihe
    private boolean redrawPhase = true;
    private boolean cheated = false;
    private boolean opponentCheated = false;

    private Card myLeader;
    private Boolean usedMyLeader;
    private Card opponentLeader;
    private Boolean usedOpponentLeader;
    private int[] myRoundCounter;
    private int[] opponentRoundCounter;
    private List<Card> allCards;
    private boolean myPassed;
    private boolean opponentPassed;
    private int roundTracker;

    public void sendToMyGrave() {
        this.myGrave.addAll(this.myClose);
        this.myGrave.addAll(this.myRanged);
        this.myClose.clear();
        this.myRanged.clear();
    }

    public void sendToOpponentGrave() {
        this.opponentGrave.addAll(this.opponentClose);
        this.opponentGrave.addAll(this.opponentRanged);
        this.opponentClose.clear();
        this.opponentRanged.clear();
    }

    public int calculateMyWins(int[] opponentArray) {
        int wins = 0;
        for (int i = 0; this.myRoundCounter.length > i; i++) {
            if (this.myRoundCounter[i] > opponentArray[i]) {
                wins++;
            }
        }
        return wins;
    }

    public void hasCards() {
        if (this.myHand.size() == 0) {
            this.myPassed = true;
        }
    }




    public boolean determineWinner(Boolean[] array) {
        int counter = 0;
        for (boolean b : array) {
            if (b) {
                counter++;
            }
        }
        if (counter >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public void initGameState() throws JSONException, IOException {
        this.myDeck = "";
        this.opponentDeck = "";
        this.weather = new ArrayList<>();
        this.myLeader = new Card();
        this.opponentLeader = new Card();
        this.usedMyLeader = false;
        this.usedOpponentLeader = false;
        this.myRoundCounter = new int[]{0, 0, 0};
        this.opponentRoundCounter = new int[]{0, 0, 0};
        this.cheated = false;
        this.opponentCheated = false;

        // myHand
        this.myHand = new ArrayList<>();
        this.allCards = new ArrayList<>();

        // Ranged
        this.myRanged = new ArrayList<>();
        this.opponentRanged = new ArrayList<>();

        // Close
        this.myClose = new ArrayList<>();
        this.opponentClose = new ArrayList<>();

        // opponentHand
        this.opponentHand = new ArrayList<>();

        // Grave
        this.myGrave = new ArrayList<>();
        this.opponentGrave = new ArrayList<>();

        //passing
        this.myPassed = false;
        this.opponentPassed = false;
        this.roundTracker = 0;
    }

    public void initAllCards(CardGenerator cardGenerator) {
        try {
            JSONObject jsonObject = new JSONObject(cardGenerator.loadCardJSONFromAsset());
            this.allCards = cardGenerator.fillAllCardsIntoList(jsonObject);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }

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

    public void removeRandomCardFromOpponentHand() {
        SecureRandom random = new SecureRandom();
        int zz = 0;
        if (!this.opponentHand.isEmpty()) {
            zz = random.nextInt(this.getOpponentHand().size());
            this.opponentHand.remove(zz);
        }
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
        Card tempOpponentLeader = this.opponentLeader;
        Boolean tempUsedOpponentLeader = this.usedOpponentLeader;

        this.opponentDeck = this.myDeck;
        this.opponentLeader = this.myLeader;
        this.usedOpponentLeader = this.usedMyLeader;

        this.myDeck = tempOpponentDeck;
        this.myLeader = tempOpponentLeader;
        this.usedMyLeader = tempUsedOpponentLeader;

        int[] tempRoundCounter = this.myRoundCounter;
        this.myRoundCounter = this.opponentRoundCounter;
        this.opponentRoundCounter = tempRoundCounter;
    }

    public void incrementRoundTracker() {
        this.roundTracker++;
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

    public List<Card> getMyRanged() {
        return myRanged;
    }

    public void setMyRanged(List<Card> myRanged) {
        this.myRanged = myRanged;
    }

    public List<Card> getOpponentClose() {
        return opponentClose;
    }

    public void setOpponentClose(List<Card> opponentClose) {
        this.opponentClose = opponentClose;
    }

    public List<Card> getOpponentRanged() {
        return opponentRanged;
    }

    public void setOpponentRanged(List<Card> opponentRanged) {
        this.opponentRanged = opponentRanged;
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

    public List<Card> getAllCards() {
        return this.allCards;
    }

    public void setAllCards(List<Card> cards) {
        this.allCards = cards;
    }

    public boolean isRedrawPhase() {
        return redrawPhase;
    }

    public void setRedrawPhase(boolean redrawPhase) {
        this.redrawPhase = redrawPhase;
    }

    public boolean isMyPassed() {
        return myPassed;
    }

    public boolean isOpponentPassed() {
        return opponentPassed;
    }

    public void setMyPassed(boolean myPassed) {
        this.myPassed = myPassed;
    }

    public void setOpponentPassed(boolean opponentPassed) {
        this.opponentPassed = opponentPassed;
    }

    public int[] getMyRoundCounter() {
        return myRoundCounter;
    }

    public void setMyRoundCounter(int[] myRoundCounter) {
        this.myRoundCounter = myRoundCounter;
    }

    public void setMyRoundCounterByRound(int points) {
        this.myRoundCounter[this.roundTracker] = points;
    }

    public int[] getOpponentRoundCounter() {
        return opponentRoundCounter;
    }

    public void setOpponentRoundCounter(int[] opponentRoundCounter) {
        this.opponentRoundCounter = opponentRoundCounter;
    }

    public void setOpponentRoundCounterByRound(int counter) {
        this.opponentRoundCounter[this.roundTracker] = counter;
    }

    public int getRoundTracker() {
        return roundTracker;
    }

    public void setRoundTracker(int roundTracker) {
        this.roundTracker = roundTracker;
    }

    public boolean isCheated() {
        return cheated;
    }

    public void setCheated(boolean cheated) {
        this.cheated = cheated;
    }

    public boolean isOpponentCheated() {
        return opponentCheated;
    }

    public void setOpponentCheated(boolean opponentCheated) {
        this.opponentCheated = opponentCheated;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "roundTracker=" + roundTracker +
                ", myPassed/opponenPassed=" + myPassed + "/" + opponentPassed +
                ", myDeck='" + myDeck + '\'' +
                ", opponentDeck='" + opponentDeck + '\'' +
                ", myHand=" + myHand +
                ", opponentHand=" + opponentHand +
                ", myGrave=" + myGrave +
                ", opponentGrave=" + opponentGrave +
                ", weather=" + weather +
                ", myClose=" + myClose +
                ", myRanged=" + myRanged +
                ", opponentClose=" + opponentClose +
                ", opponentRanged=" + opponentRanged +
                ", myLeader=" + myLeader +
                ", usedMyLeader=" + usedMyLeader +
                ", opponentLeader=" + opponentLeader +
                ", usedOpponentLeader=" + usedOpponentLeader +
                ", myRoundCounter=" + myRoundCounter +
                ", opponentRoundCounter=" + opponentRoundCounter +
                ", cheated=" + cheated +
                ", opponentCheated=" + opponentCheated +
                '}';
    }
}
