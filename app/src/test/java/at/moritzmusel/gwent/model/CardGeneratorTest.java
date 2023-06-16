package at.moritzmusel.gwent.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static at.moritzmusel.gwent.model.Ability.SCORCH_S;
import static at.moritzmusel.gwent.model.Type.SCOIATAEL;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.network.data.GameState;
import at.moritzmusel.gwent.ui.DragListener;
import at.moritzmusel.gwent.ui.GameViewActivity;

class CardGeneratorTest {
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private List<Card> cardList;

    private CardGenerator cardGenerator;
    private AssetManager assetManager;
    private Context context;
    private RecyclerView recyclerView;
    private GameState gameState;
    private GameViewActivity gameViewActivity;
    private View.OnDragListener dragListener;

    @BeforeEach
    void init() {
        context = mock(Context.class);
        assetManager = mock(AssetManager.class);
        cardGenerator = new CardGenerator(context, 100);
        cardList = new ArrayList<>();
        jsonObject = new JSONObject();
        jsonArray = new JSONArray();
        gameState = Mockito.mock(GameState.class);
        recyclerView = Mockito.mock(RecyclerView.class);
        when(context.getAssets()).thenReturn(assetManager);
    }

    @Test
    void testLoadCardJSONFromAssetException() throws IOException {
        when(assetManager.open("cards.json")).thenThrow(new IOException("File not found"));
        String jsonString = cardGenerator.loadCardJSONFromAsset();

        assertNull(jsonString);
    }

    @Test
    void testFillAllCardsIntoList() throws JSONException, IOException {
        jsonObject.put("cards", jsonArray);

        String name = "DECOY";
        int strength = 5;
        int count = 2;
        String flavor_txt = "Test text";
        String filename = "monsters_arachas.jpg";

        JSONObject newCardObj = new JSONObject();
        newCardObj.put("row", "NONE");
        newCardObj.put("name", name);
        newCardObj.put("type", "REALMS");
        newCardObj.put("strength", String.valueOf(strength));
        newCardObj.put("count", String.valueOf(count));
        newCardObj.put("flavor_txt", flavor_txt);
        newCardObj.put("filename", filename);
        newCardObj.put("ability", "RAIN");

        jsonArray.put(newCardObj);

        Card newCard = new Card();
        newCard.setName(name);
        newCard.changeType("REALMS");
        newCard.setStrength(strength);
        newCard.setCount(count);
        newCard.setFlavorTxt(flavor_txt);
        newCard.setFilename(filename);
        newCard.changeRow("NONE");
        newCard.changeAbility("RAIN");
        cardList.add(newCard);

        assertEquals(cardList.toString(), cardGenerator.fillAllCardsIntoList(jsonObject).toString());
    }

    @Test
    void testInitMyHandCards() {
        GameState gameState = new GameState(0, 0, 0, false);
        List<Card> myHand = new ArrayList<>();
        Card card1 = new Card();
        card1.setCount(2);
        Card card2 = new Card();
        card2.setCount(0);
        myHand.add(card1);
        myHand.add(card2);

        gameState.setMyHand(myHand);

        assertEquals(2, myHand.size());
        assertEquals(card1, myHand.get(0));
        assertEquals(card2, myHand.get(1));
    }

    @Test
    public void testSetCards() {
        List<Card> cards = new ArrayList<>();
        Card newCard = new Card("Schirru", SCOIATAEL, null, 8, SCORCH_S, "schirru", 1, "Time to look death in the face.");
        cards.add(newCard);

        /* when -> assert -> verify */
        Mockito.when(recyclerView.getLayoutManager()).thenReturn(null);
        Mockito.doAnswer(invocation -> {
            Object arg = invocation.getArgument(0);
            LinearLayoutManager layoutManager = (LinearLayoutManager) arg;
            assertEquals(LinearLayoutManager.HORIZONTAL, layoutManager.getOrientation());
            assertTrue(arg instanceof LinearLayoutManager);
            return null;
        }).when(recyclerView).setLayoutManager(Mockito.any(LinearLayoutManager.class));

        assertDoesNotThrow(() -> cardGenerator.setCards(recyclerView, true, cards, context, gameViewActivity, dragListener, gameState));

        Mockito.verify(recyclerView).setHasFixedSize(true);
        Mockito.verify(recyclerView).setLayoutManager(Mockito.any(LinearLayoutManager.class));
        Mockito.verify(recyclerView).setAdapter(Mockito.any(UserCardAdapter.class));
        Mockito.verify(recyclerView).setOnDragListener(Mockito.any(DragListener.class));
        Mockito.verify(recyclerView).setItemAnimator(Mockito.any(DefaultItemAnimator.class));
    }
}