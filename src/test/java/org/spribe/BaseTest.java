package org.spribe;

import org.spribe.helpers.PlayerControllerFixture;
import org.spribe.models.PlayerModel;
import org.spribe.webservice.SpribeService;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    SpribeService spribeService = new SpribeService();
    PlayerModel newPlayer;
    PlayerModel defaultPlayer;

    @BeforeClass
    public void generateData() {
        defaultPlayer = PlayerControllerFixture.getDefaultPlayer();
    }

}
