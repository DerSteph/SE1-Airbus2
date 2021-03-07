public interface IAirFlowSensor
{
    public String version();
    public int measure(String airFlow);
    public boolean alarm(int threshold);
}
