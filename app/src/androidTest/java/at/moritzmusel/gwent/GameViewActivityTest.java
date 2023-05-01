package at.moritzmusel.gwent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

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
}
