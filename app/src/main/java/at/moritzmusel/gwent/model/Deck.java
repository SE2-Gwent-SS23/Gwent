package at.moritzmusel.gwent.model;

import java.util.ArrayList;

public class Deck {
    String deckName;
    String leader;
    private ArrayList<Card> deck;
    //String cardFileName;
    int count;



    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }
    public Deck(){

    }

    public ArrayList<Card> getDeck() {
        return deck;
    }


    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }


    @Override
    public String toString() {
        String retVal;
               retVal =  "Deck{" +
                       "name='" + "deckName" + '\'' +
                       ", leader='" + "leader" + '\'' +
                       ", cards: [ ";

        for (int i = 0; i < deck.size(); i++) {
            retVal += "{ filename='" + deck.get(i).getFilename()+ '\'';
            retVal += ", count= " + deck.get(i).getCount() + "},";
        }
        retVal = retVal.substring(0, retVal.length()-1);
        retVal += "]}";
        return retVal;

    }
}
