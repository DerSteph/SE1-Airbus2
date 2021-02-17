package event.sat_com;

public class SatComSend {
    private String request;
    public SatComSend(String request){
        this.request = request;
    }
    @Override
    public String toString() {
        return "Event: SatCom - Send";
    }
}
