package at.moritzmusel.gwent.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import at.moritzmusel.gwent.network.data.GameState;

public class CardGenerator {
    private static CardGenerator cardGeneratorInstance;

    private List<Card> allCardsList;
    private Context context;

    private SecureRandom mRandom = new SecureRandom();

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
        String flavorTxt;
        String filename;
        JSONArray jsonArray = jsonObject.optJSONArray("cards");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            name = obj.optString("name");
            strength = Integer.parseInt(obj.optString("strength"));
            filename = obj.optString("filename");
            count = Integer.parseInt(obj.optString("count"));
            flavorTxt = obj.optString("flavor_txt");

            Card newCard = new Card();
            newCard.setName(name.toUpperCase());
            newCard.setStrength(strength);
            newCard.setCount(count);
            newCard.setFilename(filename.toUpperCase());
            newCard.setFlavorTxt(flavorTxt.toUpperCase());
            newCard.changeType(obj.optString("type").toUpperCase());
            newCard.changeRow(obj.optString("row").toUpperCase());
            newCard.changeAbility(obj.optString("ability").toUpperCase());
            allCardsList.add(newCard);
        }
        return allCardsList;
    }

    /**
     * Init 10 myHand cards
     * @param gameState
     * @return the adapted gameState object
     */
    public GameState initMyHandCards(GameState gameState) {
        int zz;
        for (int i = 0; i < 10; i++) {
            zz = mRandom.nextInt(gameState.getAllCards().size());
            Card card = gameState.getAllCards().get(zz);
            while (card.getCount() == 0) {
                zz = mRandom.nextInt(gameState.getAllCards().size());
                card = gameState.getAllCards().get(zz);
            }

            gameState.addToMyHand(card);
            gameState.getAllCards().get(i).setCount(card.getCount() - 1);
        }
        return gameState;
    }
}
