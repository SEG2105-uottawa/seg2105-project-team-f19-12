package com.samarthsaxena.walkinclinicapp;

import androidx.test.rule.ActivityTestRule;

import com.samarthsaxena.walkinclinicapp.frontend.Patients.RateActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class RateServicesActivityTest {

    @Rule
    public ActivityTestRule<RateActivity> mActivityTestRule = new ActivityTestRule<>(RateActivity.class);

    @Test
    public void testWelcomeLabelInitialization() {
        onView(withId(R.id.welcome)).check(matches(withSubstring("Welcome")));
    }

    @Test
    public void testTextLabel1Initialization() {
        onView(withId(R.id.rate)).check(matches(withText("Rate the Employee")));
    }

    @Test
    public void testTextLabel2Initialization() {
        onView(withId(R.id.ename)).check(matches(withText("Description")));
    }


    @Test
    public void testButton1LabelInitialization() {
        onView(withId(R.id.submitrating)).check(matches(withText("Submit Rating")));
    }

    @Test
    public void testTextField1Initialization() {
        onView(withId(R.id.name)).check(matches(withText("Enter clinic....")));
    }

    @Test
    public void testTextField2Initialization() {
        onView(withId(R.id.descField)).check(matches(withText("Enter a comment...")));
    }

    @Test
    public void testRatingBarIsDisplayed() {
        onView(withId(R.id.rating)).check(matches(isDisplayed()));
    }


}
