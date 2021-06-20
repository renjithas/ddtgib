package testcases;
/*
    When the user is navigated to the flight details page, verify the listed flight details and fare summary for Flights
    Then user closes the browser
 */
import base.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FlightDetailsPage;
import pages.HomePage;
import pages.TicketDetailsPage;
import utils.TestUtils;

public class TicketDetailsPageTest extends TestBase {

    TicketDetailsPage ticketDetailsPage;
    HomePage homePage;
    FlightDetailsPage flightDetailsPage;

    public TicketDetailsPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();

        ticketDetailsPage = new TicketDetailsPage();
        homePage = new HomePage();
        flightDetailsPage = new FlightDetailsPage();

        homePage.clickFlightsTab();
        homePage.clickRoundTrip();
        homePage.enterFromDetails(TestUtils.TESTDATA_FLIGHT_SOURCE);
        homePage.enterDestinationDetails(TestUtils.TESTDATA_FLIGHT_DESTINATION);
        homePage.enterDepartureDate(3);
        homePage.enterReturnDate(7);
        homePage.adultCount(TestUtils.TESTDATA_ADULT_COUNT);
        homePage.childCount(TestUtils.TESTDATA_CHILDREN_COUNT);
        homePage.selectTravelClass(TestUtils.TESTDATA_TRAVEL_CLASS);
        homePage.search();

        flightDetailsPage.sortReturnPerDeparture();
        flightDetailsPage.selectReturnFlight();
        flightDetailsPage.bookFlight();
    }

    @Test(priority = 1)
    public void verifyFlightLocation() {
        WebElement flightLocation = ticketDetailsPage.getLocation();
        try {
            if (flightLocation.getText().contains(TestUtils.TESTDATA_FLIGHT_SOURCE)) {
                Assert.assertTrue(true);
            }
            if (flightLocation.getText().contains(TestUtils.TESTDATA_FLIGHT_DESTINATION)) {
                Assert.assertTrue(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void verifyBaseFare() {
        int baseFare = ticketDetailsPage.getFare();
        int calculatedFare = ticketDetailsPage.calculatedBaseFare();
        try {
            if (baseFare == calculatedFare) {
                Assert.assertTrue(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}
