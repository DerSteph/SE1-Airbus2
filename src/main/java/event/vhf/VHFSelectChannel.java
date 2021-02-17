package event.vhf;

public class VHFSelectChannel {

    private String channel;
    public VHFSelectChannel(String channel){
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Event: VHF - On";
    }
}
