import java.util.Random;

public class ExhaustGasTemperaturSensor{
    //static instance
    private static ExhaustGasTemperaturSensor instance = new ExhaustGasTemperaturSensor();
    //port
    public Port port;

    private String manufacturer = "4002027/2814814";
    private int temperature;
    private boolean isAlarmMajor;
    private boolean isAlarmCritical;


    public String innerVersion() {
        return "ExhaustGasTemperatureSensorV1";
    }


    public int innerMeasure() {
        this.temperature = new Random().nextInt(200) + 800;
        return this.temperature;
    }

    public boolean innerAlarmMajor(int threshold) {
        if(innerMeasure() > threshold){
            isAlarmMajor = true;
            return true;
        }
        else{
            isAlarmMajor = false;
            return false;
        }
    }

    public boolean innerAlarmCritical(int threshold) {
        if(innerMeasure() > threshold){
            isAlarmCritical = true;
            return true;
        }
        else{
            isAlarmCritical = false;
            return false;
        }
    }

    public class Port implements IExhaustGasTemperatureSensor {
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int measure() {
            return innerMeasure();
        }

        @Override
        public boolean alarmMajor(int threshold) {
            return innerAlarmMajor(threshold);
        }

        @Override
        public boolean alarmCritical(int threshold) {
            return innerAlarmCritical(threshold);
        }
    }
}
