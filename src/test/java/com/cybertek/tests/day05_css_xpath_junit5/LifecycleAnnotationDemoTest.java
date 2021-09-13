package com.cybertek.tests.day05_css_xpath_junit5;

import org.junit.jupiter.api.*;

public class LifecycleAnnotationDemoTest {

    @BeforeAll
    public static void init(){
        System.out.println("@BeforeAll run once before all test");
    }

    @BeforeEach
    public void setup(){
        System.out.println("@BeforeEach run before each and every test");
    }

    @Test
    public void test1(){
        System.out.println("test1 method is running");
    }

    @Test
    public void test2(){
        System.out.println("test2 method is running");
    }

    @AfterEach
    public void teardown(){
        System.out.println("@AfterEach run after each and every test");
    }

    @AfterAll
    public static void cleanup(){
        System.out.println("@AfterAll run once after all test");
    }

}
