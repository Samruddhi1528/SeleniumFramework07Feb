package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void verifyLoginWithInvalidCredentials() {
        loginPage.performLogin("invaliduser@test.com", "InvalidPassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 2)
    public void verifyLoginWithEmptyUsername() {
        loginPage.performLogin("", "SomePassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 3)
    public void verifyLoginWithEmptyPassword() {
        loginPage.performLogin("someuser@test.com", "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 4)
    public void verifyLoginWithEmptyCredentials() {
        loginPage.performLogin("", "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 5)
    public void verifyLoginWithInvalidEmailFormat() {
        loginPage.performLogin("invalidemailformat", "SomePassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 6)
    public void verifyLoginWithSpecialCharactersInUsername() {
        loginPage.performLogin("!@#$%^&*()", "SomePassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 7)
    public void verifyLoginWithSpacesOnlyInUsername() {
        loginPage.performLogin("     ", "SomePassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 8)
    public void verifyLoginWithSpacesOnlyInPassword() {
        loginPage.performLogin("someuser@test.com", "     ");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 9)
    public void verifyErrorMessageContainsText() {
        loginPage.performLogin("invaliduser@test.com", "WrongPassword");
        String errorText = loginPage.getErrorMessage();
        Assert.assertFalse(errorText.isEmpty());
    }

    @Test(priority = 10)
    public void verifyUserStaysOnLoginPageAfterInvalidLogin() {
        loginPage.performLogin("invaliduser@test.com", "InvalidPassword");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("login.salesforce.com"));
    }
}
