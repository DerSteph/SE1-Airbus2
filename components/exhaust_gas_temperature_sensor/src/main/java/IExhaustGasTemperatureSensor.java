public interface IExhaustGasTemperatureSensor {

    public String version();

    public int measure();

    public boolean alarmMajor(int threshold);

    public boolean alarmCritical(int threshold);

}
