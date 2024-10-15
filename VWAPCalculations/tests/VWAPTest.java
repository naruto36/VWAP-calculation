import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class VWAPTest {

    @Test
    public void testVWAPCalculationBasic() {
        VWAPTime vwap = new VWAPTime();
        vwap.addPriceData(new PriceData(LocalDateTime.now(), "AUD/USD", 0.369, 1000));
        vwap.addPriceData(new PriceData(LocalDateTime.now(), "AUD/USD", 0.36, 2000));

        double expectedVWAP = (0.369 * 1000 + 0.36 * 2000) / 3000;
        assertEquals(expectedVWAP, vwap.calculateVWAP(), 0.001);
    }

    @Test
    public void testVWAPWithOldData() {
        VWAPTime vwap = new VWAPTime();

        // Adding multple datas older than 1 hour
        vwap.addPriceData(new PriceData(LocalDateTime.now().minusHours(2), "AUD/USD", 0.50, 1000));
        vwap.addPriceData(new PriceData(LocalDateTime.now().minusMinutes(30), "AUD/USD", 0.45, 1500));

        // Only the second entry should be considered as the fist is older than 1 hour
        double expectedVWAP = (0.45 * 1500) / 1500;
        assertEquals(expectedVWAP, vwap.calculateVWAP(), 0.001);
    }

    @Test
    public void testVWAPWithMixedTimeData() {
        VWAPTime vwap = new VWAPTime();

        // Add some older and recent data
        vwap.addPriceData(new PriceData(LocalDateTime.now().minusMinutes(65), "AUD/USD", 0.50, 1000));
        vwap.addPriceData(new PriceData(LocalDateTime.now().minusMinutes(50), "AUD/USD", 0.45, 2000));
        vwap.addPriceData(new PriceData(LocalDateTime.now().minusMinutes(30), "AUD/USD", 0.40, 3000));

        // Calculate expected VWAP with only valid entries
        double expectedVWAP = (0.45 * 2000 + 0.40 * 3000) / (2000 + 3000);
        assertEquals(expectedVWAP, vwap.calculateVWAP(), 0.001);
    }

    @Test
    public void testVWAPWithNoData() {
        VWAPTime vwap = new VWAPTime();

        // No data should return 0
        assertEquals(0.0, vwap.calculateVWAP(), 0.001);
    }

    @Test
    public void testVWAPWithVolumeZero() {
        VWAPTime vwap = new VWAPTime();

        // Adding data with 0 vol
        vwap.addPriceData(new PriceData(LocalDateTime.now(), "AUD/USD", 0.369, 0));
        vwap.addPriceData(new PriceData(LocalDateTime.now(), "AUD/USD", 0.36, 2000));

        double expectedVWAP = (0.36 * 2000) / 2000;
        assertEquals(expectedVWAP, vwap.calculateVWAP(), 0.001);
    }

    @Test
    public void testVWAPWithMultipleCurrencies() {
        VWAPTime vwap = new VWAPTime();

        // Adding data for diff currency
        vwap.addPriceData(new PriceData(LocalDateTime.now(), "AUD/USD", 0.369, 1000));
        vwap.addPriceData(new PriceData(LocalDateTime.now(), "EUR/USD", 1.123, 2000));

        // Both shold be inclded in VWAP calculations
        double expectedVWAP = (0.369 * 1000 + 1.123 * 2000) / 3000;
        assertEquals(expectedVWAP, vwap.calculateVWAP(), 0.001);
    }
}
