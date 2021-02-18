package event.air_conditioning;

public class AirConditioningCool {

    String airflow;
    int temperature;

    public AirConditioningCool(String airflow, int temperature) {
        this.airflow=airflow;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Event: AirConditioning - Cool";
    }
}
