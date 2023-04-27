package org.spribe;


import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.spribe.dto.PlayerCreateResponseDto;
import org.spribe.dto.PlayerDto;
import org.spribe.dto.PlayerUpdateResponseDto;
import org.spribe.helpers.AssertionsSpribe;
import org.spribe.helpers.PlayerControllerFixture;
import org.spribe.models.PlayerModel;
import org.testng.annotations.Test;

public class UpdatePlayerTest extends BaseTest {

    @Test(threadPoolSize = 3)
    @Description(value = "Create player and update properties")
    public void updatePlayerDetailsTest() {
        newPlayer = PlayerControllerFixture.getNewPlayer();
        PlayerCreateResponseDto registerPlayer = spribeService.getCreationNewPlayerResponseAsModel(spribeService.submitNewPlayer(newPlayer));
        PlayerModel updatedPLayerModel = PlayerControllerFixture.getNewPlayer();
        PlayerUpdateResponseDto updatedPlayer = spribeService.getUpdatePlayerResponseAsModel(spribeService.updatePlayer(registerPlayer.getId(), updatedPLayerModel));

        SoftAssertions.assertSoftly(softAssertions -> {
            AssertionsSpribe.assertPlayerId(updatedPlayer.getId(), registerPlayer.getId());
            AssertionsSpribe.assertScreenName(updatedPlayer.getScreenName(), newPlayer.getScreenName());
            AssertionsSpribe.assertAge(updatedPlayer.getAge(), newPlayer.getAge());
            AssertionsSpribe.assertGender(updatedPlayer.getGender(), newPlayer.getGender());
            AssertionsSpribe.assertLogin(updatedPlayer.getLogin(), newPlayer.getLogin());
        });

    }
}
