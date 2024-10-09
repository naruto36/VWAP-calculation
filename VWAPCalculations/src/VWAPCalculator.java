import java.util.HashMap;
import java.util.Map;

public class VWAPCalculator {
    private Map<String, VWAPTime> vwapMap = new HashMap<>();

    public void processPriceData(PriceData priceData) {
        String currency = priceData.getCurrency();

        // If a currency pair does not exists crete one
        vwapMap.putIfAbsent(currency, new VWAPTime());

        // Ad the new data to the currency pair
        VWAPTime vwapCalculator = vwapMap.get(currency);
        vwapCalculator.addPriceData(priceData);

        System.out.println("VWAP for " + currency + " : " + vwapCalculator.calculateVWAP());
    }
}
