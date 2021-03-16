package base;

import com.google.common.eventbus.EventBus;
import event.Subscriber;
import event.air_conditioning.AirConditioningOn;
import event.air_flow_sensor.AirFlowSensorBodyMeasure;
import event.air_flow_sensor.AirFlowSensorWingMeasure;
import event.anti_collision_light.AntiCollisionLightOff;
import event.anti_collision_light.AntiCollisionLightOn;
import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.battery.BatteryCharge;
import event.battery.BatteryDischarge;
import event.cargo_compartment_light.CargoCompartmentLightOff;
import event.cargo_compartment_light.CargoCompartmentLightOn;
import event.cost_optimizer.CostOptimizerOff;
import event.cost_optimizer.CostOptimizerOn;
import event.deicing_system.DeIcingSystemActivate;
import event.deicing_system.DeIcingSystemDeIce;
import event.deicing_system.DeIcingSystemDeactivate;
import event.deicing_system.DeIcingSystemRefill;
import event.engine.EngineDecreaseRPM;
import event.engine.EngineIncreaseRPM;
import event.engine.EngineShutdown;
import event.engine.EngineStart;
import event.engine_oil_tank.EngineOilTankDecreaseLevel;
import event.engine_oil_tank.EngineOilTankIncreaseLevel;
import event.fuel_tank.FuelTankRefill;
import event.fuel_tank.FuelTankTakeOut;
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
import event.nitrogen_bottle.NitrogenBottleRefill;
import event.nitrogen_bottle.NitrogenBottleTakeOut;
import event.oxygen_bottle.OxygenBottleRefill;
import event.oxygen_bottle.OxygenBottleTakeOut;
import event.pitot_tube.PitotTubeClean;
import event.pitot_tube.PitotTubeMeasureVelocity;
import event.radar_altimeter.RadarAltimeterOff;
import event.radar_altimeter.RadarAltimeterOn;
import event.right_aileron.RightAileronFullDown;
import event.right_aileron.RightAileronFullUp;
import event.route_management.RouteManagementOff;
import event.route_management.RouteManagementOn;
import event.rudder.RudderFullLeft;
import event.rudder.RudderFullRight;
import event.rudder.RudderNeutral;
import event.shock_sensor.ShockSensorBodyCalibrate;
import event.shock_sensor.ShockSensorBodyMeasure;
import event.shock_sensor.ShockSensorWingCalibrate;
import event.shock_sensor.ShockSensorWingMeasure;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.spoiler.SpoilerFullUp;
import event.spoiler.SpoilerNeutral;
import event.tcas.*;
import event.temperature_sensor.TemperatureSensorBodyMeasure;
import event.temperature_sensor.TemperatureSensorWingMeasure;
import event.right_navigation_light.RightNavigationLightOff;
import event.right_navigation_light.RightNavigationLightOn;
import event.tail_navigation_light.TailNavigationLightOff;
import event.tail_navigation_light.TailNavigationLightOn;
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
        // air flow sensor
        eventBus.post(new AirFlowSensorBodyMeasure("air"));
        eventBus.post((new AirFlowSensorWingMeasure("air")));
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
        eventBus.post(new DeIcingSystemRefill());
        eventBus.post(new DeIcingSystemDeIce(100));
        // engine
        eventBus.post(new EngineStart());
        eventBus.post(new EngineIncreaseRPM(1000));
        // engine oil tank
        eventBus.post(new EngineOilTankIncreaseLevel());
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
        // nitrogen bottle
        eventBus.post(new NitrogenBottleRefill());
        // oxygen bottle
        eventBus.post(new OxygenBottleRefill());
        // pilot tube
        eventBus.post(new PitotTubeClean());
        // radar altimeter
        eventBus.post(new RadarAltimeterOn());
        // route management
        eventBus.post(new RouteManagementOn());
        // shock sensor
        eventBus.post(new ShockSensorBodyCalibrate(5));
        eventBus.post(new ShockSensorWingCalibrate("5"));
        // spoiler
        eventBus.post(new SpoilerNeutral());
        // tcas
        eventBus.post(new TCASOn());
        // tcas
        eventBus.post(new TCASConnect("89,5 MHz"));
        // temperature sensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void taxi() {
        // air flow sensor
        eventBus.post(new AirFlowSensorBodyMeasure("air"));
        eventBus.post(new AirFlowSensorWingMeasure("air"));
        // apu
        eventBus.post(new APUDecreaseRPM(1000));
        eventBus.post(new APUShutdown());
        // brake
        eventBus.post(new GearReleaseBrake());
        // cargo compartment light
        eventBus.post(new CargoCompartmentLightOff());
        // deicing system
        eventBus.post(new DeIcingSystemDeIce(100));
        // fuel tank
        eventBus.post(new FuelTankTakeOut(10));
        // nitrogen bottle
        eventBus.post(new NitrogenBottleTakeOut(1));
        // oxygen bottle
        eventBus.post(new OxygenBottleTakeOut(1));
        // pitot tube
        eventBus.post(new PitotTubeMeasureVelocity());
        // right_navigation_light
        /eventBus.post(new RightNavigationLightOn());
        // tail_navigation_light
        eventBus.post(new TailNavigationLightOn());
        // tcas
        eventBus.post(new TCASDetermineAltitude("ground"));
        // temperature sensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
        }

    public void takeoff() {
        // air flow sensor
        eventBus.post(new AirFlowSensorBodyMeasure("air"));
        eventBus.post(new AirFlowSensorWingMeasure("air"));
        // deicing system
        eventBus.post(new DeIcingSystemDeIce(100));
        // engine
        eventBus.post(new EngineIncreaseRPM(2000));
        // fuel tank
        eventBus.post((new FuelTankTakeOut(100)));
        // gear
        eventBus.post(new GearUp());
        // logo light
        eventBus.post(new LogoLightOff());
        // nitrogen bottle
        eventBus.post(new NitrogenBottleTakeOut(10));
        // oxygen bottle
        eventBus.post(new OxygenBottleTakeOut(10));
        // pitot tube
        eventBus.post(new PitotTubeMeasureVelocity());
        // radar altimeter
        eventBus.post(new RadarAltimeterOn());
        // rudder
        eventBus.post(new RudderNeutral());
        // shock sensor
        eventBus.post(new ShockSensorBodyMeasure());
        eventBus.post(new ShockSensorWingMeasure());
        // slat
        eventBus.post(new SlatFullDown());
        // tcas
        eventBus.post(new TCASSetAltitude(50));
        eventBus.post(new TCASDetermineAltitude("ground"));
        // temperature sensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void climbing() {
        // air flow sensor
        eventBus.post(new AirFlowSensorBodyMeasure("air"));
        eventBus.post(new AirFlowSensorWingMeasure("air"));
        // deicing system
        eventBus.post(new DeIcingSystemDeIce(100));
        // engine oil tank
        eventBus.post(new EngineOilTankDecreaseLevel());
        // fuel tank
        eventBus.post((new FuelTankTakeOut(100)));
        // gear
        eventBus.post(new GearUp());
        // nitrogen bottle
        eventBus.post(new NitrogenBottleTakeOut(10));
        // oxygen bottle
        eventBus.post(new OxygenBottleTakeOut(10));
        // pitot tube
        eventBus.post(new PitotTubeMeasureVelocity());
        // radar altimeter
        eventBus.post(new RadarAltimeterOn());
        // rudder
        eventBus.post(new RudderNeutral());
        // tcas
        eventBus.post(new TCASSetAltitude(2000));
        eventBus.post(new TCASDetermineAltitude("high"));
        // temperature sensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void rightTurn() {
        // air flow sensor
        eventBus.post(new AirFlowSensorBodyMeasure("air"));
        eventBus.post(new AirFlowSensorWingMeasure("air"));
        // deicing system
        eventBus.post(new DeIcingSystemDeIce(100));
        // engine oil tank
        eventBus.post(new EngineOilTankDecreaseLevel());
        // fuel tank
        eventBus.post((new FuelTankTakeOut(100)));
        // left aileron
        eventBus.post(new LeftAileronFullDown());
        // nitrogen bottle
        eventBus.post(new NitrogenBottleTakeOut(10));
        // oxygen bottle
        eventBus.post(new OxygenBottleTakeOut(10));
        // pitot tube
        eventBus.post(new PitotTubeMeasureVelocity());
        // radar altimeter
        eventBus.post(new RadarAltimeterOn());
        // right aileron
        eventBus.post(new RightAileronFullUp());
        // rudder
        eventBus.post(new RudderFullRight());
        // temperature sensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void leftTurn() {
        // air flow sensor
        eventBus.post(new AirFlowSensorBodyMeasure("air"));
        eventBus.post(new AirFlowSensorWingMeasure("air"));
        // deicing system
        eventBus.post(new DeIcingSystemDeIce(100));
        // engine oil tank
        eventBus.post(new EngineOilTankDecreaseLevel());
        // fuel tank
        eventBus.post((new FuelTankTakeOut(100)));
        // left aileron
        eventBus.post(new LeftAileronFullUp());
        // nitrogen bottle
        eventBus.post(new NitrogenBottleTakeOut(10));
        // oxygen bottle
        eventBus.post(new OxygenBottleTakeOut(10));
        // pitot tube
        eventBus.post(new PitotTubeMeasureVelocity());
        // radar altimeter
        eventBus.post(new RadarAltimeterOn());
        // right aileron
        eventBus.post(new RightAileronFullDown());
        // rudder
        eventBus.post(new RudderFullLeft());
        // temperature sensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void descent() {
        // air flow sensor
        eventBus.post(new AirFlowSensorBodyMeasure("air"));
        eventBus.post(new AirFlowSensorWingMeasure("air"));
        // deicing system
        eventBus.post(new DeIcingSystemDeIce(100));
        // engine oil tank
        eventBus.post(new EngineOilTankDecreaseLevel());
        // fuel tank
        eventBus.post((new FuelTankTakeOut(100)));
        // nitrogen bottle
        eventBus.post(new NitrogenBottleTakeOut(10));
        // oxygen bottle
        eventBus.post(new OxygenBottleTakeOut(10));
        // pitot tube
        eventBus.post(new PitotTubeMeasureVelocity());
        // radar altimeter
        eventBus.post(new RadarAltimeterOn());
        // rudder
        eventBus.post(new RudderNeutral());
        // tcas
        eventBus.post(new TCASSetAltitude(1000));
        eventBus.post(new TCASDetermineAltitude("low"));
        // temperature sensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void landing() {
        // air flow sensor
        eventBus.post(new AirFlowSensorBodyMeasure("air"));
        eventBus.post(new AirFlowSensorWingMeasure("air"));
        // apu
        eventBus.post(new APUStart());
        eventBus.post(new APUIncreaseRPM(2000));
        // engine
        eventBus.post(new EngineDecreaseRPM(1000));
        // fuel tank
        eventBus.post((new FuelTankTakeOut(10)));
        // gear
        eventBus.post(new GearDown());
        eventBus.post(new GearSetBrakePercentage(70));
        // landing light
        eventBus.post(new LandingLightBodyOn());
        eventBus.post(new LandingLightWingOn());
        // logo light
        eventBus.post(new LogoLightOn());
        // nitrogen bottle
        eventBus.post(new NitrogenBottleTakeOut(1));
        // oxygen bottle
        eventBus.post(new OxygenBottleTakeOut(1));
        // pitot tube
        eventBus.post(new PitotTubeMeasureVelocity());
        // slat
        eventBus.post(new SlatFullDown());
        // spoiler
        eventBus.post(new SpoilerFullUp());
        // tcas
        eventBus.post(new TCASSetAltitude(0));
        eventBus.post(new TCASDetermineAltitude("ground"));
        // temperature sensor
        eventBus.post(new TemperatureSensorBodyMeasure());
        eventBus.post(new TemperatureSensorWingMeasure());
        // weather_radar
        eventBus.post(new WeatherRadarOn());
    }

    public void shutdown() {
        // anti collision light
        eventBus.post(new AntiCollisionLightOff());
        // apu
        eventBus.post(new APUDecreaseRPM(2000));
        eventBus.post(new APUShutdown());
        // battery
        eventBus.post(new BatteryDischarge(100));
        // brake
        eventBus.post(new GearSetBrake());
        // cargo compartment light
        eventBus.post(new CargoCompartmentLightOn());
        // cost optimizer
        eventBus.post(new CostOptimizerOff());
        // deicing system
        eventBus.post(new DeIcingSystemDeactivate());
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
        // radar altimeter
        eventBus.post(new RadarAltimeterOff());
        // right_navigation_light
        eventBus.post(new RightNavigationLightOff());
        // route management
        eventBus.post(new RouteManagementOff());
        // slat
        eventBus.post(new SlatNeutral());
        // spoiler
        eventBus.post(new SpoilerNeutral());
        // tail_navigation_light
        eventBus.post(new TailNavigationLightOff());
        // tcas
        eventBus.post(new TCASOff());
        // weather_radar
        eventBus.post(new WeatherRadarOff());

    }
}