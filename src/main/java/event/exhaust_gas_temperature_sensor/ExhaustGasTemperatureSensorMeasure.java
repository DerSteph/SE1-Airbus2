package event.exhaust_gas_temperature_sensor;

public class ExhaustGasTemperatureSensorMeasure {
    int temperature;

    public ExhaustGasTemperatureSensorMeasure(int temperature){
        this.temperature = temperature;
    }
    public String toString(){
        return "Event: ExhaustGasTemperatureSensor - Measure";
    }
}
