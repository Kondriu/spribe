package org.spribe;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.spribe.dto.PlayerItemDto;
import org.spribe.helpers.AssertionsSpribe;
import org.testng.annotations.Test;

import java.util.List;

public class GetAllPlayersTest extends BaseTest {
    @Test(threadPoolSize = 3)
    @Description(value = "Validate presence default user")
    public void testGettingDefaultPlayer() {
        Response response = spribeService.getAllUsers();
        response.print();
        PlayerItemDto firstPlayer = spribeService.getAllUsersResponseDtoArray(response).getPlayers().get(0);

        SoftAssertions.assertSoftly(softAssertions -> {
            AssertionsSpribe.assertStatusCode(response);
            AssertionsSpribe.assertScreenName(firstPlayer.getScreenName(), defaultPlayer.getScreenName());
            AssertionsSpribe.assertGender(firstPlayer.getGender(), defaultPlayer.getGender());
            AssertionsSpribe.assertAge(firstPlayer.getAge(), defaultPlayer.getAge());
        });
    }

    @Test(threadPoolSize = 3)
    @Description(value = "Validation number of registered users")
    public void testNumberOfRegisteredPlayers() {
        Response response = spribeService.getAllUsers();
        List<PlayerItemDto> players = spribeService.getAllUsersResponseDtoArray(response).getPlayers();
        SoftAssertions.assertSoftly(softAssertions -> {
            AssertionsSpribe.assertStatusCode(response);
            AssertionsSpribe.assertNumberOfPLayers(players.size(), 99);
        });
    }
}
