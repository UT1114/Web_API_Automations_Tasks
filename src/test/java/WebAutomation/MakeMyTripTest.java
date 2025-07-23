package WebAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MakeMyTripTest {

    WebDriver driver;
    MakeMyTripPage page;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/flights/");
        page = new MakeMyTripPage(driver);
    }

    @Test
    public void testFlight() {
        page.closeInitialPopup();
        page.enterFromCity("Delhi");
        page.selectCityFromDropdown("Delhi");
        page.enterToCity("Mumbai");
        page.selectCityFromDropdown("Mumbai");
        page.selectDate();
        page.clickSearch();
        page.closeGotItPopup();
        page.sortByEarlyDeparture();
        page.printSecondLowestFlightDetails();
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}