import java.time.LocalDateTime;

public class PriceData {
    private final LocalDateTime timestamp;
    private final String currency;
    private final double price;
    private final long volume;

    public PriceData(LocalDateTime timestamp, String currency, double price, long volume) {
        this.timestamp = timestamp;
        this.currency = currency;
        this.price = price;
        this.volume = volume;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCurrency() {
        return currency;
    }

    public double getPrice() {
        return price;
    }

    public long getVolume() {
        return volume;
    }
}
