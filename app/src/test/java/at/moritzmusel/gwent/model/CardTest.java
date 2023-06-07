package at.moritzmusel.gwent.model;

import static org.junit.jupiter.api.Assertions.*;
import static at.moritzmusel.gwent.model.Ability.scorch_s;
import static at.moritzmusel.gwent.model.Type.scoiatael;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CardTest {
    private static List<Card> cardList;
    private Card card1;
    private Card card2;

    @BeforeEach
    void init() {
        cardList = new ArrayList<>();
        card1 = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        cardList.add(card1);
        card2 = new Card();
    }

    @Test
    void testCreateNewCard() {
        Card newCard = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        assertEquals(card1.toString(), newCard.toString());
    }

    @Test
    void testChangeType() {
        Card newCard = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        newCard.changeType("monsters");
        assertEquals(Type.monsters, newCard.getType());
    }

    @Test
    void testChangeRow() {
        Card newCard = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        newCard.changeRow("close");
        assertEquals(Row.close, newCard.getRow());
    }

    @Test
    void testChangeAbility() {
        Card newCard = new Card("Schirru", scoiatael, null, 1, scorch_s, "schirru", 1, "Time to look death in the face.");
        newCard.changeAbility("scorch_s");
        assertEquals(scorch_s, newCard.getAbility());
    }

    @Test
    void testApplyWeather() {
        Card newCard = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        newCard.applyWeather();
        assertEquals(1, newCard.getStrength());
    }

    @Test
    void testApplyHorn() {
        Card newCard = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        int strength = newCard.strength*2;
        newCard.applyHorn();
        assertEquals(strength, newCard.getStrength());
    }

    @Test
    void testNewEmptyCard() {
        Card newCard = new Card();
        assertEquals(newCard.toString(), card2.toString());
    }

}
