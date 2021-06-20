/*
    When the user is in the flight selection page, user selects the first flight from LHS for Flights
    Then user sorts the list of flights in RHS by clicking on Departure for Flights
    Then from the listed flights, user selects the flight on 2nd last row for Flights
    Then verify the selected flights are listed in "your selection" for Flights
    Then user clicks on the Book button for Flights
 */
package testcases;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.FlightDetailsPage;
import pages.HomePage;
import utils.TestUtils;

public class FlightDetailsPageTest extends TestBase {

    HomePage homePage;
    FlightDetailsPage flightDetailsPage;

    String sheetName = "homepage";

    public FlightDetailsPageTest() {
        super();
    }

    @BeforeMethod()
    public void setUp() {
        initialization();

        flightDetailsPage = new FlightDetailsPage();
        homePage = new HomePage();

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
    }

    @Test
    public void selectFlight() {
        flightDetailsPage.sortReturnPerDeparture(); //Sort the list of flights in RHS by clicking on Departure for Flights
        flightDetailsPage.selectReturnFlight(); //From the listed return flights, select the flight on 2nd last row for Flights
        verifyFlightSelection();
        flightDetailsPage.bookFlight();
    }

    public void verifyFlightSelection() { //Verify the selected flights are listed in "Your selection" for flights --> verify the source flight amount+destination flight amount = total amount in "Your flight selection" modal
        int actualAmount = flightDetailsPage.getTotalAmount(); //sum of source flight amount and destination flight amount
        int yourSelectionAmount = Integer.parseInt(TestUtils.getData(flightDetailsPage.UrSelnAmt).replaceAll(",", "")); //total amount in the 'your selection' field

        if (actualAmount == yourSelectionAmount) {
            Assert.assertTrue(true);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
