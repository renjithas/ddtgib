package pages;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.TestUtils;

public class HomePage extends TestBase {

    @FindBy(linkText = "Flights")
    WebElement linkFlights;

    @FindBy(id = "roundTrip")
    WebElement linkRoundTrip;

    @FindBy(id = "gosuggest_inputSrc")
    WebElement txtFrom;

    @FindBy(id = "gosuggest_inputDest")
    WebElement txtDestination;

//    @FindBy(id = "departureCalendar")
//    WebElement txtDepartureDate;
//
//    @FindBy(id = "returnCalendar")
//    WebElement txtReturnDate;

    @FindBy(id = "pax_link_common")
    WebElement txtPax;

    @FindBy(id = "adultPaxBox")
    WebElement paxBoxAdult;

    @FindBy(id = "childPaxBox")
    WebElement paxBoxChild;

    @FindBy(id = "gi_class")
    WebElement dpdnTravelClass;

    @FindBy(id = "gi_search_btn")
    WebElement btnSearch;


    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    //click flights tab
    public void clickFlightsTab() {
        linkFlights.click();
    }

    //click round trip
    public void clickRoundTrip() {
        linkRoundTrip.click();
    }

    //enter source location
    public void enterFromDetails(String flightSource) {
        txtFrom.sendKeys(flightSource);
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'" + flightSource + "')]"));
        TestUtils.waitForElement(driver, element, TestUtils.WAIT_TIME);
        element.click();

    }

    //enter destination location
    public void enterDestinationDetails(String flightDestination) {
        txtDestination.sendKeys(flightDestination);
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'" + flightDestination + "')]"));
        TestUtils.waitForElement(driver, element, TestUtils.WAIT_TIME);
        element.click();
    }

    //enter departure date
    public void enterDepartureDate(int plusDateFromCurrentDate) {
        enterDate(plusDateFromCurrentDate);
    }

    //enter return date
    public void enterReturnDate(int plusDateFromCurrentDate) {
        enterDate(plusDateFromCurrentDate);
    }

    public void enterDate(int plusDateFromCurrentDate) {
        String Date = TestUtils.enterDate(plusDateFromCurrentDate);
        String DateId = "//*[contains(@id,'" + Date + "')]";
        WebElement dateElement = driver.findElement(By.xpath(DateId));
        TestUtils.waitForElement(driver, dateElement, TestUtils.WAIT_TIME);
        dateElement.click();
    }

    // select travel class
    public void selectTravelClass(String travelClass){
        Select select = new Select(dpdnTravelClass);
        select.selectByVisibleText(travelClass);
    }

    //select adult count
    public void adultCount(String count){
        txtPax.click();
        paxBoxAdult.clear();
        paxBoxAdult.sendKeys(count);
    }

    //select children count
    public void childCount(String count){
        paxBoxChild.clear();
        paxBoxChild.sendKeys(count);
    }

    //click search button
    public FlightDetailsPage search(){
        TestUtils.waitForElementAndClick(driver, btnSearch, TestUtils.WAIT_TIME);
        return new FlightDetailsPage();
    }
}
