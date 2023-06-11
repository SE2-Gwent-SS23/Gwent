package at.moritzmusel.gwent.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static at.moritzmusel.gwent.model.Ability.bond;
import static at.moritzmusel.gwent.model.Ability.fog;
import static at.moritzmusel.gwent.model.Ability.muster;
import static at.moritzmusel.gwent.model.Type.skellige;
import static at.moritzmusel.gwent.model.Type.special;
import static at.moritzmusel.gwent.model.Type.weather;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.moritzmusel.gwent.network.data.GameState;

class RedrawObjectGeneratorTest {

    private RedrawObjectGenerator redrawObjectGenerator;

    @BeforeEach
    void init() {
        redrawObjectGenerator = new RedrawObjectGenerator();
    }

    @Test
    void testHalfList() {
        List<Card> newList = Arrays.asList(
                new Card("Name 1", skellige, null, 8, fog, "name1.jpg", 1, "Time to look death in the face."),
                new Card("Name 2", skellige, null, 2, muster, "name2.jpg", 3, "Text 2"),
                new Card("Name 3", weather, null, 5, bond, "name3.jpg", 2, "Text 3"),
                new Card("Name 4", special, null, 3, fog, "name4.jpg", 4, "Text 4")
                );

        List<List<Card>> expectedResultListOfList = Arrays.asList(
                Arrays.asList(  new Card("Name 1", skellige, null, 8, fog, "name1.jpg", 1, "Time to look death in the face."),
                                new Card("Name 2", skellige, null, 2, muster, "name2.jpg", 3, "Text 2")),
                Arrays.asList(  new Card("Name 3", weather, null, 5, bond, "name3.jpg", 2, "Text 3"),
                                new Card("Name 4", special, null, 3, fog, "name4.jpg", 4, "Text 4")));

        assertEquals(expectedResultListOfList.toString(), redrawObjectGenerator.halveList(newList).toString());
    }

    @Test
    void testDrawRandomCard() {
        GameState gameState = new GameState(0, 0, 0, false);
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("Name 1", skellige, null, 8, fog, "name1", 1, "Time to look death in the face."));
        cards.add(new Card("Name 2", skellige, null, 2, muster, "name2", 3, "Text 2"));
        cards.add(new Card("Name 3", skellige, null, 9, muster, "name3", 3, "Text 3"));
        gameState.setAllCards(cards);

        RedrawObjectGenerator redrawObjectGenerator = new RedrawObjectGenerator();
        ReturnCard returnCard = redrawObjectGenerator.drawRandomCard(gameState);

        assertNotNull(returnCard);

        List<Card> allCards = gameState.getAllCards();
        assertTrue(allCards.contains(returnCard.getCard()));

        Card drawnCard = returnCard.getCard();
        int initialCount = drawnCard.getCount();
        int updatedCount = gameState.getAllCards().stream().filter(card -> card.equals(drawnCard)).mapToInt(Card::getCount).findFirst().orElse(0);
        assertEquals(initialCount, updatedCount);
    }
}
