package event.air_conditioning;

public class AirConditioningClean {

    String airflow;
    int temperature;

    public AirConditioningClean(String airflow, int temperature) {
        this.airflow=airflow;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Event: AirConditioning - Clean";
    }
}
