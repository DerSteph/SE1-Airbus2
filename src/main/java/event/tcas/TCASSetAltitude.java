package event.tcas;

public class TCASSetAltitude {
    int value;

    public TCASSetAltitude(int value) {
        this.value = value;
    }

    public String toString() {
        return "Event: TCAS - SetAltitude";
    }
}
