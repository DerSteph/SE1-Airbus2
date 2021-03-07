package event.air_flow_sensor;

public class AirFlowSensorWingMeasure
{
    String airFlow;

    public AirFlowSensorWingMeasure(String airFlow) {
        this.airFlow = airFlow;
    }

    public String toString() {
        return "Event: AirFlowSensorWing - Measure";
    }
}
