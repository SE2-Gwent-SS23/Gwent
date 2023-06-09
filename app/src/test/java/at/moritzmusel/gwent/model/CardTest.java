package at.moritzmusel.gwent.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static at.moritzmusel.gwent.model.Ability.SCORCH_S;
import static at.moritzmusel.gwent.model.Type.SCOIATAEL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CardTest {
    private static List<Card> cardList;
    private Card card1;
    private Card card2;

    @BeforeEach
    void init() {
        cardList = new ArrayList<>();
        card1 = new Card("Schirru", SCOIATAEL, null, 8, SCORCH_S, "schirru", 1);
        cardList.add(card1);
        card2 = new Card();
    }

    @Test
    void testCreateNewCard() {
        Card newCard = new Card("Schirru", SCOIATAEL, null, 8, SCORCH_S, "schirru", 1);
        assertEquals(card1.toString(), newCard.toString());
    }

    @Test
    void testChangeType() {
        Card newCard = new Card("Schirru", SCOIATAEL, null, 8, SCORCH_S, "schirru", 1);
        newCard.changeType("MONSTERS");
        assertEquals(Type.MONSTERS, newCard.getType());
    }

    @Test
    void testChangeRow() {
        Card newCard = new Card("Schirru", SCOIATAEL, null, 8, SCORCH_S, "schirru", 1);
        newCard.changeRow("CLOSE");
        assertEquals(Row.CLOSE, newCard.getRow());
    }

    @Test
    void testChangeAbility() {
        Card newCard = new Card("Schirru", SCOIATAEL, null, 8, SCORCH_S, "schirru", 1);
        newCard.changeAbility("SCORCH_S");
        assertEquals(SCORCH_S, newCard.getAbility());
    }

    @Test
    void testApplyWeather() {
        Card newCard = new Card("Schirru", SCOIATAEL, null, 8, SCORCH_S, "schirru", 1);
        newCard.applyWeather();
        assertEquals(1, newCard.getStrength());
    }

    @Test
    void testApplyHorn() {
        Card newCard = new Card("Schirru", SCOIATAEL, null, 8, SCORCH_S, "schirru", 1);
        int strength = newCard.strength*2;
        newCard.applyHorn();
        assertEquals(strength, newCard.getStrength());
    }

    @Test
    void testNewEmptyCard() {
        Card newCard = new Card();
        assertEquals(newCard.toString(), card2.toString());
        assertEquals(newCard.getFilename(), card2.getFilename());
    }

    @Test
    void testChangeRowWithTxtEmpty() {
        Card newCard = new Card("Schirru", SCOIATAEL, null, 8, SCORCH_S, "schirru", 1);
        newCard.changeRow("");
        assertEquals(Row.NONE, newCard.getRow());
    }

    @Test
    void testChangeAbilityWithTxtEmpty() {
        Card newCard = new Card("Schirru", SCOIATAEL, null, 8, SCORCH_S, "schirru", 1);
        newCard.changeAbility("");
        assertEquals(Ability.NONE, newCard.getAbility());
    }

}
