package pages;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.TestUtils;


public class TicketDetailsPage extends TestBase {

    @FindBy(xpath = ".//span[text()='Base Fare']/following::span[1]")
    WebElement baseFareAmount;

    String location = "//*[contains(text(),'####')]";

    public TicketDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getLocation() {
        return driver.findElement(By.xpath(location.replace("####", TestUtils.TESTDATA_FLIGHT_SOURCE)));

    }

    public int getFare() {
        TestUtils.jsGetText(baseFareAmount, TestUtils.WAIT_TIME);
        return Integer.parseInt(baseFareAmount.getText().replaceAll("[^0-9]", ""));

    }

    public int calculatedBaseFare() {
        return FlightDetailsPage.totalAmount * Integer.parseInt(TestUtils.TESTDATA_ADULT_COUNT);
    }
}
