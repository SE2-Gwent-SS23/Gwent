package at.moritzmusel.gwent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.moritzmusel.gwent.ui.WelcomeActivity;

@RunWith(AndroidJUnit4.class)
public class WelcomeActivityTest {

    @Rule
    public ActivityScenarioRule<WelcomeActivity> welcomeActivityScenarioRule
            = new ActivityScenarioRule<>(WelcomeActivity.class);

    @Test
    public void clickWelcomeScreenImage() {
        onView(withId(R.id.imageViewWelcomeScreen)).perform(click());
    }
}
