package eu.zvireciliga.teamlottery;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTestBase extends CommonTestBase
{
    @Rule
    public ActivityTestRule<MainActivity_> activityTestRule = new ActivityTestRule<>(MainActivity_.class);

    @Test
    public void mainElementsDisplayed()
    {
        onView(allOf(withId(R.id.teamList), isDisplayed())).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.progressBar), isDisplayed())).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fab), isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void testSaveDraw()
    {
        onView(allOf(withId(R.id.fab), isDisplayed())).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fab), isDisplayed())).perform(click());
        onView(allOf(withText("MALE"), isDisplayed())).perform(click());
        onView(allOf(childAtPosition(withId(R.id.gridView), 0), isDisplayed())).perform(click());

        onView(allOf(withId(android.R.id.button1), withText("Save"),
            withParent(allOf(
                withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                withParent(withClassName(is("android.widget.LinearLayout")))
            )),
            isDisplayed())
        ).perform(click());

        onView(allOf(withId(R.id.fab), isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void testCancelDraw()
    {
        onView(allOf(withId(R.id.fab), isDisplayed())).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fab), isDisplayed())).perform(click());
        onView(allOf(withText("MALE"), isDisplayed())).perform(click());
        onView(allOf(childAtPosition(withId(R.id.gridView), 0), isDisplayed())).perform(click());

        onView(allOf(withId(android.R.id.button2), withText("Cancel"),
            withParent(allOf(
                withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                withParent(withClassName(is("android.widget.LinearLayout")))
            )),
            isDisplayed())
        ).perform(click());

        onView(allOf(withId(R.id.fab), isDisplayed())).check(matches(isDisplayed()));
    }
}
