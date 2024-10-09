import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class VWAPTest {

    @Test
    public void testVWAPCalculation() {
        VWAPTime vwap = new VWAPTime();
        vwap.addPriceData(new PriceData(LocalDateTime.now(), "AUD/USD", 0.369, 1000));
        vwap.addPriceData(new PriceData(LocalDateTime.now(), "AUD/USD", 0.36, 2000));

        double expectedVWAP = (0.369 * 1000 + 0.36 * 2000) / 3000;
        assertEquals(expectedVWAP, vwap.calculateVWAP(), 0.001);
    }
}
