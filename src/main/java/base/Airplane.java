package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.anti_collision_light.AntiCollisionLightOn;
import event.apu.APUStart;
import event.battery.BatteryCharge;
import event.cargo_compartment_light.CargoCompartmentLightOn;
import event.cost_optimizer.CostOptimizerOn;
import event.deicing_system.DeIcingSystemActivate;
import event.engine.EngineStart;
import event.fuel_tank.FuelTankRefill;
import event.gear.GearSetBrake;
import event.hydraulic_pump.HydraulicPumpBodyCompress;
import event.left_navigation_light.LeftNavigationLightOn;
import event.logo_light.LogoLightOn;
import event.pitot_tube.PitotTubeClean;
import event.radar_altimeter.RadarAltimeterOn;
import event.route_management.RouteManagementOn;
import event.shock_sensor.ShockSensorBodyCalibrate;
import event.shock_sensor.ShockSensorWingCalibrate;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import section.Body;
import section.Wing;

public class Airplane implements IAirplane {
    private EventBus eventBus;
    private Body body;
    private Wing leftWing;
    private Wing rightWing;

    public Airplane() {
        eventBus = new EventBus("EB-A350");
    }

    public void addSubscriber(Subscriber subscriber) {
        eventBus.register(subscriber);
    }

    public void build() {
        body = new Body();
        addSubscriber(body);

        leftWing = new Wing();
        addSubscriber(leftWing);

        rightWing = new Wing();
        addSubscriber(rightWing);
    }

    public void startup() {
        // anti collision light
        eventBus.post(new AntiCollisionLightOn());
        //apu
        eventBus.post(new APUStart());
        // battery
        eventBus.post(new BatteryCharge(100));
        // brake
        eventBus.post(new GearSetBrake());
        // cargo compartment light
        eventBus.post(new CargoCompartmentLightOn());
        // cost optimizer
        eventBus.post(new CostOptimizerOn());
        // deicing system
        eventBus.post(new DeIcingSystemActivate());
        // engine
        eventBus.post(new EngineStart());
        // fuel tank
        eventBus.post(new FuelTankRefill());
        // hydraulic pump
        eventBus.post(new HydraulicPumpBodyCompress());
        // left navigation light
        eventBus.post(new LeftNavigationLightOn());
        // logo light
        eventBus.post(new LogoLightOn());
        // pilot tube
        eventBus.post(new PitotTubeClean());
        // radar altimeter
        eventBus.post(new RadarAltimeterOn());
        // route management
        eventBus.post(new RouteManagementOn());
        // shock sensor
        eventBus.post(new ShockSensorBodyCalibrate(5));
        eventBus.post(new ShockSensorWingCalibrate("5")); // ToDo ?
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void taxi() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void takeoff() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void climbing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void rightTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void leftTurn() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void descent() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void landing() {
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void shutdown() {
        // weather_radar
        eventBus.post(new WeatherRadarOff());
    }
}