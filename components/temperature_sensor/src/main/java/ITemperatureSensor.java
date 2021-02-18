public interface ITemperatureSensor
{
    public String version();
    public int measure();
    public boolean alarm(int threshold);
}
