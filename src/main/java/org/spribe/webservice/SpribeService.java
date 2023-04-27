package org.spribe.webservice;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import org.spribe.dto.*;
import org.spribe.helpers.PropertiesReader;
import org.spribe.models.PlayerModel;

import java.util.HashMap;
import java.util.Map;

public class SpribeService {

    private static final Logger log = Logger.getLogger(SpribeService.class.getName());
    private PropertiesReader propertiesReader = new PropertiesReader("spribe.properties");
    private String editor = propertiesReader.getValue("editor");
    private static final String CREATE_PLAYER = "player/create/"; //v2
    private static final String GET_All_PLAYERS_URL = "player/get/all";
    private static final String GET_PLAYER_BY_ID_URL = "player/get";
    private static final String DELETE_PLAYER_URL = "player/delete/";
    private static final String UPDATE_PLAYER = "/player/update/";


    public SpribeService() {
        RestAssured.baseURI = propertiesReader.getValue("url");
        RestAssured.registerParser("text/html", Parser.JSON);
    }

    @Step("Call 'Get all users' endpoint")
    public Response getAllUsers() {
        log.info("Call '/player/get/all' endpoint");
        Response response = RestAssured.given()
                .log().all()
                .header("accept", "*/*")
                .header("Content-Type", "application/json")
                .get(GET_All_PLAYERS_URL)
                .andReturn();
        log.info("Call api: " + RestAssured.baseURI + GET_All_PLAYERS_URL);
        log.info("Respond status code is: " + response.getStatusCode());
        log.info(response.getBody());

        return response;
    }

    @Step("Return response as List of POJO")
    public AllPlayersArrayDto getAllUsersResponseDtoArray() {
        log.info("convert response to the POJO");
        return getAllUsers().as(AllPlayersArrayDto.class);
    }

    @Step("Return response as List of POJO")
    public AllPlayersArrayDto getAllUsersResponseDtoArray(Response response) {
        log.info("convert response to the POJO");
        return response.as(AllPlayersArrayDto.class);
    }

    @Step("Get player by id {0}")
    public Response getUserById(Integer id) {
        log.info("Getting player by id: " + id);
        Map<String, Object> body = new HashMap<>();
        body.put("playerId", id);

        Response response = RestAssured.given()
                .log().all()
                .header("accept", "*/*")
                .header("Content-Type", "application/json")
                .body(body)
                .post(GET_PLAYER_BY_ID_URL)
                .andReturn();
        log.info("Call api: " + RestAssured.baseURI + GET_PLAYER_BY_ID_URL);
        log.info("Respond status code is: " + response.getStatusCode());
        log.info(response.getBody());


        return response;
    }

    @Step("Get player as POJO model")
    public PlayerGetByPlayerIdResponseDto getUserByIdResponseAsModel(Response response) {
        log.info("Getting player by id response as POJO model");
        return response.as(PlayerGetByPlayerIdResponseDto.class);
    }

    @Step("Submit new player")
    public Response submitNewPlayer(PlayerModel newPlayer) {
        Response response = RestAssured.given()
                .log().all()
                .header("accept", "*/*")
                .header("Content-Type", "application/json")
                .queryParam("age", newPlayer.getAge())
                .queryParam("gender", newPlayer.getGender())
                .queryParam("login", newPlayer.getLogin())
                .queryParam("password", newPlayer.getPassword())
                .queryParam("role", newPlayer.getRole())
                .queryParam("screenName", newPlayer.getScreenName())
                .get(CREATE_PLAYER + newPlayer.getEditor())
                .andReturn();
        log.info("call api: " + RestAssured.baseURI + CREATE_PLAYER);
        log.info("Respond status code is: " + response.getStatusCode());
        log.info(response.getBody().asPrettyString());
        log.info(response.getBody());
        return response;
    }

    @Step("Get new player creation response as POJO model")
    public PlayerCreateResponseDto getCreationNewPlayerResponseAsModel(Response response) {
        log.info("Get new player creation response as POJO model");
        return response.as(PlayerCreateResponseDto.class);
    }

    @Step("Delete player")
    public Response submitDeletePlayerCommand(Integer id) {
        Map<String, Object> body = new HashMap<>();
        body.put("playerId", id);

        Response response = RestAssured.given()
                .log().all()
                .header("accept", "*/*")
                .header("Content-Type", "application/json")
                .body(body)
                .delete(DELETE_PLAYER_URL + this.editor)
                .andReturn();
        log.info("Deleting user with id:  " + id);
        log.info("Call api: " + RestAssured.baseURI + DELETE_PLAYER_URL + this.editor);
        log.info("Respond status code is: " + response.getStatusCode());
        log.info(response.getBody());

        return response;
    }

    @Step("Update player by id: {0}")
    public Response updatePlayer(Integer id, PlayerModel player) {
        Map<String, Object> body = new HashMap<>();
        body.put("age", player.getAge());
        body.put("gender", player.getGender());
        body.put("login", player.getLogin());
        body.put("password", player.getPassword());
        body.put("role", player.getRole());
        body.put("screenName", player.getScreenName());


        Response response = RestAssured.given()
                .log().all()
                .header("accept", "*/*")
                .header("Content-Type", "application/json")
                .body(body)
                .patch(UPDATE_PLAYER + this.editor + id)
                .andReturn();

        log.info("Updating player details with player id: " + id);
        log.info("Call api: " + RestAssured.baseURI + UPDATE_PLAYER + this.editor);
        log.info("Respond status code is: " + response.getStatusCode());
        log.info(response.getBody());

        return response;
    }

    @Step("Get player update response as POJO model")
    public PlayerUpdateResponseDto getUpdatePlayerResponseAsModel(Response response) {
        log.info("Get player update response as POJO model");
        return response.as(PlayerUpdateResponseDto.class);
    }

}
