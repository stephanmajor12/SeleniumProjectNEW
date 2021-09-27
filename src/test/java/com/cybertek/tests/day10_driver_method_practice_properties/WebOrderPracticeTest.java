package com.cybertek.tests.day10_driver_method_practice_properties;

import com.cybertek.utility.TestBase;
import com.cybertek.utility.WebOrderUtil;
import org.junit.jupiter.api.Test;

public class WebOrderPracticeTest extends TestBase {

    @Test
    public void testDriverPractice(){

        WebOrderUtil.openWebOrderApp();
        WebOrderUtil.login();
    }

}
