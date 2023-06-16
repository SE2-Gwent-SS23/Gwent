package at.moritzmusel.gwent.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONException;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.mockito.Mockito;

import at.moritzmusel.gwent.network.data.GameState;
import at.moritzmusel.gwent.ui.GameViewActivity;


public class GameStateTest {
    private GameState gameState;
    private Card cardClose;
    private Card cardRanged;
    private Context context;
    //   private AssetManager assetManager;
    private List<Card> cardList;
    //  private CardGenerator cardGenerator;


    @BeforeEach
    void init() {
        this.gameState = new GameState(0, 0, 0, false);
        this.cardClose = new Card("Test1", Type.MONSTERS, Row.CLOSE, 1, Ability.BOND, "TestCase1", 1, "First Card to test");
        this.cardRanged = new Card("Test2", Type.SCOIATAEL, Row.RANGED, 2, Ability.MEDIC, "TestCase2", 1, "Second Card to test");
        this.cardList = new ArrayList<>();
        this.cardList.add(cardClose);
        this.cardList.add(cardRanged);
        // this.cardGenerator = Mockito.mock(CardGenerator.class);
        this.context= Mockito.mock(GameViewActivity.class);
        // when(context.getAssets()).thenReturn(assetManager);
        try {
            //    when(cardGenerator.fillAllCardsIntoList(any())).thenReturn(cardList);
            this.gameState.initGameState();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testSendToMyGrave() {
        this.gameState.addToMyClose(cardClose);
        this.gameState.addToMyRanged(cardRanged);
        this.gameState.sendToMyGrave();
        assertEquals(this.gameState.getMyGrave(), cardList);
        Assertions.assertTrue(this.gameState.getMyClose().isEmpty());
        Assertions.assertTrue(this.gameState.getMyRanged().isEmpty());
    }

    @Test
    void testSendToOpponentGrave() {
        this.gameState.addToOpponentClose(cardClose);
        this.gameState.addToOpponentRanged(cardRanged);
        this.gameState.sendToOpponentGrave();
        assertEquals(this.gameState.getOpponentGrave(), cardList);
        Assertions.assertTrue(this.gameState.getOpponentClose().isEmpty());
        Assertions.assertTrue(this.gameState.getOpponentRanged().isEmpty());
    }

    @Test
    void testCalculateMyWins() {
        int[] myRoundCounter = new int[]{10, 10, 10};
        int[] noWins = new int[]{20, 20, 20};
        int[] oneWins = new int[]{9, 20, 20};
        int[] twoWins = new int[]{9, 9, 20};
        int[] threeWins = new int[]{9, 9, 9};
        this.gameState.setMyRoundCounter(myRoundCounter);

        assertEquals(this.gameState.calculateMyWins(noWins), 0);
        assertEquals(this.gameState.calculateMyWins(oneWins), 1);
        assertEquals(this.gameState.calculateMyWins(twoWins), 2);
        assertEquals(this.gameState.calculateMyWins(threeWins), 3);
    }

    @Test
    void testHasCards() {
        Assertions.assertFalse(this.gameState.isMyPassed());
        this.gameState.hasCards();
        Assertions.assertTrue(this.gameState.isMyPassed());
    }

    @Test
        //not used
    void testDetermineWinner() {

    }

    @Test
    void testInitGameState() {
        Assertions.assertTrue(gameState.getMyDeck() instanceof String);
        Assertions.assertTrue(gameState.getOpponentDeck() instanceof String);
        Assertions.assertTrue(gameState.getWeather() instanceof ArrayList);
        Assertions.assertTrue(gameState.getMyLeader() instanceof Card);
        Assertions.assertTrue(gameState.getOpponentLeader() instanceof Card);
        Assertions.assertTrue(gameState.getUsedMyLeader() instanceof Boolean);
        Assertions.assertTrue(gameState.getUsedOpponentLeader() instanceof Boolean);
        Assertions.assertTrue(gameState.getMyRoundCounter() instanceof int[]);
        assertNotNull(gameState.isCheated());
        Assertions.assertTrue(gameState.getMyHand() instanceof ArrayList);
        Assertions.assertTrue(gameState.getAllCards() instanceof ArrayList);
        Assertions.assertTrue(gameState.getMyRanged() instanceof ArrayList);
        Assertions.assertTrue(gameState.getOpponentRanged() instanceof ArrayList);
        Assertions.assertTrue(gameState.getMyClose() instanceof ArrayList);
        Assertions.assertTrue(gameState.getOpponentClose() instanceof ArrayList);
        Assertions.assertTrue(gameState.getOpponentHand() instanceof ArrayList);
        Assertions.assertTrue(gameState.getMyGrave() instanceof ArrayList);
        Assertions.assertTrue(gameState.getOpponentGrave() instanceof ArrayList);
        assertNotNull(gameState.isMyPassed());
        assertNotNull(gameState.isOpponentPassed());
        assertNotNull(gameState.getRoundTracker());
    }

    @Test
    void testInitAllCards() throws IOException, JSONException {
        CardGenerator cardGenerator = Mockito.mock(CardGenerator.class);

        Mockito.when(cardGenerator.loadCardJSONFromAsset()).thenReturn("{\"cards\": []}");
        Mockito.when(cardGenerator.fillAllCardsIntoList(Mockito.any(JSONObject.class))).thenReturn(new ArrayList<>());

        GameState yourClass = new GameState(0, 0, 0, false);
        yourClass.initAllCards(cardGenerator);

        Assertions.assertNotNull(yourClass.getAllCards());

        Mockito.verify(cardGenerator, Mockito.times(1)).loadCardJSONFromAsset();
        Mockito.verify(cardGenerator, Mockito.times(1)).fillAllCardsIntoList(Mockito.any(JSONObject.class));

    }

    @Test
    void testCalculateMyPoints() {
        this.gameState.addToMyClose(cardClose);
        this.gameState.addToMyClose(cardRanged);
        this.gameState.addToMyRanged(cardRanged);
        this.gameState.addToMyRanged(cardClose);
        assertEquals(this.gameState.calculateMyPoints(), 6);
    }

    @Test
    void testCalculateOpponentPoints() {
        this.gameState.addToOpponentClose(cardClose);
        this.gameState.addToOpponentClose(cardRanged);
        this.gameState.addToOpponentRanged(cardRanged);
        this.gameState.addToOpponentRanged(cardClose);
        assertEquals(this.gameState.calculateOpponentPoints(), 6);
    }

    @Test
    void testApplySun() {
        this.gameState.addToWeather(cardClose);
        Assertions.assertFalse(this.gameState.getWeather().isEmpty());
        this.gameState.applySun();
        Assertions.assertTrue(this.gameState.getWeather().isEmpty());
    }

    @Test
        //not used
    void testSwapPlayer() {

    }

    @Test
    void testIncrementRoundTracker() {
        assertEquals(this.gameState.getRoundTracker(), 0);
        this.gameState.incrementRoundTracker();
        assertEquals(this.gameState.getRoundTracker(), 1);

    }

    @Test
    void testToString() {
        this.gameState.addToMyHand(cardClose);
        this.gameState.addToMyClose(cardClose);
        this.gameState.addToMyRanged(cardRanged);
        this.gameState.addToOpponentHand(cardClose);
        this.gameState.addToOpponentClose(cardClose);
        this.gameState.addToOpponentRanged(cardRanged);
        this.gameState.addToMyGrave(cardRanged);
        String testString = "GameState{localPlayer=0, playerTurn=0, playerWon=0, isOver=false, myDeck='', opponentDeck='', myHand=[Card{name='Test1', type=MONSTERS, row=CLOSE, strength=1, ability=BOND, filename='TestCase1', count=1, flavorTxt='First Card to test'}], opponentHand=[Card{name='Test1', type=MONSTERS, row=CLOSE, strength=1, ability=BOND, filename='TestCase1', count=1, flavorTxt='First Card to test'}], myGrave=[Card{name='Test2', type=SCOIATAEL, row=RANGED, strength=2, ability=MEDIC, filename='TestCase2', count=1, flavorTxt='Second Card to test'}], opponentGrave=[], weather=[], myClose=[Card{name='Test1', type=MONSTERS, row=CLOSE, strength=1, ability=BOND, filename='TestCase1', count=1, flavorTxt='First Card to test'}], myRanged=[Card{name='Test2', type=SCOIATAEL, row=RANGED, strength=2, ability=MEDIC, filename='TestCase2', count=1, flavorTxt='Second Card to test'}], opponentClose=[Card{name='Test1', type=MONSTERS, row=CLOSE, strength=1, ability=BOND, filename='TestCase1', count=1, flavorTxt='First Card to test'}], opponentRanged=[Card{name='Test2', type=SCOIATAEL, row=RANGED, strength=2, ability=MEDIC, filename='TestCase2', count=1, flavorTxt='Second Card to test'}], myLeader=Card{name='null', type=null, row=null, strength=0, ability=null, filename='null', count=0, flavorTxt='null'}, usedMyLeader=false, opponentLeader=Card{name='null', type=null, row=null, strength=0, ability=null, filename='null', count=0, flavorTxt='null'}, usedOpponentLeader=false, myRoundCounter=[I@3003827c, opponentRoundCounter=[I@29b40b3}";
        assertEquals(this.gameState.toString(), testString);
    }

    @AfterEach
    void tearDown() {
        this.gameState = null;
    }


}
