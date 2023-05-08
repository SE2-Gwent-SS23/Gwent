package at.moritzmusel.gwent.network.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.moritzmusel.gwent.model.Card2;

public class GameState {
    String myDeck;
    String opponentDeck;
    List<Card2> myHand;
    List<Card2> opponentHand;
    List<Card2> myGrave;
    List<Card2> opponentGrave;
    List<Card2> weather;
    List<Card2> myClose;
    Boolean myWeatherClose;
    List<Card2> myRanged;
    Boolean myWeatherRanged;
    List<Card2> opponentClose;
    Boolean opponentWeatherClose;
    List<Card2> opponentRanged;
    Boolean opponentWeatherRanged;

    Card2 myLeader;
    Boolean usedMyLeader;
    Card2 opponentLeader;
    Boolean usedOpponentLeader;

    public int calculateMyPoints() {
        int sum = 0;
        for (Card2 c : this.myClose) {
            sum += c.getStrength();
        }
        for (Card2 c : this.myRanged) {
            sum += c.getStrength();
        }
        return sum;
    }

    public int calculateOpponentPoints() {
        int sum = 0;
        for (Card2 c : this.opponentClose) {
            sum += c.getStrength();
        }
        for (Card2 c : this.opponentRanged) {
            sum += c.getStrength();
        }
        return sum;
    }

    public void applySun() {
        this.weather.clear();
    }

    public void swapPlayer() {
        List<Card2> tempOpponentHand = new ArrayList<>(this.opponentHand);
        List<Card2> tempOpponentGrave = new ArrayList<>(this.opponentGrave);
        List<Card2> tempOpponentClose = new ArrayList<>(this.opponentClose);
        List<Card2> tempOpponentRanged = new ArrayList<>(this.opponentRanged);

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
        Card2 tempOpponentLeader = this.opponentLeader;
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


    public void addToMyHand(Card2 card) {
        this.myHand.add(card);
    }

    public void removeFromMyHand(Card2 card) {
        this.myHand.remove(card);
    }

    public void addToOpponentHand(Card2 card) {
        this.opponentHand.add(card);
    }

    public void removeFromOpponentHand(Card2 card) {
        this.opponentHand.remove(card);
    }

    public void addToMyGrave(Card2 card) {
        this.myGrave.add(card);
    }

    public void removeFromMyGrave(Card2 card) {
        this.myGrave.remove(card);
    }

    public void addOpponentToGrave(Card2 card) {
        this.opponentGrave.add(card);
    }

    public void removeFromOpponentGrave(Card2 card) {
        this.opponentGrave.remove(card);
    }

    public void addToWeather(Card2 card) {
        this.weather.add(card);
    }

    public void removeFromWeather(Card2 card) {
        this.weather.remove(card);
    }

    public void addToMyClose(Card2 card) {
        this.myClose.add(card);
    }

    public void removeFromMyClose(Card2 card) {
        this.myClose.remove(card);
    }

    public void addToMyRanged(Card2 card) {
        this.myRanged.add(card);
    }

    public void removeFromMyRanged(Card2 card) {
        this.myRanged.remove(card);
    }

    public void addToOpponentClose(Card2 card) {
        this.opponentClose.add(card);
    }

    public void removeFromOpponentClose(Card2 card) {
        this.opponentClose.remove(card);
    }

    public void addToOpponentRanged(Card2 card) {
        this.opponentRanged.add(card);
    }

    public void removeFromOpponentRanged(Card2 card) {
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

    public List<Card2> getMyHand() {
        return myHand;
    }

    public void setMyHand(List<Card2> myHand) {
        this.myHand = myHand;
    }

    public List<Card2> getOpponentHand() {
        return opponentHand;
    }

    public void setOpponentHand(List<Card2> opponentHand) {
        this.opponentHand = opponentHand;
    }

    public List<Card2> getMyGrave() {
        return myGrave;
    }

    public void setMyGrave(List<Card2> myGrave) {
        this.myGrave = myGrave;
    }

    public List<Card2> getOpponentGrave() {
        return opponentGrave;
    }

    public void setOpponentGrave(List<Card2> opponentGrave) {
        this.opponentGrave = opponentGrave;
    }

    public List<Card2> getWeather() {
        return weather;
    }

    public void setWeather(List<Card2> weather) {
        this.weather = weather;
    }

    public List<Card2> getMyClose() {
        return myClose;
    }

    public void setMyClose(List<Card2> myClose) {
        this.myClose = myClose;
    }

    public Boolean getMyWeatherClose() {
        return myWeatherClose;
    }

    public void setMyWeatherClose(Boolean myWeatherClose) {
        this.myWeatherClose = myWeatherClose;
    }

    public List<Card2> getMyRanged() {
        return myRanged;
    }

    public void setMyRanged(List<Card2> myRanged) {
        this.myRanged = myRanged;
    }

    public Boolean getMyWeatherRanged() {
        return myWeatherRanged;
    }

    public void setMyWeatherRanged(Boolean myWeatherRanged) {
        this.myWeatherRanged = myWeatherRanged;
    }

    public List<Card2> getOpponentClose() {
        return opponentClose;
    }

    public void setOpponentClose(List<Card2> opponentClose) {
        this.opponentClose = opponentClose;
    }

    public Boolean getOpponentWeatherClose() {
        return opponentWeatherClose;
    }

    public void setOpponentWeatherClose(Boolean opponentWeatherClose) {
        this.opponentWeatherClose = opponentWeatherClose;
    }

    public List<Card2> getOpponentRanged() {
        return opponentRanged;
    }

    public void setOpponentRanged(List<Card2> opponentRanged) {
        this.opponentRanged = opponentRanged;
    }

    public Boolean getOpponentWeatherRanged() {
        return opponentWeatherRanged;
    }

    public void setOpponentWeatherRanged(Boolean opponentWeatherRanged) {
        this.opponentWeatherRanged = opponentWeatherRanged;
    }

    public Card2 getMyLeader() {
        return myLeader;
    }

    public void setMyLeader(Card2 myLeader) {
        this.myLeader = myLeader;
    }

    public Boolean getUsedMyLeader() {
        return usedMyLeader;
    }

    public void setUsedMyLeader(Boolean usedMyLeader) {
        this.usedMyLeader = usedMyLeader;
    }

    public Card2 getOpponentLeader() {
        return opponentLeader;
    }

    public void setOpponentLeader(Card2 opponentLeader) {
        this.opponentLeader = opponentLeader;
    }

    public Boolean getUsedOpponentLeader() {
        return usedOpponentLeader;
    }

    public void setUsedOpponentLeader(Boolean usedOpponentLeader) {
        this.usedOpponentLeader = usedOpponentLeader;
    }
}
