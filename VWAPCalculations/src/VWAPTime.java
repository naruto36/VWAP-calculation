import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Queue;

public class VWAPTime {
    private final Queue<PriceData> priceDataQueue = new LinkedList<>();
    private double cumulativePriceVolume = 0.0;
    private long cumulativeVolume = 0;

    public void addPriceData(PriceData priceData) {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);

        // Remove outdated data
        while (!priceDataQueue.isEmpty() && priceDataQueue.peek().getTimestamp().isBefore(oneHourAgo)) {
            PriceData outdatedData = priceDataQueue.poll();
            assert outdatedData != null;
            cumulativePriceVolume -= outdatedData.getPrice() * outdatedData.getVolume();
            cumulativeVolume -= outdatedData.getVolume();
        }

        // Add the new price data to the queue
        priceDataQueue.offer(priceData);
        cumulativePriceVolume += priceData.getPrice() * priceData.getVolume();
        cumulativeVolume += priceData.getVolume();
    }

    // Calculate VWAP
    public double calculateVWAP() {
        if (cumulativeVolume == 0) {
            return 0.0;
        }
        return cumulativePriceVolume / cumulativeVolume;
    }
}
