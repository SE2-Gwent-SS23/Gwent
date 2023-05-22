package at.moritzmusel.gwent.model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

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
        String jsonString = "\"cards\": [\n" +
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
                "    } }";

        jsonObject = new JSONObject(jsonString);
        cardGenerator.fillAllCardsIntoList(jsonObject);
        assertEquals(3,cardGenerator.getAllCardsList().size());
    }
}
