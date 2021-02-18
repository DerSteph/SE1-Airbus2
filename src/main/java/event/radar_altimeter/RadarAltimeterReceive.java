package event.radar_altimeter;

public class RadarAltimeterReceive
{
    String radioWave;

    public RadarAltimeterReceive(String radioWave) {
        this.radioWave = radioWave;
    }

    @Override
    public String toString() {
        return "Event: RadarAltimeter - Receive";
    }
}
