package at.moritzmusel.gwent.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import at.moritzmusel.gwent.network.data.GameState;

public class RedrawObjectGenerator {

    private static RedrawObjectGenerator redrawObjectGeneratorInstance;

    public static RedrawObjectGenerator getInstance() {
        if (redrawObjectGeneratorInstance == null) {
            redrawObjectGeneratorInstance = new RedrawObjectGenerator();
        }
        return redrawObjectGeneratorInstance;
    }

    public List<List<Card>> halveList(List<Card> list) {
        int marker = list.size() / 2;
        List<Card> firstHalf = new ArrayList<>();
        List<Card> secondHalf = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (i < marker) {
                firstHalf.add(list.get(i));
            } else {
                secondHalf.add(list.get(i));
            }
        }
        List<List<Card>> result = new ArrayList<>();
        result.add(firstHalf);
        result.add(secondHalf);

        return result;
    }

    public ReturnCard drawRandomCard(GameState gameState) {
        SecureRandom random = new SecureRandom();
        int zz = 0;
        Card card = null;
        if(!gameState.getAllCards().isEmpty()) {
            zz = random.nextInt(gameState.getAllCards().size());
            card = gameState.getAllCards().get(zz);

            while (card.getCount() == 0) {
                zz = random.nextInt(gameState.getAllCards().size());
                card = gameState.getAllCards().get(zz);
            }
            gameState.getAllCards().get(zz).setCount(card.getCount() - 1);
        }

        return new ReturnCard(gameState, card);
    }
}
