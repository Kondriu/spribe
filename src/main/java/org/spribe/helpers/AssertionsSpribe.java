package org.spribe.helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.spribe.dto.PlayerDto;
import org.testng.Assert;

public class AssertionsSpribe {
    private static final Logger log = Logger.getLogger(AssertionsSpribe.class);

    @Step("Status code validation")
    public static void assertStatusCode(Response response) {
        log.info("validate response:" + response.prettyPeek().asString());
        int statusCode = response.getStatusCode();
        log.info("status code from response is: " + statusCode);
        Assert.assertEquals(statusCode, 200, "Can't validate expected status code!");
    }

    @Step("Status code validation {}")
    public static void assertStatusCode(Response response, Integer expectedCode) {
        log.info("validate response:" + response.prettyPeek().asString());
        int statusCode = response.getStatusCode();
        log.info("status code from response is: " + statusCode);
        Assert.assertEquals(statusCode, expectedCode, "Can't validate expected status code!");
    }

    @Step("Player 'Screen name' validation")
    public static void assertScreenName(String actualScreenName, String expectedScreenName) {
        log.info("Asserting parameter Screen Name");
        Assert.assertEquals(actualScreenName, expectedScreenName,
                "Can't validate expected user's screen name!");
    }

    @Step("Player 'Gender' validation")
    public static void assertGender(String actualGender, String expectedGender) {
        log.info("Asserting parameter Gender");
        Assert.assertEquals(actualGender, expectedGender,
                "Can't validate expected user's gender!");
    }

    @Step("Player 'Age' validation")
    public static void assertAge(Integer actualAge, String expectedAge) {
        log.info("Asserting parameter Age");
        Assert.assertEquals(actualAge.toString(), expectedAge,
                "Can't validate expected user's age!");
    }

    @Step("Number of registered players validation")
    public static void assertNumberOfPLayers(Integer actualNumberOfPlayers, Integer expectedNumberOfPLayers) {
        log.info("Asserting Number of registered players");
        Assert.assertEquals(actualNumberOfPlayers, expectedNumberOfPLayers,
                "Can't validate expected number of players!");
    }

    @Step("'Player id' validation")
    public static void assertPlayerId(Integer actualUserId, Integer expectedUserId) {
        log.info("Asserting player Id");
        Assert.assertEquals(actualUserId, expectedUserId,
                "Can't validate expected player id!");
    }

    @Step("Check the 'Player Id' is not null")
    public static void assertPlayerIdNotNull(Object objetsId) {
        log.info("Asserting player Id is set");
        Assert.assertNotNull(objetsId, "Absent 'Player Id' in object!");
    }

    @Step("Player 'Login' validation")
    public static void assertLogin(String actualLogin, String expectedLogin) {
        log.info("Asserting 'Player Login'");
        Assert.assertEquals(actualLogin, expectedLogin,
                "Can't validate expected 'Player Login'!");
    }

    @Step("Player 'Password' validation")
    public static void assertPassword(String actualPassword, String expectedPassword) {
        log.info("Asserting 'Player Password'");
        Assert.assertEquals(actualPassword, expectedPassword,
                "Can't validate expected 'Player Password'!");
    }

    @Step("Player 'Screen role' validation")
    public static void assertRole(String actualRole, String expectedRole) {
        log.info("Asserting 'Player role'");
        Assert.assertEquals(actualRole, expectedRole,
                "Can't validate expected 'Player role'!");

    }
}
