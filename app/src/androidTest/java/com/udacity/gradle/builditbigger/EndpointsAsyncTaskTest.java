package com.udacity.gradle.builditbigger;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.app.Application;
import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void verifyButtonResponse() {

        onView(withId(R.id.fragment_main_button)).perform(click());

        onView(withId(R.id.fragment_joke_display_textview))
                .check(matches(
                        new TypeSafeMatcher<View>(){
                            @Override
                            protected boolean matchesSafely(View item) {
                                TextView textView = (TextView) item;
                                String value = textView.getText().toString();
                                return 0 < value.length() && !value.startsWith("Failed to connect to");
                            }

                            @Override
                            public void describeTo(Description description) {
                            }
                        }
                ));
    }
}






