package event.gps;

public class GPSConnect {
    String satelite;

    public GPSConnect(String satelite) {
        this.satelite = satelite;
    }

    public String toString() {
        return "Event: GPS - Connect";
    }
}
