package at.moritzmusel.gwent.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class CardTest {

    private Card card1;

    @BeforeEach
    private void init() {
        card1 = new Card();
    }

    @Test
    private void testCreateNewCard(Card card) {
        Assertions.assertEquals(card1, card);
    }

}
