package at.moritzmusel.gwent.model;

import static org.junit.Assert.*;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FillCardsTest {
    private static CardGenerator cardGenerator;
    private static List<Card> cardList;

    private Card card1;
    private Card card2;

    @Test
    public void testFillCardSizeTest() throws JSONException, IOException {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        System.out.println(targetContext);
        cardGenerator = new CardGenerator(targetContext);
        // loadCardJSONFromAsset();
        JSONObject jsonObject = new JSONObject(cardGenerator.loadCardJSONFromAsset());
        cardGenerator.fillAllCardsIntoList(jsonObject);
        assertEquals(214, cardGenerator.getAllCardsList().size());
    }

    public void testAddCardToList() {

    }
}
