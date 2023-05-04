package at.moritzmusel.gwent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainMenuTest {
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
        onView(withId(R.id.imageViewWelcomeScreen)).check(matches(isDisplayed()));

        onView(withId(R.id.imageViewWelcomeScreen)).perform((click()));
    }

    @Test
    public void test_navigate_LoadingActivity() {
        onView(withId(R.id.imageViewWelcomeScreen)).perform((click()));

        onView(withId(R.id.textView)).check(matches(isDisplayed()));
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
    }

    @Test
    public void test_navigate_MainActivity() {
        //onView(withId(R.id.imageViewWelcomeScreen)).perform((click()));

        onView(withId(R.id.imageView_play)).check(matches(isDisplayed()));
        onView(withId(R.id.imageView_settings)).check(matches(isDisplayed()));
        onView(withId(R.id.imageView_quit)).check(matches((isDisplayed())));
        onView(withId(R.id.iv_profile_pic_user)).check(matches((isDisplayed())));
        onView(withId(R.id.textView_username)).check(matches(isDisplayed()));

        onView(withId(R.id.imageView_play)).perform((click()));
        //onView(withId(R.menu.popup_menu)).check(matches(isDisplayed()));
    }
}
