package at.moritzmusel.gwent.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import at.moritzmusel.gwent.R;
import at.moritzmusel.gwent.adapter.UserCardAdapter;
import at.moritzmusel.gwent.network.data.GameState;

public class CardGenerator {
    private List<Card> allCardsList;
    private Context context;
    private int deviceHeight;
    private SecureRandom mRandom = new SecureRandom();


    public CardGenerator(Context context, int deviceHeight) {
        this.allCardsList = new ArrayList<>();
        this.context = context;
        this.deviceHeight = deviceHeight;
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
            is.close();
        } catch (IOException e) {
            Log.e("Error", e.getLocalizedMessage());
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
        String filename;
        JSONArray jsonArray = jsonObject.optJSONArray("cards");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            name = obj.optString("name");
            strength = Integer.parseInt(obj.optString("strength"));
            filename = obj.optString("filename");
            count = Integer.parseInt(obj.optString("count"));

            Card newCard = new Card();
            newCard.setName(name.toUpperCase());
            newCard.setStrength(strength);
            newCard.setCount(count);
            newCard.setFilename(filename);
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

    public void setCards(RecyclerView view, Boolean isMyHand, List<Card> cards, Context context, Activity parentActivity, View.OnDragListener dragListener, GameState gameState) throws JSONException, IOException {
        UserCardAdapter adapterLanes = new UserCardAdapter(cards, isMyHand, context, deviceHeight / 6, gameState);
        view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(parentActivity, LinearLayoutManager.HORIZONTAL, false);
        view.setLayoutManager(linearLayoutManagerUser);
        view.setItemAnimator(new DefaultItemAnimator());
        view.setAdapter(adapterLanes);
        if (dragListener == null) {
            view.setOnDragListener(adapterLanes.getDragInstance());
        } else {
            view.setOnDragListener(dragListener);
        }
    }

    public void setImageFromAssetForOpponent(Context context, ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) AppCompatResources.getDrawable(context, R.drawable.card_deck_back_opponent_right)).getBitmap();
        Drawable dr = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 50, 70, true));
        image.setImageDrawable(dr);
    }
}
