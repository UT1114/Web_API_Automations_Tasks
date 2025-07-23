package APIAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PostUserTest extends BaseTest {

    @Test
    public void testCreateUser() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "Pankaj Sharma");
        user.put("email", "pankaj" + System.currentTimeMillis() + "@damcosolutions.com");
        user.put("gender", "male");
        user.put("status", "active");

        Response response = given()
                .header("Authorization", TOKEN)
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().response();

        System.out.println("Post users response:");
        response.prettyPrint();

        assertEquals(user.get("name"), response.jsonPath().get("name"));
        assertEquals(user.get("email"), response.jsonPath().get("email"));
        assertEquals(user.get("gender"), response.jsonPath().get("gender"));
        assertEquals(user.get("status"), response.jsonPath().get("status"));
    }
}