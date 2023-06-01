package at.moritzmusel.gwent.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CardGenerator {
    private static CardGenerator cardGeneratorInstance;

    private List<Card> allCardsList;
    private Context context;

    public CardGenerator() {
    }

    public CardGenerator(Context context) {
        this.allCardsList = new ArrayList<>();
        this.context = context;
    }

    public static CardGenerator getInstance() {
        if (cardGeneratorInstance == null) {
            cardGeneratorInstance = new CardGenerator();
        }
        return cardGeneratorInstance;
    }

    public String loadCardJSONFromAsset() throws IOException {
        String jsonString = null;
        InputStream is = null;
        try {
            is = context.getAssets().open("cards.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            while (is.read(buffer) > 0) {
                jsonString = new String(buffer, "UTF-8");
            }
            if (is != null) is.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        } finally {
            if (is != null) is.close();
        }

        return jsonString;
    }

    public List<Card> fillAllCardsIntoList(JSONObject jsonObject) throws JSONException, IOException {
        this.allCardsList = new ArrayList<>();
        String name;
        int strength;
        int count;
        String flavor_txt;
        String filename;
        JSONArray jsonArray = jsonObject.optJSONArray("cards");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            name = obj.optString("name");
            strength = Integer.parseInt(obj.optString("strength"));
            filename = obj.optString("filename");
            count = Integer.parseInt(obj.optString("count"));
            flavor_txt = obj.optString("flavor_txt");

            Card newCard = new Card();
            newCard.setName(name);
            newCard.setStrength(strength);
            newCard.setCount(count);
            newCard.setFilename(filename);
            newCard.setFlavor_txt(flavor_txt);
            newCard.changeType(obj.optString("type"));
            newCard.changeRow(obj.optString("row"));
            newCard.changeAbility(obj.optString("ability"));
            allCardsList.add(newCard);
        }
        return allCardsList;
    }
}
