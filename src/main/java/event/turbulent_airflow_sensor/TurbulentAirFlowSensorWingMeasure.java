package event.turbulent_airflow_sensor;

public class TurbulentAirFlowSensorWingMeasure {
    String airFlow;

    public TurbulentAirFlowSensorWingMeasure(String airFlow) {
        this.airFlow = airFlow;
    }

    public String toString() {
        return "Event: TurbulentAirFlowSensor - Measure";
    }
}
