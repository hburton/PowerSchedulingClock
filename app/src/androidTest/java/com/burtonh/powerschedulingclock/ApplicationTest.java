package com.burtonh.powerschedulingclock;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.Assert;

import java.util.Calendar;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void getMinuteTest(){
        Assert.assertEquals(Calendar.getInstance().get(Calendar.MINUTE), Utilities.getMinute() - 1);
    }
}