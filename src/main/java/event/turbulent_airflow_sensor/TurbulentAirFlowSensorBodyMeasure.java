package event.turbulent_airflow_sensor;

public class TurbulentAirFlowSensorBodyMeasure {
    String airFlow;

    public TurbulentAirFlowSensorBodyMeasure(String airFlow) {
        this.airFlow = airFlow;
    }

    public String toString() {
        return "Event: TurbulentAirFlowSensor - Measure";
    }
}
