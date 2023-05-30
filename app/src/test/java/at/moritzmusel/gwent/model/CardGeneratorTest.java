package at.moritzmusel.gwent.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static at.moritzmusel.gwent.model.Ability.scorch_s;
import static at.moritzmusel.gwent.model.Type.scoiatael;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import at.moritzmusel.gwent.ui.GameViewActivity;

public class CardGeneratorTest {

    private CardGenerator cardGenerator;
    @Mock
    private JSONObject jsonObject;

    @BeforeEach
    void init() {
        cardGenerator = CardGenerator.getInstance();
    }

    @Test
    void testCreateNewCardListWithJSONObject() throws JSONException, IOException {
        String jsonString = "{\n" +
                "  \"cards\": [\n" +
                "    {\n" +
                "      \"name\": \"Mysterious Elf\",\n" +
                "      \"type\": \"neutral\",\n" +
                "      \"row\": \"close\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"hero spy\",\n" +
                "      \"filename\": \"mysterious_elf\",\n" +
                "      \"count\": \"1\",\n" +
                "      \"flavor_txt\": \"You humans have... unusual tastes.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Decoy\",\n" +
                "      \"type\": \"special\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"decoy\",\n" +
                "      \"filename\": \"decoy\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"When you run out of peasants, decoys also make decent arrow fodder.\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Biting Frost\",\n" +
                "      \"type\": \"weather\",\n" +
                "      \"row\": \"none\",\n" +
                "      \"strength\": \"0\",\n" +
                "      \"ability\": \"frost\",\n" +
                "      \"filename\": \"frost\",\n" +
                "      \"count\": \"3\",\n" +
                "      \"flavor_txt\": \"Best part about frost - bodies of the fallen don't rot so quickly.\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        jsonObject = new JSONObject(jsonString);
        cardGenerator.fillAllCardsIntoList(jsonObject);
        assertEquals(3, cardGenerator.getAllCardsList().size());
    }
}
