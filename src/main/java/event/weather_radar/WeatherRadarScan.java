package event.weather_radar;

import event.Subscriber;

public class WeatherRadarScan extends Subscriber {
    String environment;

    public WeatherRadarScan(String environment) {
        this.environment = environment;
    }

    public String toString() {
        return "Event: WeatherRadar - Scan";
    }
}