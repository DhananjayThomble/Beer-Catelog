package com.ddtinfotech.beercatalog;

import org.junit.Test;

import static org.junit.Assert.*;

import com.ddtinfotech.beercatalog.util.UtilityClass;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    // checking getRandomNum() from MainActivity.java
    @Test
    public void getRandomNum( ) {

        UtilityClass utilityClass = new UtilityClass();
        int randomNum = utilityClass.getRandomNum();
        assertTrue(randomNum >= 2 && randomNum <= 100);
    }
}