import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;


public class RegisterTests {

    @Test
    void successfulRegisterTest() {
        String registerData = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        given()
                .body(registerData)
                .contentType(JSON)
                .log().uri()

        .when()
                .post("https://reqres.in/api/register")

        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4),
                        "token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void registerMissingPasswordTest() {
        String registerData = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"\"\n" +
                "}";
        given()
                .body(registerData)
                .contentType(JSON)
                .log().uri()

        .when()
                .post("https://reqres.in/api/register")

        .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void registerMissingEmailTest() {
        String registerData = "{\n" +
                "    \"email\": \"\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        given()
                .body(registerData)
                .contentType(JSON)
                .log().uri()

        .when()
                .post("https://reqres.in/api/register")

        .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void registerMissingEmailAndPasswordTest() {
        String registerData = "{\n" +
                "    \"email\": \"\",\n" +
                "    \"password\": \"\"\n" +
                "}";
        given()
                .body(registerData)
                .contentType(JSON)
                .log().uri()

        .when()
                .post("https://reqres.in/api/register")

        .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void registerMissingAtSymbolInEmaiTest() {
        String registerData = "{\n" +
                "    \"email\": \"eve.holtreqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        given()
                .body(registerData)
                .contentType(JSON)
                .log().uri()

        .when()
                .post("https://reqres.in/api/register")

        .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Note: Only defined users succeed registration"));

    }

}
