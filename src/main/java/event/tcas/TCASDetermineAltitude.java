package event.tcas;

public class TCASDetermineAltitude {
    String environment;

    public TCASDetermineAltitude(String environment) {
        this.environment = environment;
    }

    public String toString() {
        return "Event: TCAS - DetermineAltitude";
    }
}
