package at.moritzmusel.gwent.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static at.moritzmusel.gwent.model.Ability.BOND;
import static at.moritzmusel.gwent.model.Ability.FOG;
import static at.moritzmusel.gwent.model.Ability.MUSTER;
import static at.moritzmusel.gwent.model.Type.SKELLIGE;
import static at.moritzmusel.gwent.model.Type.SPECIAL;
import static at.moritzmusel.gwent.model.Type.WEATHER;
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
    void testRedrawInstance() {
        RedrawObjectGenerator instance1 = RedrawObjectGenerator.getInstance();
        RedrawObjectGenerator instance2 = RedrawObjectGenerator.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    void testHalfList() {
        List<Card> newList = Arrays.asList(
                new Card("Name 1", SKELLIGE, null, 8, FOG, "name1.jpg", 1),
                new Card("Name 2", SKELLIGE, null, 2, MUSTER, "name2.jpg", 3),
                new Card("Name 3", WEATHER, null, 5, BOND, "name3.jpg", 2),
                new Card("Name 4", SPECIAL, null, 3, FOG, "name4.jpg", 4)
                );

        List<List<Card>> expectedResultListOfList = Arrays.asList(
                Arrays.asList(  new Card("Name 1", SKELLIGE, null, 8, FOG, "name1.jpg", 1),
                                new Card("Name 2", SKELLIGE, null, 2, MUSTER, "name2.jpg", 3)),
                Arrays.asList(  new Card("Name 3", WEATHER, null, 5, BOND, "name3.jpg", 2),
                                new Card("Name 4", SPECIAL, null, 3, FOG, "name4.jpg", 4)));

        assertEquals(expectedResultListOfList.toString(), redrawObjectGenerator.halveList(newList).toString());
    }

    @Test
    void testDrawRandomCard() {
        GameState gameState = new GameState();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("Name 1", SKELLIGE, null, 8, FOG, "name1", 1));
        cards.add(new Card("Name 2", SKELLIGE, null, 2, MUSTER, "name2", 3));
        cards.add(new Card("Name 3", SKELLIGE, null, 9, MUSTER, "name3", 3));
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

    @Test
    void testDrawRandomCountCardCountZero() {
        GameState gameState = new GameState();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("Name 1", SKELLIGE, null, 8, FOG, "name1", 0));
        cards.add(new Card("Name 2", SKELLIGE, null, 2, MUSTER, "name2", 3));

        gameState.setAllCards(cards);

        RedrawObjectGenerator redrawObjectGenerator = new RedrawObjectGenerator();
        ReturnCard returnCard = redrawObjectGenerator.drawRandomCard(gameState);

        assertNotNull(returnCard);

        List<Card> allCards = gameState.getAllCards();
        assertTrue(allCards.contains(returnCard.getCard()));

        Card drawnCard = returnCard.getCard();
        assertNotNull(drawnCard);
    }

}
