package org.spribe;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.spribe.dto.PlayerCreateResponseDto;
import org.spribe.dto.PlayerDto;
import org.spribe.dto.PlayerItemDto;
import org.spribe.helpers.AssertionsSpribe;
import org.spribe.helpers.PlayerControllerFixture;
import org.testng.annotations.Test;

import java.util.List;

public class CreatePlayerTest extends BaseTest {
    @Test(threadPoolSize = 3)
    @Description(value = "Submit new player")
    public void testSubmitNewPlayer() {
        newPlayer = PlayerControllerFixture.getNewPlayer();
        Response response = spribeService.submitNewPlayer(newPlayer);
        PlayerCreateResponseDto newPlayerModel = spribeService.getCreationNewPlayerResponseAsModel(response);
        SoftAssertions.assertSoftly(softAssertions -> {
            AssertionsSpribe.assertStatusCode(response);
            AssertionsSpribe.assertPlayerIdNotNull(newPlayerModel.getId());
            AssertionsSpribe.assertScreenName(newPlayerModel.getScreenName(), newPlayer.getScreenName());
            AssertionsSpribe.assertAge(newPlayerModel.getAge(), newPlayer.getAge());
            AssertionsSpribe.assertGender(newPlayerModel.getGender(), newPlayer.getGender());
            AssertionsSpribe.assertLogin(newPlayerModel.getLogin(), newPlayer.getLogin());
            AssertionsSpribe.assertPassword(newPlayerModel.getPassword(), newPlayer.getPassword());
            AssertionsSpribe.assertRole(newPlayerModel.getRole(), newPlayer.getRole());
        });
    }

    @Test(threadPoolSize = 3)
    @Description(value = "Validate creation of new player")
    public void createNewPLayerTest() {
        newPlayer = PlayerControllerFixture.getNewPlayer();
        Response response = spribeService.submitNewPlayer(newPlayer);
        PlayerCreateResponseDto registerPlayer = spribeService.getCreationNewPlayerResponseAsModel(response);
        List<PlayerItemDto> players = spribeService.getAllUsersResponseDtoArray().getPlayers();
        PlayerItemDto last = players.get(players.size() - 1);
        SoftAssertions.assertSoftly(softAssertions -> {
            AssertionsSpribe.assertStatusCode(response);
            AssertionsSpribe.assertScreenName(last.getScreenName(), newPlayer.getScreenName());
            AssertionsSpribe.assertAge(last.getAge(), newPlayer.getAge());
            AssertionsSpribe.assertGender(last.getGender(), newPlayer.getGender());
            AssertionsSpribe.assertPlayerId(last.getId(), registerPlayer.getId());
        });
    }

}
