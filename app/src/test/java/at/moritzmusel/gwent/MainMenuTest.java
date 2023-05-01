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
        Espresso.onView(ViewMatchers.withId(R.id.imageViewWelcomeScreen)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.imageViewWelcomeScreen)).perform((ViewActions.click()));
    }

    @Test
    public void test_navigate_LoadingActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.imageViewWelcomeScreen)).perform((ViewActions.click()));

        Espresso.onView(ViewMatchers.withId(R.id.textView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void test_navigate_MainActivity() {
        //onView(withId(R.id.imageViewWelcomeScreen)).perform((click()));

        Espresso.onView(ViewMatchers.withId(R.id.imageView_play)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.imageView_settings)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.imageView_quit)).check(ViewAssertions.matches((ViewMatchers.isDisplayed())));
        Espresso.onView(ViewMatchers.withId(R.id.iv_profile_pic_user)).check(ViewAssertions.matches((ViewMatchers.isDisplayed())));
        Espresso.onView(ViewMatchers.withId(R.id.textView_username)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.imageView_play)).perform((ViewActions.click()));
        //onView(withId(R.menu.popup_menu)).check(matches(isDisplayed()));
    }
}
