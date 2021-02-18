public interface IShockSensor
{
    public String version();
    public boolean calibrate();
    public boolean calibrate(double level);
    public int measure();
    public boolean alarm();
}
