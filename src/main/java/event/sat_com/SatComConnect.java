package event.sat_com;

public class SatComConnect {
    private String satelite;
    private String frequency;

    public SatComConnect(String satelite, String frequency){
        this.satelite = satelite;
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Event: SatCom - Connect";
    }
}
