package event.radar_altimeter;

public class RadarAltimeterSend
{
    String radioWave;

    public RadarAltimeterSend(String radioWave) {
        this.radioWave = radioWave;
    }

    @Override
    public String toString() {
        return "Event: RadarAltimeter - Send";
    }
}
