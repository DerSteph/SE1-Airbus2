package event.air_conditioning;

public class AirConditioningHeat {

    String airflow;
    int temperature;

    public AirConditioningHeat(String airflow, int temperature) {
        this.airflow=airflow;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Event: AirConditioning - Heat";
    }
}
