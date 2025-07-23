package WebAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


    public class MakeMyTripPage {
        WebDriver driver;
        WebDriverWait wait;

        public MakeMyTripPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

        By closePopup = By.xpath("//span[@class='commonModal__close']");
        By gotItPopup = By.xpath("//span[text()='GOT IT']");
        By fromCity = By.id("fromCity");
        By fromTextBox = By.xpath("//input[@placeholder='From']");
        By toCity = By.id("toCity");
        By toTextBox = By.xpath("//input[@placeholder='To']");
        By cityDropdownOptions = By.xpath("//span[@class='makeFlex flexOne spaceBetween appendRight10']/span");
        By selectDate = By.xpath("//div[@class='DayPicker-Day DayPicker-Day--selected']");
        By searchButton = By.xpath("//p[@data-cy='submit']");
        By otherSortButton=By.xpath("//p[text()='Other Sort']");
        By earlyDepartureButton=By.xpath("//span[text()='Early Departure']");


        By flightNames = By.xpath("//p[@class='boldFont blackText airlineName']");
        By flightPrices = By.xpath("//span[@class=\" fontSize18 blackFont\" and contains(text(),'₹')]");




        public void closeInitialPopup() {
            try {
                WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(closePopup));
                popup.click();
            } catch (Exception e) {
                System.out.println("No initial popup displayed.");
            }
        }

        public void enterFromCity(String city) {
            wait.until(ExpectedConditions.elementToBeClickable(fromCity)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(fromTextBox)).sendKeys(city);
        }

        public void enterToCity(String city) {
            wait.until(ExpectedConditions.elementToBeClickable(toCity)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(toTextBox)).sendKeys(city);
        }

        public void selectCityFromDropdown(String city) {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(cityDropdownOptions, 0));
            List<WebElement> options = driver.findElements(cityDropdownOptions);
            for (WebElement option : options) {
                if (option.getText().contains(city)) {
                    option.click();
                    break;
                }
            }
        }

        public void selectDate() {
            wait.until(ExpectedConditions.elementToBeClickable(selectDate)).click();
        }

        public void clickSearch() {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        }

        public void closeGotItPopup() {
            try {
                WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(gotItPopup));
                popup.click();
            } catch (Exception e) {
                System.out.println("No 'Got It' popup displayed.");
            }
        }

        public void sortByEarlyDeparture() {
            wait.until(ExpectedConditions.elementToBeClickable(otherSortButton)).click();
            wait.until(ExpectedConditions.elementToBeClickable(earlyDepartureButton)).click();
            System.out.println("Flights sorted by early departure time.......");
        }

        public void printSecondLowestFlightDetails() {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(flightPrices));

            List<WebElement> prices = driver.findElements(flightPrices);
            List<WebElement> names = driver.findElements(flightNames);

            if (prices.size() >= 2 && names.size() >= 2) {
                List<Integer> priceValues = new ArrayList<>();

                for (WebElement e : prices) {
                    String priceText = e.getText().replaceAll("[^0-9]", "");
                    if (!priceText.isEmpty()) {
                        priceValues.add(Integer.parseInt(priceText));
                    }
                }

                Collections.sort(priceValues);

                int secondLowestPrice = priceValues.get(1);

                for (int i = 0; i < prices.size(); i++) {
                    String text = prices.get(i).getText().replaceAll("[^0-9]", "");
                    int price = Integer.parseInt(text);
                    if (price == secondLowestPrice) {
                        System.out.println("Second lowest priced flight:");
                        System.out.println("Airline: " + names.get(i).getText());
                        System.out.println("Price: ₹" + price);
                        break;
                    }
                }
            } else {
                System.out.println("Not flight with second lowest price.........");
            }
        }
    }




