package at.moritzmusel.gwent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Root;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class MainMenuActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        onView(withId(R.id.imageViewWelcomeScreen)).perform((click()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_navigate_MainActivity() {
        //loading
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.imageView_play)).check(matches(isDisplayed()));
        onView(withId(R.id.imageView_settings)).check(matches(isDisplayed()));
        onView(withId(R.id.imageView_quit)).check(matches((isDisplayed())));
        onView(withId(R.id.iv_profile_pic_user)).check(matches((isDisplayed())));
        onView(withId(R.id.textView_username)).check(matches(isDisplayed()));
    }

    @Test
    public void test_popup_menu() {
        //loading
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.imageView_play)).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Host")).inRoot(isPopupWindow()).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //check if Activity changed
        onView(withId(R.id.iv_profile_pic_opponent)).check(matches(isDisplayed()));
    }
    public static Matcher<Root> isPopupWindow() {
        return isPlatformPopup();
    }
}
