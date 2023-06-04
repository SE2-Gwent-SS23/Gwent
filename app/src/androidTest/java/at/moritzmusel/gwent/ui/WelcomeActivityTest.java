package at.moritzmusel.gwent.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import at.moritzmusel.gwent.MainActivity;
import at.moritzmusel.gwent.R;

public class WelcomeActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_navigate_WelcomeActivity() {
        onView(ViewMatchers.withId(R.id.imageViewWelcomeScreen)).check(matches(isDisplayed()));
    }
}
