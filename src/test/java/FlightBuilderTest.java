import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class FlightBuilderTest {

    @Test
    public void testCreateFlight() {
        LocalDateTime departure1 = LocalDateTime.of(2024, 1, 15, 12, 0);
        LocalDateTime arrival1 = LocalDateTime.of(2024, 1, 15, 14, 0);
        LocalDateTime departure2 = LocalDateTime.of(2024, 1, 15, 15, 0);
        LocalDateTime arrival2 = LocalDateTime.of(2024, 1, 15, 17, 0);

        Flight flight = FlightBuilder.createFlight(departure1, arrival1, departure2, arrival2);

        assertNotNull(flight);
        List<Segment> segments = flight.getSegments();
        assertNotNull(segments);
        assertEquals(2, segments.size());

        assertEquals(departure1, segments.get(0).getDepartureDate());
        assertEquals(arrival1, segments.get(0).getArrivalDate());

        assertEquals(departure2, segments.get(1).getDepartureDate());
        assertEquals(arrival2, segments.get(1).getArrivalDate());
    }
}
