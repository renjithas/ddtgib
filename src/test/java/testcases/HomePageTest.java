package testcases;
/*
Given Chrome browser was launched
Then navigate to "https://www.goibibo.com/" URL
Then user verifies "Flights" is selected and then "Round trip" is selected
Then user clicks on From and enter "Mumbai" for Flights
Then the autosuggestions are listed, user selects "Mumbai, India" from the dropdown for Flights
Then user clicks on Destination and input "Kochi" for Flights
When the autosuggestions are listed, user selects "Kochi, India" from the dropdown for Flights
Then user clicks on the Departure and selects the departure date
Then user clicks on Return and selects the return date
Then user modifies the traveller details by clicking on the selector, change the value to Adults "2" and children "1" and travel class to "Business" for Flights
When the user is able to close the popup, click on the Search button for Flights
*/
import base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestUtils;

public class HomePageTest extends TestBase {

    HomePage homePage;

    String sheetName = "homepage";

    public HomePageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        homePage = new HomePage();
    }
    @DataProvider
    public Object [][] getTestData(){
        Object data[][] =   TestUtils.getTestData(sheetName);
        return data;
    }

    @Test(dataProvider = "getTestData")
    public void travelDetails(String flightSource, String flightDestination, String adultCount, String childrenCount, String travelClass) {
        homePage.clickFlightsTab();
        homePage.clickRoundTrip();
        homePage.enterFromDetails(flightSource);
        homePage.enterDestinationDetails(flightDestination);
        homePage.enterDepartureDate(3);
        homePage.enterReturnDate(7);
        homePage.adultCount(adultCount);
        homePage.childCount(childrenCount);
        homePage.selectTravelClass(travelClass);
        homePage.search();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }


}
