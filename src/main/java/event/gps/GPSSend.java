package event.gps;

public class GPSSend {
    String request;

    public GPSSend(String request) {
        this.request = request;
    }

    public String toString() {
        return "Event: GPS - Send";
    }
}
