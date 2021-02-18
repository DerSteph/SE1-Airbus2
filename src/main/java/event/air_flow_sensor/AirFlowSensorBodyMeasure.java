package event.air_flow_sensor;

public class AirFlowSensorBodyMeasure
{
    String airFlow;

    public AirFlowSensorBodyMeasure(String airFlow) {
        this.airFlow = airFlow;
    }

    public String toString() {
        return "Event: AirFlowSensorBody - Measure";
    }
}
