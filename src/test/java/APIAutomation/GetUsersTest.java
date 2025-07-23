package APIAutomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetUsersTest extends BaseTest {

    @Test
    public void testGetUsers() {
        Response response = given()
                .header("Authorization", TOKEN)
                .relaxedHTTPSValidation()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Map<String, Object>> users = response.jsonPath().getList("");
        for (int i = 0; i < users.size(); i++) {
            Map<String, Object> user = users.get(i);
            assertEquals(user.get("status"), "active");
        }
    }}