package testng.tech;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import org.testng.annotations.Test;

public class Topic_01_Annotation {
    @Test
    public void Register ()
    {
        System.out.println("Register function");
    }

    @Test
    public void Login () {
        System.out.println("Login function");
        Assert.assertEquals("", "");
    }

    @BeforeMethod //chay 1 lan truoc tung testcase
    public void beforeMethod () {
        System.out.println("Before Method");
    }

    @AfterMethod //chay 1 lan sau tung testcase
    public void afterMethod () {
        System.out.println("After Method");
    }

    @AfterClass
    public void afterClass () {
        System.out.println("After Class");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
    }

    @BeforeSuite // chay 1 lan truoc all testcase/method
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @AfterSuite // chay 1 lan sau all testcase/method
    public void afterSuite() {
        System.out.println("After Suite");
    }





}
