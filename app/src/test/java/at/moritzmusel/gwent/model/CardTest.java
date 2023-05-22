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

    @BeforeEach
    void init() {
        cardList = new ArrayList<>();
        card1 = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        cardList.add(card1);
    }

    @Test
    void testCreateNewCard() {
        Card newCard = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        assertEquals(card1.toString(), newCard.toString());
    }

    @Test
    void testGetName() {
        Card newCard = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        assertEquals(card1.getName(), newCard.getName());
    }
}
