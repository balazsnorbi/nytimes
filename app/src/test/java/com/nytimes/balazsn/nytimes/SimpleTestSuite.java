package com.nytimes.balazsn.nytimes;

import com.nytimes.balazsn.nytimes.list.ResultClickedEvent;
import com.nytimes.balazsn.nytimes.splashscreen.GoToActivityEvent;
import com.nytimes.balazsn.nytimes.splashscreen.view.SplashActivity;
import com.nytimes.balazsn.pojo.Result;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SimpleTestSuite {

    private GoToActivityEvent mGoToActivityEvent;
    private ResultClickedEvent mResultClickedEvent;

    @Mock
    private Result result;

    @Before
    public void beforeTest() {
        mGoToActivityEvent = new GoToActivityEvent(SplashActivity.class);
        mResultClickedEvent = new ResultClickedEvent(result);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(SplashActivity.class, mGoToActivityEvent.getmNextClass());
        assertEquals(result, mResultClickedEvent.getResult());
    }

    @After
    public void afterTest() {
        mGoToActivityEvent = null;
    }
}