package at.moritzmusel.gwent.model;

import static at.moritzmusel.gwent.model.Ability.scorch_s;
import static at.moritzmusel.gwent.model.Type.scoiatael;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class CardTest {
    private static List<Card> cardList;
    private Card card1;

    @BeforeEach
    private void init() {
        cardList = new ArrayList<>();
        card1 = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        cardList.add(card1);
    }

    @Test
    private void testCreateNewCard() {
        Card newCard = new Card("Schirru", scoiatael, null, 8, scorch_s, "schirru", 1, "Time to look death in the face.");
        Assertions.assertEquals(card1, newCard);
    }

}
