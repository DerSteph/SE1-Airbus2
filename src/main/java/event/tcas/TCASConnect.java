package event.tcas;

public class TCASConnect {
    String frequency;

    public TCASConnect(String frequency) {
        this.frequency = frequency;
    }

    public String toString() {
        return "Event: TCAS - Connect";
    }
}
