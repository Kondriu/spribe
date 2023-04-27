package org.spribe;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.spribe.dto.PlayerCreateResponseDto;
import org.spribe.dto.PlayerDto;
import org.spribe.helpers.AssertionsSpribe;
import org.spribe.helpers.PlayerControllerFixture;
import org.testng.annotations.Test;

public class DeletePlayerTest extends BaseTest {

    @Test(threadPoolSize = 3)
    @Description(value = "Deleting player test")
    public void deletePlayerTest() {
        newPlayer = PlayerControllerFixture.getNewPlayer();
        PlayerCreateResponseDto registerPlayer = spribeService.getCreationNewPlayerResponseAsModel(spribeService.submitNewPlayer(newPlayer));
        Response response = spribeService.submitDeletePlayerCommand(registerPlayer.getId());
        AssertionsSpribe.assertStatusCode(response);
    }

    @Test(threadPoolSize = 3)
    @Description(value = "Deleting player test")
    public void deleteAbsentPlayerTest() {
        Response response = spribeService.submitDeletePlayerCommand(Integer.MAX_VALUE);
        AssertionsSpribe.assertStatusCode(response, 204);
    }

}
