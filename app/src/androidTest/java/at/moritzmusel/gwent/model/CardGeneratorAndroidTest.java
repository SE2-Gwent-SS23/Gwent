package at.moritzmusel.gwent.model;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

public class CardGeneratorAndroidTest {
    private static CardGenerator cardGenerator;

    @Test
    void testFillCardSizeTest() throws JSONException, IOException {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        cardGenerator = new CardGenerator(targetContext, 100);
        JSONObject jsonObject = new JSONObject(cardGenerator.loadCardJSONFromAsset());
        assertEquals(214, cardGenerator.fillAllCardsIntoList(jsonObject).size());
    }
}
