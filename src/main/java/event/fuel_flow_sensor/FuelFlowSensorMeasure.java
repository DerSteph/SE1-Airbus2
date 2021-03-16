package event.fuel_flow_sensor;

public class FuelFlowSensorMeasure {
    int fuelFlow;

    public FuelFlowSensorMeasure(int fuelFlow){
        this.fuelFlow = fuelFlow;
    }

    public String toString(){
        return "Event: FuelFlowSensor - Measure";
    }
}
