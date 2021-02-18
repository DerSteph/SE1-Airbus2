public interface IRadarAltimeter
{
    public String version();
    public boolean on();
    public void send(String radioWave);
    public int receive(String radioWave);
    public int measureAltitude();
    public boolean off();
}
