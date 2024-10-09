import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        VWAPCalculator vwapCalculator = new VWAPCalculator();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        vwapCalculator.processPriceData(new PriceData(LocalDateTime.parse("2024-10-04 09:30:00", formatter), "AUD/USD", 0.6905, 106198));
        vwapCalculator.processPriceData(new PriceData(LocalDateTime.parse("2024-10-04 09:31:00", formatter), "USD/JPY", 142.497, 30995));
        vwapCalculator.processPriceData(new PriceData(LocalDateTime.parse("2024-10-04 09:32:00", formatter), "USD/JPY", 139.392, 2890000));
        vwapCalculator.processPriceData(new PriceData(LocalDateTime.parse("2024-10-04 09:33:00", formatter), "AUD/USD", 0.6899, 444134));
        vwapCalculator.processPriceData(new PriceData(LocalDateTime.parse("2024-10-04 09:34:00", formatter), "NZD/GBP", 0.4731, 64380));
        vwapCalculator.processPriceData(new PriceData(LocalDateTime.parse("2024-10-04 09:35:00", formatter), "NZD/GBP", 0.4725, 8226295));
    }
}