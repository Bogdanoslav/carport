package com.rooter.carportv8.integration.rest;

import com.rooter.carportv8.Carportv8Application;
import com.rooter.carportv8.model.enums.ETripStatus;
import com.rooter.carportv8.payload.request.LoginRequest;
import com.rooter.carportv8.pojo.SaveTrip;
import com.rooter.carportv8.pojo.Trip;
import com.rooter.carportv8.service.AuthService;
import com.rooter.carportv8.util.FileUtils;
import com.rooter.carportv8.utils.FileJsonAssertion;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = {Carportv8Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TripsControllerTest {
    @LocalServerPort
    private Integer port;
    private RequestSpecification REQ_SPEC;
    @Autowired
    private AuthService authService;

    @BeforeAll
    public void setUp() {
        String BASE_URI = "http://localhost";
        REQ_SPEC =
                new RequestSpecBuilder()
                        .setBaseUri(BASE_URI)
                        .setPort(port)
                        .setContentType(ContentType.JSON)
                        .build();
    }

    @Test
    public void search_EmptyFilter_ReturnTrips(){
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/trips/search_no_filter.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/trips")
                .then()
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void search_pageZeroSize2_TwoElements(){
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/trips/search_no_filter.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/trips")
                .then()
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void search_filterByDepartureAndArrivalAddress_ReturnTrips(){
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/trips/search_no_filter.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/trips")
                .then()
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void search_filterByDepartureTime_ReturnTrips(){
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/trips/search_no_filter.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/trips")
                .then()
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void search_filterByMinFreeSeats_ReturnTrips(){
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/trips/search_no_filter.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/trips")
                .then()
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void search_SortByDepartureTime_ReturnTrips(){
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/trips/search_no_filter.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/trips")
                .then()
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void search_SortByPrice_ReturnTrips(){
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/trips/search_no_filter.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/trips")
                .then()
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void search_ValidPageableAndFilterByDepartureAndArrivalAddressAndSortByPrice_ReturnTrips(){
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/trips/search_no_filter.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/trips")
                .then()
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void getTripById_TripExist_Status200() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        JsonPath expectedJson = new JsonPath(getClass().getResourceAsStream("/tests/expectedJson/trips/getById_id_1.json"));
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/trips/1")
                .then()
                .body("", equalTo(expectedJson.getMap("")));
    }

    @Test
    public void getTripById_TripNotExist_Status404() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/trips/100")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void save_ValidSaveTrip_Status201ReturnNewTrip() throws JSONException {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        JsonPath saveTripJson = new JsonPath(getClass().getResourceAsStream("/tests/testInput/trips/save_trip.json"));
        SaveTrip saveTrip = saveTripJson.getObject("", SaveTrip.class);
        Trip newTrip = given()
                .spec(REQ_SPEC)
                .body(saveTrip)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/trips")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(Trip.class);
        Assertions.assertEquals(saveTrip.getDepartureAddress().getAddress(), newTrip.getDepartureAddress().getAddress());
        Assertions.assertEquals(saveTrip.getDepartureAddress().getCity(), newTrip.getDepartureAddress().getCity());
        Assertions.assertEquals(saveTrip.getDepartureAddress().getLongitude(), newTrip.getDepartureAddress().getLongitude());
        Assertions.assertEquals(saveTrip.getDepartureAddress().getLatitude(), newTrip.getDepartureAddress().getLatitude());
        Assertions.assertEquals(saveTrip.getArrivalAddress().getAddress(), newTrip.getArrivalAddress().getAddress());
        Assertions.assertEquals(saveTrip.getArrivalAddress().getCity(), newTrip.getArrivalAddress().getCity());
        Assertions.assertEquals(saveTrip.getArrivalAddress().getLongitude(), newTrip.getArrivalAddress().getLongitude());
        Assertions.assertEquals(saveTrip.getArrivalAddress().getLatitude(), newTrip.getArrivalAddress().getLatitude());
        Assertions.assertEquals(saveTrip.getDepartureTime(), newTrip.getDepartureTime());
        Assertions.assertEquals(saveTrip.getAbout(), newTrip.getAbout());
        Assertions.assertEquals(saveTrip.getTotalSeats(), newTrip.getTotalSeats());
        Assertions.assertEquals(0, newTrip.getBookedSeats());
        Assertions.assertEquals(ETripStatus.SCHEDULED, newTrip.getStatus());
        Assertions.assertNotNull(newTrip.getCreatedAt());
    }

    @Test
    public void save_ArrivalBeforeDeparture_Status409() throws JSONException {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        JsonPath saveTripJson = new JsonPath(getClass().getResourceAsStream("/tests/testInput/trips/save_trip_arrival_before_departure.json"));
        SaveTrip saveTrip = saveTripJson.getObject("", SaveTrip.class);
        given()
                .spec(REQ_SPEC)
                .body(saveTrip)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/trips")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void save_UserIsNotDriver_Status404() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", false)).getToken();
        JsonPath saveTripJson = new JsonPath(getClass().getResourceAsStream("/tests/testInput/trips/save_trip.json"));
        SaveTrip saveTrip = saveTripJson.getObject("", SaveTrip.class);
        given()
                .spec(REQ_SPEC)
                .body(saveTrip)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/trips")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

}
