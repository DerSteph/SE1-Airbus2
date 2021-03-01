package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.air_conditioning.AirConditioningOn;
import event.anti_collision_light.AntiCollisionLightOff;
import event.anti_collision_light.AntiCollisionLightOn;
import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.battery.BatteryCharge;
import event.cargo_compartment_light.CargoCompartmentLightOff;
import event.cargo_compartment_light.CargoCompartmentLightOn;
import event.cost_optimizer.CostOptimizerOff;
import event.cost_optimizer.CostOptimizerOn;
import event.deicing_system.DeIcingSystemActivate;
import event.engine.EngineDecreaseRPM;
import event.engine.EngineIncreaseRPM;
import event.engine.EngineShutdown;
import event.engine.EngineStart;
import event.fuel_tank.FuelTankRefill;
import event.gear.*;
import event.hydraulic_pump.HydraulicPumpBodyCompress;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.landing_light.LandingLightWingOff;
import event.landing_light.LandingLightWingOn;
import event.left_aileron.LeftAileronFullDown;
import event.left_aileron.LeftAileronFullUp;
import event.left_navigation_light.LeftNavigationLightOff;
import event.left_navigation_light.LeftNavigationLightOn;
import event.logo_light.LogoLightOff;
import event.logo_light.LogoLightOn;
import event.pitot_tube.PitotTubeClean;
import event.radar_altimeter.RadarAltimeterOn;
import event.right_aileron.RightAileronFullDown;
import event.right_aileron.RightAileronFullUp;
import event.route_management.RouteManagementOff;
import event.route_management.RouteManagementOn;
import event.rudder.RudderFullLeft;
import event.rudder.RudderFullRight;
import event.rudder.RudderNeutral;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.spoiler.SpoilerFullUp;
import event.spoiler.SpoilerNeutral;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import section.Body;
import section.Wing;

public class Airplane implements IAirplane {
    private final EventBus eventBus;
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
        // air conditioning
        eventBus.post(new AirConditioningOn());
        // anti collision light
        eventBus.post(new AntiCollisionLightOn());
        //apu
        eventBus.post(new APUStart());
        eventBus.post(new APUIncreaseRPM(1000));
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
        eventBus.post(new EngineIncreaseRPM(1000));
        // fuel tank
        eventBus.post(new FuelTankRefill());
        // gear
        eventBus.post(new GearDown());
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
//        // shock sensor
//        eventBus.post(new ShockSensorBodyCalibrate(5));
//        eventBus.post(new ShockSensorWingCalibrate("5"));
        // spoiler
        eventBus.post(new SpoilerNeutral());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void taxi() {
        // apu
        eventBus.post(new APUDecreaseRPM(0));
        eventBus.post(new APUShutdown());
        // brake
        eventBus.post(new GearReleaseBrake());
        // cargo compartment light
        eventBus.post(new CargoCompartmentLightOff());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void takeoff() {
        // engine
        eventBus.post(new EngineIncreaseRPM(2000));
        // gear
        eventBus.post(new GearUp());
        // logo light
        eventBus.post(new LogoLightOff());
        // rudder
        eventBus.post(new RudderNeutral());
        // slat
        eventBus.post(new SlatFullDown());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void climbing() {
        // gear
        eventBus.post(new GearUp());
        // rudder
        eventBus.post(new RudderNeutral());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void rightTurn() {
        // left aileron
        eventBus.post(new LeftAileronFullDown());
        // right aileron
        eventBus.post(new RightAileronFullUp());
        // rudder
        eventBus.post(new RudderFullRight());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void leftTurn() {
        // left aileron
        eventBus.post(new LeftAileronFullUp());
        // right aileron
        eventBus.post(new RightAileronFullDown());
        // rudder
        eventBus.post(new RudderFullLeft());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void descent() {
        // rudder
        eventBus.post(new RudderNeutral());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void landing() {
        // apu
        eventBus.post(new APUStart());
        eventBus.post(new APUIncreaseRPM(2000));
        // engine
        eventBus.post(new EngineDecreaseRPM(1000));
        // gear
        eventBus.post(new GearDown());
        eventBus.post(new GearSetBrakePercentage(70));
        // landing light
        eventBus.post(new LandingLightBodyOn());
        eventBus.post(new LandingLightWingOn());
        // logo light
        eventBus.post(new LogoLightOn());
        // slat
        eventBus.post(new SlatFullDown());
        // spoiler
        eventBus.post(new SpoilerFullUp());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void shutdown() {
        // anti collision light
        eventBus.post(new AntiCollisionLightOff());
        // apu
        eventBus.post(new APUDecreaseRPM(2000));
        eventBus.post(new APUShutdown());
        // brake
        eventBus.post(new GearSetBrake());
        // cargo compartment light
        eventBus.post(new CargoCompartmentLightOn());
        // cost optimizer
        eventBus.post(new CostOptimizerOff());
        // engine
        eventBus.post(new EngineDecreaseRPM(1000));
        eventBus.post(new EngineShutdown());
        // landing light
        eventBus.post(new LandingLightBodyOff());
        eventBus.post(new LandingLightWingOff());
        // left navigation light
        eventBus.post(new LeftNavigationLightOff());
        // logo light
        eventBus.post(new LogoLightOff());
        // route management
        eventBus.post(new RouteManagementOff());
        // slat
        eventBus.post(new SlatNeutral());
        // spoiler
        eventBus.post(new SpoilerNeutral());
        // weather_radar
        eventBus.post(new WeatherRadarOff());
    }
}