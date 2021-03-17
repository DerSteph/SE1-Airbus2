import java.util.Random;

public class FuelFlowSensor {
    //static instance
    private static FuelFlowSensor instance = new FuelFlowSensor();
    //port
    public Port port;
    private String manufacturer = "4002027/2814814";

    private int fuelFlow;

    private FuelFlowSensor() { port = new Port(); }

    public static FuelFlowSensor getInstance() { return instance; }

    public String innerVersion() {
        return "FuelFlowSensorV1";
    }

    public int innerMeasure() {
        fuelFlow = new Random().nextInt(101);
        return fuelFlow;
    }

    public class Port implements IFuelFlowSensor{
        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int measure() {
            return innerMeasure();
        }
    }
}
