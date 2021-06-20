package pages;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.TestUtils;
import java.util.List;

public class FlightDetailsPage extends TestBase {

    public String sourceFlightAmount;
    public String destinationFlightAmount;
    public WebElement spanRtnFlight;
    public static int totalAmount;

    @FindBy(xpath = "//*[@class='SortOptionsstyles__SortOptionWrapper-tji0t1-1 GBYkC'][2]/div/div/span[text()='DEPARTURE']")
    WebElement sortDeparture;

    @FindBy(xpath = ".//div[@class='flexRow justifyBetween']/div[1]/div[1]/div/div/div/div/div/div[1]/div[2]/div/span/span/span")
    WebElement SrcFlightAmount;

    @FindBy(xpath = ".//span[@class='padR5 padB5 alignItemsEnd flexCol']/span/child::span")
    public WebElement UrSelnAmt;

    @FindBy(xpath = ".//input[@type='button']")
    WebElement btnBook;

    public FlightDetailsPage() {
        PageFactory.initElements(driver, this);
    }

    public void sortReturnPerDeparture() {
        sortDeparture.click();
    }

    public void selectReturnFlight() {

        List<WebElement> listElement = driver.findElements(By.xpath(".//div[@class='flexRow justifyBetween']/div[2]/div"));

        int n = listElement.size() - 1;

        String rtnFlight = ".//div[@class='flexRow justifyBetween']/div[2]/div[" + n + "]";

        TestUtils.jsExecutorScrollToView(driver.findElement(By.xpath(rtnFlight)));

        spanRtnFlight = driver.findElement(By.xpath(rtnFlight + "/div/div/div/div/div/div[1]/div[2]/div/span/span/span"));

        TestUtils.jsExecutorClick(spanRtnFlight);
    }

    //verify details in "Your Selection" for flights
    public int getTotalAmount() {
        sourceFlightAmount = TestUtils.getData(SrcFlightAmount).replaceAll(",", "");

        int sourceAmount = Integer.parseInt(sourceFlightAmount);

        destinationFlightAmount = TestUtils.getData(spanRtnFlight).replaceAll(",", "");

        int destinationAmount = Integer.parseInt(destinationFlightAmount);

        totalAmount = sourceAmount + destinationAmount;

        return totalAmount;

    }

    public void bookFlight() {
        btnBook.click();
    }
}
