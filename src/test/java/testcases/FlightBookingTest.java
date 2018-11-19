package testcases;
import org.testng.annotations.Test;

import pages.CommonNavigationPanel;

public class FlightBookingTest extends CommonNavigationPanel {

    @Test
    public void testThatResultsAppearForAOneWayJourney() throws Exception {
    	FlightBookingPage().testOneWayFlightResults();
    }
}
