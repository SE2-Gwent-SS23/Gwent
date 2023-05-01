package at.moritzmusel.gwent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import at.moritzmusel.gwent.ui.GameViewActivity;

public class GameViewActivityTest {

    @Rule
    public ActivityScenarioRule<GameViewActivity> activityRule = new ActivityScenarioRule<>(GameViewActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_navigateToGameView() {
        Espresso.onView(ViewMatchers.withId(R.id.imageViewWelcomeScreen)).perform((ViewActions.click()));
        Espresso.onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
