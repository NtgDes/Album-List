package android.albumlist;

import android.content.Context;

import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4ClassRunner.class)
public class AlbumActivityTest {
	@Test
	public void useAppContext() {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
		assertEquals("android.albumlist", appContext.getPackageName());
	}

	@Test
	public void Activity_Displayed () {
		ActivityScenario<AlbumActivity> activityScenario = ActivityScenario.launch(AlbumActivity.class);
		assertNotNull(activityScenario);

		onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
	}
	@Test
	public void ActionBarTitle_Displayed() {
		ActivityScenario.launch(AlbumActivity.class);
		ViewInteraction textView = onView(
				allOf(withText("Album List"),
						withParent(allOf(withId(R.id.action_bar),
								withParent(withId(R.id.action_bar_container)))),
						isDisplayed()));
		textView.check(matches(withText("Album List")));
	}


}