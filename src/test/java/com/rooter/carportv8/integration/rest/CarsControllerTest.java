package com.rooter.carportv8.integration.rest;

import com.rooter.carportv8.Carportv8Application;
import com.rooter.carportv8.dto.car.PartialUpdateCar;
import com.rooter.carportv8.dto.car.SaveCar;
import com.rooter.carportv8.model.enums.EBodyType;
import com.rooter.carportv8.model.enums.EColor;
import com.rooter.carportv8.payload.request.LoginRequest;
import com.rooter.carportv8.service.AuthService;
import com.rooter.carportv8.util.FileUtils;
import com.rooter.carportv8.utils.FileJsonAssertion;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = {Carportv8Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarsControllerTest {


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
    public void save_CarCreated_Status200AndShouldReturnNewCar() {
        SaveCar saveCar = new SaveCar(1L, EColor.BLACK, EBodyType.SEDAN);
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/cars/save.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .body(saveCar)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/cars")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);

    }

    @Test
    public void save_UserIsNotDriver_Status403() {
        SaveCar saveCar = new SaveCar(1L, EColor.BLACK, EBodyType.SEDAN);
        String token = authService.login(new LoginRequest("user_role1", "user_role1", false)).getToken();
        given()
                .spec(REQ_SPEC)
                .body(saveCar)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/cars")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void getCarsByOwnerId_CarsBelongToUser_Status200()  {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/cars/getByOwnerId.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/cars")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void getById_UserIsOwner_Status200()  {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/cars/getById_id_1.json");
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .get("/cars/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void getById_CarNotExists_Status404() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/cars/100")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }



    @Test
    public void getById_UserNotOwner_Status404() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/cars/3")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }



    @Test
    public void getById_UserIsNotDriver_Status403() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", false)).getToken();
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)                .when()
                .get("/cars/1")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void delete_CarAttachedToActiveTrip_Status409() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/cars/1")
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    public void delete_CarNOTAttachedToActiveTrip_Status201() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/cars/10")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void delete_UserIsNotDriver_Status403() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", false)).getToken();
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/cars/2")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void delete_UserNotOwner_Status403() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/cars/3")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void delete_CarNotFound_Status404() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/cars/200")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void patch_UserNotOwner_Status403() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        PartialUpdateCar partialUpdateCar = new PartialUpdateCar(EColor.BLACK);
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .body(partialUpdateCar)
                .when()
                .patch("/cars/3")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void patch_UserIsOwner_Status200AndCarChanged() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        String expectedResponseString = FileUtils.getFileContent("/tests/expectedJson/cars/patch_id_10.json");

        PartialUpdateCar partialUpdateCar = new PartialUpdateCar(EColor.BLACK);
        String actualResponseString = given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .body(partialUpdateCar)
                .when()
                .patch("/cars/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().asString();
        FileJsonAssertion.assertJsonEquals(expectedResponseString, actualResponseString);
    }

    @Test
    public void patch_UserIsNotDriver_Status403() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", false)).getToken();
        PartialUpdateCar partialUpdateCar = new PartialUpdateCar(EColor.BLACK);
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .body(partialUpdateCar)
                .when()
                .patch("/cars/1")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void patch_CarNotFound_Status200AndCarChanged() {
        String token = authService.login(new LoginRequest("user_role1", "user_role1", true)).getToken();
        PartialUpdateCar partialUpdateCar = new PartialUpdateCar(EColor.BLACK);
        given()
                .spec(REQ_SPEC)
                .header("Authorization", "Bearer " + token)
                .body(partialUpdateCar)
                .when()
                .patch("/cars/100")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }


}
