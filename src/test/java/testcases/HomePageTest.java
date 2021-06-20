package testcases;

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
