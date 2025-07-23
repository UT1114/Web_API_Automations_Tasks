package APIAutomation;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public static String TOKEN = "Bearer 749464ecfb2a9f3af74af52ae96026b02d4cd8f8788fe8f1fadfcc7867c06afb";

    @BeforeTest
    public static void setup() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
    }
}