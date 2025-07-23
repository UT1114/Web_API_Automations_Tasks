package APIAutomation;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeleteUserTest extends BaseTest {

    @Test
    public void testDeleteUser() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "Pankaj Sharma");
        user.put("email", "pankaj" + System.currentTimeMillis() + "@damcosolutions.com");
        user.put("gender", "male");
        user.put("status", "active");


        // Creating a user
        int userId = given()
                .header("Authorization", TOKEN)
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        //Deleting the user
        given()
                .header("Authorization", TOKEN)
                .relaxedHTTPSValidation()
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);

        System.out.println("Delete user" + userId + " passed.......");
    }
}
