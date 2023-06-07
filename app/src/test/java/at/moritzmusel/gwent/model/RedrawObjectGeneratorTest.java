package at.moritzmusel.gwent.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static at.moritzmusel.gwent.model.Ability.bond;
import static at.moritzmusel.gwent.model.Ability.fog;
import static at.moritzmusel.gwent.model.Ability.muster;
import static at.moritzmusel.gwent.model.Ability.scorch_s;
import static at.moritzmusel.gwent.model.Type.scoiatael;
import static at.moritzmusel.gwent.model.Type.skellige;
import static at.moritzmusel.gwent.model.Type.special;
import static at.moritzmusel.gwent.model.Type.weather;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.moritzmusel.gwent.network.data.GameState;

public class RedrawObjectGeneratorTest {

    private GameState gameState;
    private RedrawObjectGenerator redrawObjectGenerator;
    private CardGenerator cardGenerator;
    private Context context;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private List<Card> cardList;

    @BeforeEach
    void init() {
        redrawObjectGenerator = new RedrawObjectGenerator();
        gameState = mock(GameState.class);
        context = mock(Context.class);
        jsonObject = new JSONObject();
        jsonArray = new JSONArray();
        cardGenerator = new CardGenerator(context);
        cardList = new ArrayList<>();
    }

    @Test
    void testHalfList() {
        List<Card> newList = Arrays.asList(
                new Card("Name 1", skellige, null, 8, fog, "name1", 1, "Time to look death in the face."),
                new Card("Name 2", skellige, null, 2, muster, "name2", 3, "Text 2"),
                new Card("Name 3", weather, null, 5, bond, "name3", 2, "Text 3"),
                new Card("Name 4", special, null, 3, fog, "name4", 4, "Text 4")
                );

        List<List<Card>> expectedResultListOfList = Arrays.asList(
                Arrays.asList(  new Card("Name 1", skellige, null, 8, fog, "name1.jpg", 1, "Time to look death in the face."),
                                new Card("Name 2", skellige, null, 2, muster, "name2.jpg", 3, "Text 2")),
                Arrays.asList(  new Card("Name 3", weather, null, 5, bond, "name3.jpg", 2, "Text 3"),
                                new Card("Name 4", special, null, 3, fog, "name4.jpg", 4, "Text 4")));

        assertEquals(expectedResultListOfList.toString(), redrawObjectGenerator.halfList(newList).toString());
    }
}
