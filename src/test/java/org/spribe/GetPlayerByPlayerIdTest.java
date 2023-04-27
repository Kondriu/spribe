package org.spribe;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.spribe.dto.PlayerDto;
import org.spribe.dto.PlayerGetByPlayerIdResponseDto;
import org.spribe.helpers.AssertionsSpribe;
import org.spribe.helpers.PlayerControllerFixture;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

public class GetPlayerByPlayerIdTest extends BaseTest {
    @DataProvider(name = "listOfDefaultUsers", parallel = true)
    public Iterator<String[]> getListOfPairs() {
        return PlayerControllerFixture.getDefaultPlayersArguments();
    }

    @Test(dataProvider = "listOfDefaultUsers", threadPoolSize = 3)
    @Description(value = "Getting registered players by id")
    public void testGettingPlayersById(String[] arguments) {
        int id = Integer.parseInt(arguments[0]);
        String expectedName = arguments[1];
        Response response = spribeService.getUserById(id);
        PlayerGetByPlayerIdResponseDto player = spribeService.getUserByIdResponseAsModel(response);
        SoftAssertions.assertSoftly(softAssertions -> {
            AssertionsSpribe.assertStatusCode(response);
            AssertionsSpribe.assertPlayerId(player.getId(), id);
            AssertionsSpribe.assertScreenName(player.getScreenName(), expectedName);
        });
    }

}
