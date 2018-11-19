package testcases;
import org.testng.annotations.Test;

import pages.CommonNavigationPanel;

public class HotelBookingTest extends CommonNavigationPanel {

    @Test
    public void shouldBeAbleToSearchForHotels() throws Exception {
    	HotelBookingPage().searchForHotels();
    }
}
