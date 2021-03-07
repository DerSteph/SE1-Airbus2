package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.nitrogen_bottle.NitrogenBottleRefill;
import event.nitrogen_bottle.NitrogenBottleTakeOut;
import event.oxygen_bottle.OxygenBottleRefill;
import event.oxygen_bottle.OxygenBottleTakeOut;
import event.portable_watertank.PortableWaterTankLock;
import event.portable_watertank.PortableWaterTankRefill;
import event.portable_watertank.PortableWaterTankTakeOut;
import event.portable_watertank.PortableWaterTankUnlock;
import event.sat_com.*;
import event.tcas.*;
import event.turbulent_airflow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.turbulent_airflow_sensor.TurbulentAirFlowSensorWingMeasure;
import event.vhf.*;
import event.wastewater_tank.WasteWaterTankAdd;
import event.wastewater_tank.WasteWaterTankLock;
import event.wastewater_tank.WasteWaterTankPumpOut;
import event.wastewater_tank.WasteWaterTankUnlock;


import event.air_flow_sensor.*;
import event.battery.*;
import event.deicing_system.*;
import event.pitot_tube.*;
import event.radar_altimeter.*;
import event.shock_sensor.*;
import event.stalling_sensor.*;
import event.temperature_sensor.*;

import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;

import event.anti_collision_light.AntiCollisionLightOff;
import event.anti_collision_light.AntiCollisionLightOn;
import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.cargo_compartment_light.CargoCompartmentLightDim;
import event.cargo_compartment_light.CargoCompartmentLightOff;
import event.cargo_compartment_light.CargoCompartmentLightOn;
import event.cost_optimizer.*;
import event.gear.*;
import event.hydraulic_pump.*;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.logo_light.LogoLightOff;
import event.logo_light.LogoLightOn;
import event.route_management.*;
import event.rudder.*;

import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.io.ObjectInputFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {
    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> slatPortList;
    private ArrayList<Object> wasteWaterTankPortList;
    private ArrayList<Object> portableWaterTankPortList;
    private ArrayList<Object> oxygenBottlePortList;
    private ArrayList<Object> nitrogenBottlePortList;
    private ArrayList<Object> vHFPortList;
    private ArrayList<Object> satComPortList;
    private ArrayList<Object> cameraPortList;
    private ArrayList<Object> gpsPortList;
    private ArrayList<Object> radarPortList;
    private ArrayList<Object> tcasPortList;
    private ArrayList<Object> turbulentAirFlowSensorPortList;


    private ArrayList<Object> shockSensorPortList;
    private ArrayList<Object> stallingSensorPortList;
    private ArrayList<Object> temperatureSensorPortList;
    private ArrayList<Object> airFlowSensorPortList;

    private ArrayList<Object> batteryPortList;
    private ArrayList<Object> deIcingSystemPortList;
    private ArrayList<Object> pitotTubePortList;
    private ArrayList<Object> radarAltimeterPortList;

    // Add a new list for each service...

    private ArrayList<Object> apuList;
    private ArrayList<Object> gearList;
    private ArrayList<Object> hydraulicPumpList;
    private ArrayList<Object> airConditioningList;
    private ArrayList<Object> kitchenList;
    private ArrayList<Object> rudderPortList;
    private ArrayList<Object> routeManagementPortList;
    private ArrayList<Object> antiCollisionLightPortList;
    private ArrayList<Object> cargoCompartmentLightPortList;
    private ArrayList<Object> landingLightPortList;
    private ArrayList<Object> logoLightPortList;
    private ArrayList<Object> costOptimizerPortList;


    public Body() {
        weatherRadarPortList = new ArrayList<>();
        slatPortList = new ArrayList<>();

        shockSensorPortList = new ArrayList<>();
        stallingSensorPortList = new ArrayList<>();
        temperatureSensorPortList = new ArrayList<>();
        airFlowSensorPortList = new ArrayList<>();

        batteryPortList = new ArrayList<>();
        deIcingSystemPortList = new ArrayList<>();
        pitotTubePortList = new ArrayList<>();
        radarAltimeterPortList = new ArrayList<>();

        // Add a new list for each service...
        apuList = new ArrayList<>();
        gearList = new ArrayList<>();
        hydraulicPumpList = new ArrayList<>();
        airConditioningList = new ArrayList<>();
        kitchenList = new ArrayList<>();

        weatherRadarPortList = new ArrayList<>();
        rudderPortList = new ArrayList<>();
        routeManagementPortList = new ArrayList<>();
        antiCollisionLightPortList = new ArrayList<>();
        cargoCompartmentLightPortList = new ArrayList<>();
        landingLightPortList = new ArrayList<>();
        logoLightPortList = new ArrayList<>();
        costOptimizerPortList = new ArrayList<>();

        wasteWaterTankPortList = new ArrayList<>();
        portableWaterTankPortList = new ArrayList<>();
        oxygenBottlePortList = new ArrayList<>();
        nitrogenBottlePortList = new ArrayList<>();
        vHFPortList = new ArrayList<>();
        satComPortList = new ArrayList<>();
        cameraPortList = new ArrayList<>();
        gpsPortList = new ArrayList<>();
        radarPortList = new ArrayList<>();
        tcasPortList = new ArrayList<>();
        turbulentAirFlowSensorPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
            rudderPortList.add(RudderFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
            routeManagementPortList.add(RouteManagementFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfAntiCollisionLight; i++) {
            antiCollisionLightPortList.add(AntiCollisionLightFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfCompartmentLight; i++) {
            cargoCompartmentLightPortList.add(CargoCompartmentLightFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
            landingLightPortList.add(LandingLightFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
            logoLightPortList.add(LogoLightFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
            costOptimizerPortList.add(CostOptimizerFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
            apuList.add(APUFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
            gearList.add(APUFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
            hydraulicPumpList.add(APUFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfAirConditioning; i++) {
            airConditioningList.add(APUFactory.build());
        }


        for (int i = 0; i < Configuration.instance.numberOfShockSensorBody; i++) {
            shockSensorPortList.add(ShockSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfStallingSensorBody; i++) {
            stallingSensorPortList.add(StallingSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorBody; i++) {
            temperatureSensorPortList.add(TemperatureSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfAirFlowSensorBody; i++) {
            airFlowSensorPortList.add(AirFlowSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfBattery; i++)
        {
            batteryPortList.add(BatteryFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++)
        {
            deIcingSystemPortList.add(DeIcingSystemFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfPitotTube; i++)
        {
            pitotTubePortList.add(PitotTubeFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfRadarAltimeter; i++)
        {
            radarAltimeterPortList.add(RadarAltimeterFactory.build());
        }


        // Add a new iteration for each service...
        for (int i = 0; i < Configuration.instance.numberOfKitchen; i++) {
            kitchenList.add(APUFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfWasteWaterTank; i++) {
            wasteWaterTankPortList .add(WasteWaterTankFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfPortableWaterTank; i++) {
            portableWaterTankPortList .add(PortableWaterTankFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfOxygenBottle; i++) {
            oxygenBottlePortList .add(OxygenBottleFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfNitrogenBottle; i++) {
            nitrogenBottlePortList .add(NitrogenBottleFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfVHF; i++) {
            vHFPortList .add(VHFFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfSatCom; i++) {
            vHFPortList .add(SatComFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
            tcasPortList.add(TCASFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfTurbulentAirFlowSensorBody; i++) {
            turbulentAirFlowSensorPortList.add(TurbulentAirFlowSensorFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfCameraBody; i++) {
            cameraPortList.add(CameraFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfGPS; i++) {
            gpsPortList.add(GPSFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfRadar; i++) {
            radarPortList.add(RadarFactory.build());
        }
    }

    // --- WeatherRadar -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(WeatherRadarOn weatherRadarOn) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method onMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive(WeatherRadarOff weatherRadarOff) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method offMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- APU --------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(APUStart apuStart) {
        LogEngine.instance.write("+ Body.receive(" + apuStart.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + apuStart.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method onMethod = apuList.get(i).getClass().getDeclaredMethod("start");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(apuList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isAPUStarted = isOn;
                FlightRecorder.instance.insert("Body", "APU (isOn): " + isOn);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAPUStarted): " + PrimaryFlightDisplay.instance.isAPUStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAPUStarted: " + PrimaryFlightDisplay.instance.isAPUStarted);
    }

    @Subscribe
    public void receive(APUShutdown apuShutdown) {
        LogEngine.instance.write("+ Body.receive(" + apuShutdown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + apuShutdown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method offMethod = apuList.get(i).getClass().getDeclaredMethod("shutdown");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(apuList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isAPUStarted = isOn;
                FlightRecorder.instance.insert("Body", "APU (isOn): " + isOn);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAPUStarted): " + PrimaryFlightDisplay.instance.isAPUStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAPUStarted: " + PrimaryFlightDisplay.instance.isAPUStarted);
    }

    @Subscribe
    public void receive(APUIncreaseRPM apuIncreaseRPM) {
        LogEngine.instance.write("+ Body.receive(" + apuIncreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + apuIncreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method onMethod = apuList.get(i).getClass().getDeclaredMethod("increaseRPM", int.class);
                LogEngine.instance.write("onMethod = " + onMethod);

                int rpm = (int) onMethod.invoke(apuList.get(i), apuIncreaseRPM.getValue());
                LogEngine.instance.write("rpm = " + rpm);

                PrimaryFlightDisplay.instance.rpmAPU = rpm;
                FlightRecorder.instance.insert("Body", "APU (rpm): " + rpm);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmAPU): " + PrimaryFlightDisplay.instance.rpmAPU);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmAPU: " + PrimaryFlightDisplay.instance.rpmAPU);
    }

    @Subscribe
    public void receive(APUDecreaseRPM apuDecreaseRPM) {
        LogEngine.instance.write("+ Body.receive(" + apuDecreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + apuDecreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAPU; i++) {
                Method offMethod = apuList.get(i).getClass().getDeclaredMethod("decreaseRPM", int.class);
                LogEngine.instance.write("offMethod = " + offMethod);

                int rpm = (int) offMethod.invoke(apuList.get(i), apuDecreaseRPM.getValue());
                LogEngine.instance.write("rpm = " + rpm);

                PrimaryFlightDisplay.instance.rpmAPU = rpm;
                FlightRecorder.instance.insert("Body", "APU (rpm): " + rpm);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmAPU): " + PrimaryFlightDisplay.instance.rpmAPU);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmAPU: " + PrimaryFlightDisplay.instance.rpmAPU);
    }

    // --- AntiCollisionLight -----------------------------------------------------------------------------------------

    @Subscribe
    public void receive(AntiCollisionLightOn antiCollisionLightOn) {
        LogEngine.instance.write("+ Body.receive(" + antiCollisionLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + antiCollisionLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAntiCollisionLight; i++) {
                Method method = antiCollisionLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(antiCollisionLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isAntiCollisionLightOn = isOn;
                FlightRecorder.instance.insert("Body", "AntiCollisionLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAntiCollisionLightOn): " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAntiCollisionLightOn: " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
    }

    @Subscribe
    public void receive(AntiCollisionLightOff antiCollisionLightOff) {
        LogEngine.instance.write("+ Body.receive(" + antiCollisionLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + antiCollisionLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAntiCollisionLight; i++) {
                Method method = antiCollisionLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(antiCollisionLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isAntiCollisionLightOn = isOn;
                FlightRecorder.instance.insert("Body", "AntiCollisionLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAntiCollisionLightOn): " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAntiCollisionLightOn: " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- CargoCompartmentLight --------------------------------------------------------------------------------------

    @Subscribe
    public void receive(CargoCompartmentLightOn cargoCompartmentLightOn) {
        LogEngine.instance.write("+ Body.receive(" + cargoCompartmentLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCompartmentLight; i++) {
                Method method = cargoCompartmentLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(cargoCompartmentLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
                FlightRecorder.instance.insert("Body", "CargoCompartmentLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCargoCompartmentLightOn): " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCargoCompartmentLightOn: " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
    }

    @Subscribe
    public void receive(CargoCompartmentLightOff cargoCompartmentLightOff) {
        LogEngine.instance.write("+ Body.receive(" + cargoCompartmentLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCompartmentLight; i++) {
                Method method = cargoCompartmentLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(cargoCompartmentLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
                FlightRecorder.instance.insert("Body", "CargoCompartmentLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCargoCompartmentLightOn): " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCargoCompartmentLightOn: " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
    }

    @Subscribe
    public void receive(CargoCompartmentLightDim cargoCompartmentLightDim) {
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightDim.toString() + ")");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- Rudder -----------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RudderNeutral rudderNeutral) {
        LogEngine.instance.write("+ Body.receive(" + rudderNeutral.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method method = rudderPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    @Subscribe
    public void receive(RudderFullLeft rudderFullLeft) {
        LogEngine.instance.write("+ Body.receive(" + rudderFullLeft.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderFullLeft.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method method = rudderPortList.get(i).getClass().getDeclaredMethod("fullLeft");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    @Subscribe
    public void receive(RudderFullRight rudderFullRight) {
        LogEngine.instance.write("+ Body.receive(" + rudderFullRight.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderFullRight.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method method = rudderPortList.get(i).getClass().getDeclaredMethod("fullRight");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    @Subscribe
    public void receive(RudderLeft rudderLeft) {
        LogEngine.instance.write("+ Body.receive(" + rudderLeft.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderLeft.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method method = rudderPortList.get(i).getClass().getDeclaredMethod("left", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rudderPortList.get(i), rudderLeft.getDegree());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    @Subscribe
    public void receive(RudderRight rudderRight) {
        LogEngine.instance.write("+ Body.receive(" + rudderRight.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderRight.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method method = rudderPortList.get(i).getClass().getDeclaredMethod("right", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rudderPortList.get(i), rudderRight.getDegree());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- RouteManagement --------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RouteManagementOn routeManagementOn) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method method = routeManagementPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRouteManagementOn = isOn;
                FlightRecorder.instance.insert("Body", "RouteManagement (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRouteManagementOn): " + PrimaryFlightDisplay.instance.isRouteManagementOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRouteManagementOn: " + PrimaryFlightDisplay.instance.isRouteManagementOn);
    }

    @Subscribe
    public void receive(RouteManagementOff routeManagementOff) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method method = routeManagementPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRouteManagementOn = isOn;
                FlightRecorder.instance.insert("Body", "RouteManagement (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRouteManagementOn): " + PrimaryFlightDisplay.instance.isRouteManagementOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRouteManagementOn: " + PrimaryFlightDisplay.instance.isRouteManagementOn);
    }

    @Subscribe
    public void receive(RouteManagementAdd routeManagementAdd) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementAdd.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementAdd.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method method = routeManagementPortList.get(i).getClass().getDeclaredMethod("add", CheckPoint.class);
                LogEngine.instance.write("onMethod = " + method);

                int number = (int) method.invoke(routeManagementPortList.get(i), routeManagementAdd.getCheckPoint());
                LogEngine.instance.write("size = " + number);

                PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement = number;
                FlightRecorder.instance.insert("Body", "RouteManagement (size): " + number);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsRouteManagement): " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsRouteManagement: " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
    }

    @Subscribe
    public void receive(RouteManagementRemove routeManagementRemove) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementRemove.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementRemove.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method method = routeManagementPortList.get(i).getClass().getDeclaredMethod("remove", CheckPoint.class);
                LogEngine.instance.write("onMethod = " + method);

                int number = (int) method.invoke(routeManagementPortList.get(i), routeManagementRemove.getCheckPoint());
                LogEngine.instance.write("size = " + number);

                PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement = number;
                FlightRecorder.instance.insert("Body", "RouteManagement (size): " + number);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsRouteManagement): " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsRouteManagement: " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
    }

    @Subscribe
    public void receive(RouteManagementSetCostIndex routeManagementSetCostIndex) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementSetCostIndex.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementSetCostIndex.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method method = routeManagementPortList.get(i).getClass().getDeclaredMethod("setCostIndex", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int costIndex = (int) method.invoke(routeManagementPortList.get(i), routeManagementSetCostIndex.getValue());
                LogEngine.instance.write("costIndex = " + costIndex);

                PrimaryFlightDisplay.instance.indexRouteManagement = costIndex;
                FlightRecorder.instance.insert("Body", "RouteManagement (costIndex): " + costIndex);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (indexRouteManagement): " + PrimaryFlightDisplay.instance.indexRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", ": " + PrimaryFlightDisplay.instance.indexRouteManagement);
    }

    // --- Gear -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(GearUp gearUp) {
        LogEngine.instance.write("+ Body.receive(" + gearUp.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
                Method onMethod = gearList.get(i).getClass().getDeclaredMethod("up");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isGearDown = (boolean) onMethod.invoke(gearList.get(i));
                LogEngine.instance.write("isGearDown = " + isGearDown);

                PrimaryFlightDisplay.instance.isGearDown = isGearDown;
                FlightRecorder.instance.insert("Body", "Gear (isGearDown): " + isGearDown);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGearDown): " + PrimaryFlightDisplay.instance.isGearDown);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGearDown: " + PrimaryFlightDisplay.instance.isGearDown);
    }


    @Subscribe
    public void receive(GearDown gearDown) {
        LogEngine.instance.write("+ Body.receive(" + gearDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
                Method offMethod = gearList.get(i).getClass().getDeclaredMethod("down");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isGearDown = (boolean) offMethod.invoke(gearList.get(i));
                LogEngine.instance.write("isGearDown = " + isGearDown);

                PrimaryFlightDisplay.instance.isAPUStarted = isGearDown;
                FlightRecorder.instance.insert("Body", "Gear (isGearDown): " + isGearDown);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isGearDown): " + PrimaryFlightDisplay.instance.isGearDown);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isGearDown: " + PrimaryFlightDisplay.instance.isGearDown);
    }

    @Subscribe
    public void receive(GearSetBrake gearSetBrake) {
        LogEngine.instance.write("+ Body.receive(" + gearSetBrake.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearSetBrake.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
                Method onMethod = gearList.get(i).getClass().getDeclaredMethod("setBrake");
                LogEngine.instance.write("onMethod = " + onMethod);

                int gearBrakePercentage = (int) onMethod.invoke(gearList.get(i));
                LogEngine.instance.write("setBrake = " + gearBrakePercentage);

                PrimaryFlightDisplay.instance.gearBrakePercentage = gearBrakePercentage;
                FlightRecorder.instance.insert("Body", "Gear (gearBrakePercentage): " + gearBrakePercentage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(GearReleaseBrake gearReleaseBrake) {
        LogEngine.instance.write("+ Body.receive(" + gearReleaseBrake.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearReleaseBrake.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
                Method offMethod = gearList.get(i).getClass().getDeclaredMethod("releaseBrake");
                LogEngine.instance.write("offMethod = " + offMethod);

                int gearBrakePercentage = (int) offMethod.invoke(gearList.get(i));
                LogEngine.instance.write("releaseBrake = " + gearBrakePercentage);

                PrimaryFlightDisplay.instance.gearBrakePercentage = gearBrakePercentage;
                FlightRecorder.instance.insert("Body", "Gear (gearBrakePercentage): " + gearBrakePercentage);
            }
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (gearBrakePercentage): " + PrimaryFlightDisplay.instance.gearBrakePercentage);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "gearBrakePercentage: " + PrimaryFlightDisplay.instance.gearBrakePercentage);
    }

    @Subscribe
    public void receive(GearSetBrakePercentage gearSetBrakePercentage) {
        LogEngine.instance.write("+ Body.receive(" + gearSetBrakePercentage.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + gearSetBrakePercentage.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfGear; i++) {
                Method offMethod = gearList.get(i).getClass().getDeclaredMethod("setBrake", int.class);
                LogEngine.instance.write("offMethod = " + offMethod);

                int gearBrakePercentage = (int) offMethod.invoke(gearList.get(i), gearSetBrakePercentage.getValue());
                LogEngine.instance.write("setBrake = " + gearBrakePercentage);

                PrimaryFlightDisplay.instance.gearBrakePercentage = gearBrakePercentage;
                FlightRecorder.instance.insert("Body", "Gear (gearBrakePercentage): " + gearBrakePercentage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (gearBrakePercentage): " + PrimaryFlightDisplay.instance.gearBrakePercentage);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "gearBrakePercentage: " + PrimaryFlightDisplay.instance.gearBrakePercentage);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- LandingLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LandingLightBodyOn landingLightBodyOn) {
        LogEngine.instance.write("+ Body.receive(" + landingLightBodyOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + landingLightBodyOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
                Method method = landingLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(landingLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightBodyOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightBodyOn): " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLandingLightBodyOn: " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
    }

    @Subscribe
    public void receive(LandingLightBodyOff landingLightBodyOff) {
        LogEngine.instance.write("+ Body.receive(" + landingLightBodyOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + landingLightBodyOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
                Method method = landingLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(landingLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightBodyOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightBodyOn): " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLandingLightBodyOn: " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- LogoLight --------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LogoLightOn logoLightOn) {
        LogEngine.instance.write("+ Body.receive(" + logoLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + logoLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
                Method method = logoLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(logoLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLogoLightOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLogoLightOn): " + PrimaryFlightDisplay.instance.isLogoLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLogoLightOn: " + PrimaryFlightDisplay.instance.isLogoLightOn);
    }

    @Subscribe
    public void receive(LogoLightOff logoLightOff) {
        LogEngine.instance.write("+ Body.receive(" + logoLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + logoLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
                Method method = logoLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(logoLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLogoLightOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLogoLightOn): " + PrimaryFlightDisplay.instance.isLogoLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLogoLightOn: " + PrimaryFlightDisplay.instance.isLogoLightOn);
    }

    // --- HydraulicPump -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(HydraulicPumpBodyCompress hydraulicPumpBodyCompress) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyCompress.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyCompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method onMethod = hydraulicPumpList.get(i).getClass().getDeclaredMethod("compress");
                LogEngine.instance.write("onMethod = " + onMethod);

                int compressed = (int) onMethod.invoke(hydraulicPumpList.get(i));
                LogEngine.instance.write("compressed = " + compressed);
                FlightRecorder.instance.insert("Body", "Gear (compressed): " + compressed);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpBodyCompressAmount hydraulicPumpBodyCompressAmount) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyCompressAmount.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyCompressAmount.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method onMethod = hydraulicPumpList.get(i).getClass().getDeclaredMethod("compress", int.class);
                LogEngine.instance.write("onMethod = " + onMethod);

                int compressed = (int) onMethod.invoke(hydraulicPumpList.get(i), hydraulicPumpBodyCompressAmount.getValue());
                LogEngine.instance.write("compressed = " + compressed);
                FlightRecorder.instance.insert("Body", "Gear (compressed): " + compressed);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpBodyDecompress hydraulicPumpBodyDecompress) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyDecompress.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyDecompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method offMethod = hydraulicPumpList.get(i).getClass().getDeclaredMethod("decompress");
                LogEngine.instance.write("offMethod = " + offMethod);

                int decompressed = (int) offMethod.invoke(hydraulicPumpList.get(i));
                LogEngine.instance.write("decompressed = " + decompressed);
                FlightRecorder.instance.insert("Body", "Gear (decompressed): " + decompressed);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpBodyRefillOil hydraulicPumpBodyRefillOil) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyRefillOil.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyRefillOil.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method onMethod = hydraulicPumpList.get(i).getClass().getDeclaredMethod("refillOil");
                LogEngine.instance.write("onMethod = " + onMethod);

                int hydraulicPumpBodyOilAmount = (int) onMethod.invoke(hydraulicPumpList.get(i));
                LogEngine.instance.write("hydraulicPumpBodyOilAmount = " + hydraulicPumpBodyOilAmount);

                PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount = hydraulicPumpBodyOilAmount;
                FlightRecorder.instance.insert("Body", "Gear (hydraulicPumpBodyOilAmount): " + hydraulicPumpBodyOilAmount);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpBodyRefillOilAmount hydraulicPumpBodyRefillOilAmount) {
        LogEngine.instance.write("+ Body.receive(" + hydraulicPumpBodyRefillOilAmount.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + hydraulicPumpBodyRefillOilAmount.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpBody; i++) {
                Method onMethod = hydraulicPumpList.get(i).getClass().getDeclaredMethod("refillOil", int.class);
                LogEngine.instance.write("onMethod = " + onMethod);

                int hydraulicPumpBodyOilAmount = (int) onMethod.invoke(hydraulicPumpList.get(i), hydraulicPumpBodyRefillOilAmount.getValue());
                LogEngine.instance.write("hydraulicPumpBodyOilAmount = " + hydraulicPumpBodyOilAmount);

                PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount = hydraulicPumpBodyOilAmount;
                FlightRecorder.instance.insert("Body", "Gear (hydraulicPumpBodyOilAmount): " + hydraulicPumpBodyOilAmount);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- CostOptimizer ----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(CostOptimizerOn costOptimizerOn) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCostOptimizerOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCostOptimizerOn): " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCostOptimizerOn: " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
    }



    @Subscribe
    public void receive(CostOptimizerOff costOptimizerOff) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCostOptimizerOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCostOptimizerOn): " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCostOptimizerOn: " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
    }

    @Subscribe
    public void receive(CostOptimizerAddCheckPoint costOptimizerAddCheckPoint) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerAddCheckPoint.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerAddCheckPoint.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("add", CheckPoint.class);
                LogEngine.instance.write("onMethod = " + method);

                int size = (int) method.invoke(costOptimizerPortList.get(i), costOptimizerAddCheckPoint.getCheckPoint());
                LogEngine.instance.write("size = " + size);

                PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer = size;
                FlightRecorder.instance.insert("Body", "CostOptimizer (size): " + size);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsCostOptimizer): " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsCostOptimizer: " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
    }



    @Subscribe
    public void receive(CostOptimizerRemoveCheckPoint costOptimizerRemoveCheckPoint) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerRemoveCheckPoint.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerRemoveCheckPoint.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("remove", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int size = (int) method.invoke(costOptimizerPortList.get(i), costOptimizerRemoveCheckPoint.getCheckPointIndex());
                LogEngine.instance.write("size = " + size);

                PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer = size;
                FlightRecorder.instance.insert("Body", "CostOptimizer (size): " + size);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsCostOptimizer): " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsCostOptimizer: " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
    }


    @Subscribe
    public void receive(CostOptimizerOptimize costOptimizerOptimize) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOptimize.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOptimize.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("optimize", CheckPoint.class);
                LogEngine.instance.write("onMethod = " + method);

                int size = (int) method.invoke(costOptimizerPortList.get(i), costOptimizerOptimize.getCheckPointList());
                LogEngine.instance.write("size = " + size);

                PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer = size;
                FlightRecorder.instance.insert("Body", "CostOptimizer (size): " + size);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsCostOptimizer): " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsCostOptimizer: " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
    }

    @Subscribe
    public void receive(CostOptimizerValidate costOptimizerValidate) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerValidate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerValidate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("validate", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int index = (int) method.invoke(costOptimizerPortList.get(i), costOptimizerValidate.getCostIndex());
                LogEngine.instance.write("index = " + index);

                PrimaryFlightDisplay.instance.indexCostOptimizer = index;
                FlightRecorder.instance.insert("Body", "CostOptimizer (index): " + index);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (indexCostOptimizer): " + PrimaryFlightDisplay.instance.indexCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexCostOptimizer: " + PrimaryFlightDisplay.instance.indexCostOptimizer);
    }

    // --- Battery -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(BatteryCharge batteryCharge){
        LogEngine.instance.write("+ Body.receive(" + batteryCharge.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + batteryCharge.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBattery; i++) {
                Method chargeMethod = batteryPortList.get(i).getClass().getDeclaredMethod("charge");
                LogEngine.instance.write("chargeMethod = " + chargeMethod);

                int charge = (int) chargeMethod.invoke(batteryPortList.get(i));
                LogEngine.instance.write("charge = " + charge);

                PrimaryFlightDisplay.instance.percentageBattery = charge;
                FlightRecorder.instance.insert("Body", "Battery (charge): " + charge);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (BatteryCharge): " + PrimaryFlightDisplay.instance.percentageBattery);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "BatteryCharge: " + PrimaryFlightDisplay.instance.percentageBattery);
    }

    @Subscribe
    public void receive(BatteryDischarge batteryDischarge){
        LogEngine.instance.write("+ Body.receive(" + batteryDischarge.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + batteryDischarge.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBattery; i++) {
                Method dischargeMethod = batteryPortList.get(i).getClass().getDeclaredMethod("charge");
                LogEngine.instance.write("dischargeMethod = " + dischargeMethod);

                int discharge =(int) dischargeMethod.invoke(batteryPortList.get(i));
                LogEngine.instance.write("discharge = " + discharge);

                PrimaryFlightDisplay.instance.percentageBattery = discharge;
                FlightRecorder.instance.insert("Body", "Battery (discharge): " + discharge);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (BatteryDischarge): " + PrimaryFlightDisplay.instance.percentageBattery);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "BatteryDischarge: " + PrimaryFlightDisplay.instance.percentageBattery);
    }

    // --- DeIcingSystem -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(DeIcingSystemActivate deIcingSystemActivate){
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemActivate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + deIcingSystemActivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method activateMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("activate");
                LogEngine.instance.write("activateMethod = " + activateMethod);

                boolean active = (boolean) activateMethod.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("active = " + active);

                PrimaryFlightDisplay.instance.isDeIcingSystemActivated = active;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (active): " + active);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingSystemActivate): " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingSystemActivate: " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
    }

    @Subscribe
    public void receive(DeIcingSystemDeactivate deIcingSystemDeactivate){
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemDeactivate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + deIcingSystemDeactivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method deactivateMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deactivate");
                LogEngine.instance.write("deactivateMethod = " + deactivateMethod);

                boolean active = (boolean) deactivateMethod.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("active = " + active);

                PrimaryFlightDisplay.instance.isDeIcingSystemActivated = active;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (active): " + active);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingSystemDeactivate): " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingSystemDeactivate: " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
    }

    @Subscribe
    public void receive(DeIcingSystemDeIce deIcingSystemDeIce){
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemDeIce.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + deIcingSystemDeIce.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method deIce = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deIce", int.class);
                LogEngine.instance.write("deIceMethod = " + deIce);

                int amount = (int) deIce.invoke(deIcingSystemPortList.get(i), deIcingSystemDeIce.amount);
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingSystemDeIce): " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingSystemDeIce: " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
    }

    @Subscribe
    public void receive(DeIcingSystemRefill deIcingSystemRefill){
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemRefill.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + deIcingSystemRefill.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method refill = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("refill");
                LogEngine.instance.write("refillMethod = " + refill);

                int amount = (int) refill.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingSystemRefill): " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingSystemRefill: " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
    }

    // --- PitotTube -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(PitotTubeClean pitotTubeClean){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(PitotTubeMeasureStaticPressure pitotTubeMeasureStaticPressure){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(PitotTubeMeasureTotalPressure pitotTubeMeasureTotalPressure){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(PitotTubeMeasureVelocity pitotTubeMeasureVelocity){
        throw new RuntimeException("Not implemented yet.");
    }

    // --- RadarAltimeter -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RadarAltimeterOn radarAltimeterOn){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(RadarAltimeterOff radarAltimeterOff){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(RadarAltimeterSend radarAltimeterSend){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(RadarAltimeterReceive radarAltimeterReceive){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(RadarAltimeterMeasureAltitude radarAltimeterMeasureAltitude){
        throw new RuntimeException("Not implemented yet.");
    }
    // ----------------------------------------------------------------------------------------------------------------

    // --- ShockSensor ------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(ShockSensorBodyCalibrate shockSensorBodyCalibrate) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(ShockSensorBodyMeasure shockSensorBodyMeasure) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- StallingSensor ---------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(StallingSensorBodyMeasure stallingSensorBodyMeasure) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- TemperatureSensor ------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(TemperatureSensorBodyMeasure temperatureSensorBodyMeasure) {
        LogEngine.instance.write("+ Body.receive(" + temperatureSensorBodyMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + temperatureSensorBodyMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorBody; i++) {
                Method measureMethod = temperatureSensorPortList.get(i).getClass().getDeclaredMethod("measure");
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int measure = (int) measureMethod.invoke(temperatureSensorPortList.get(i));
                LogEngine.instance.write("measure = " + measure);

                PrimaryFlightDisplay.instance.temperatureBody = measure;
                FlightRecorder.instance.insert("Body", "TemperatureSensor (Measure): " + measure);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (TemperatureSensor): " + PrimaryFlightDisplay.instance.temperatureBody);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "TemperatureSensor: " + PrimaryFlightDisplay.instance.temperatureBody);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- AirFlowSensor ----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(AirFlowSensorBodyMeasure airFlowSensorBodyMeasure) {
        LogEngine.instance.write("+ Body.receive(" + airFlowSensorBodyMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + airFlowSensorBodyMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAirFlowSensorBody; i++) {
                Method measureMethod = airFlowSensorPortList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int measure = (int) measureMethod.invoke(airFlowSensorPortList.get(i), "");
                LogEngine.instance.write("measure = " + measure);

                PrimaryFlightDisplay.instance.airPressure = measure;
                FlightRecorder.instance.insert("Body", "AirFLowSensor (Measure): " + measure);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (AirFLowSensor): " + PrimaryFlightDisplay.instance.airPressure);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "AirFLowSensor: " + PrimaryFlightDisplay.instance.airPressure);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- WasteWaterTank -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(WasteWaterTankLock lock) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(WasteWaterTankUnlock unlock) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(WasteWaterTankAdd add) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(WasteWaterTankPumpOut pumpOut) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------


    // --- PortableWaterTank -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(PortableWaterTankLock lock) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(PortableWaterTankUnlock unlock) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(PortableWaterTankTakeOut takeOut) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(PortableWaterTankRefill refill) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------


    // --- OxygenBottle -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(OxygenBottleTakeOut takeOut) {
        LogEngine.instance.write("+ Body.receive(" + takeOut.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + takeOut.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfOxygenBottle; i++) {
                Method takeOutMethod = oxygenBottlePortList.get(i).getClass().getDeclaredMethod("takeOut");
                LogEngine.instance.write("takeOutMethod = " + takeOutMethod);

                int oxygenBottleAmount = (int)takeOutMethod.invoke(oxygenBottlePortList.get(i));
                LogEngine.instance.write("oxygenBottleAmount = " + oxygenBottleAmount);

                PrimaryFlightDisplay.instance.oxygenBottleAmount = oxygenBottleAmount;
                FlightRecorder.instance.insert("Body", "oxygenBottle(takeOut):" + oxygenBottleAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (OxygenBottleTakeOut): " + PrimaryFlightDisplay.instance.oxygenBottleAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "OxygenBottleTakeOut: " + PrimaryFlightDisplay.instance.oxygenBottleAmount);
    }

    @Subscribe
    public void receive(OxygenBottleRefill refill) {
        LogEngine.instance.write("+ Body.receive(" + refill.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + refill.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfOxygenBottle; i++) {
                Method refillMethod = oxygenBottlePortList.get(i).getClass().getDeclaredMethod("refill");
                LogEngine.instance.write("refillMethod = " + refillMethod);

                int oxygenBottleAmount = (int)refillMethod.invoke(oxygenBottlePortList.get(i));
                LogEngine.instance.write("oxygenBottleAmount = " + oxygenBottleAmount);

                PrimaryFlightDisplay.instance.oxygenBottleAmount = oxygenBottleAmount;
                FlightRecorder.instance.insert("Body", "oxygenBottle(takeOut):" + oxygenBottleAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (OxygenBottleTakeOut): " + PrimaryFlightDisplay.instance.oxygenBottleAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "OxygenBottleRefill: " + PrimaryFlightDisplay.instance.oxygenBottleAmount);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- NitrogenBottle -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(NitrogenBottleTakeOut takeOut) {
        LogEngine.instance.write("+ Body.receive(" + takeOut.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + takeOut.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfNitrogenBottle; i++) {
                Method takeOutMethod = nitrogenBottlePortList.get(i).getClass().getDeclaredMethod("takeOut");
                LogEngine.instance.write("takeOutMethod = " + takeOutMethod);

                int nitrogenBottleAmount = (int)takeOutMethod.invoke(nitrogenBottlePortList.get(i));
                LogEngine.instance.write("nitrogenBottleAmount = " + nitrogenBottleAmount);

                PrimaryFlightDisplay.instance.nitrogenBottleAmount = nitrogenBottleAmount;
                FlightRecorder.instance.insert("Body", "nitrogenBottle(takeOut):" + nitrogenBottleAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (NitrogenBottleTakeOut): " + PrimaryFlightDisplay.instance.nitrogenBottleAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "NitrogenBottleTakeOut: " + PrimaryFlightDisplay.instance.nitrogenBottleAmount);

    }

    @Subscribe
    public void receive(NitrogenBottleRefill refill) {
        LogEngine.instance.write("+ Body.receive(" + refill.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + refill.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfNitrogenBottle; i++) {
                Method refillMethod = nitrogenBottlePortList.get(i).getClass().getDeclaredMethod("refill");
                LogEngine.instance.write("refillMethod = " + refillMethod);

                int nitrogenBottleAmount = (int)refillMethod.invoke(nitrogenBottlePortList.get(i));
                LogEngine.instance.write("nitrogenBottleAmount = " + nitrogenBottleAmount);

                PrimaryFlightDisplay.instance.nitrogenBottleAmount = nitrogenBottleAmount;
                FlightRecorder.instance.insert("Body", "nitrogenBottle(takeOut):" + nitrogenBottleAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (nitrogenBottleTakeOut): " + PrimaryFlightDisplay.instance.nitrogenBottleAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "nitrogenBottleRefill: " + PrimaryFlightDisplay.instance.nitrogenBottleAmount);

    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- VHF -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(VHFBackwardChannel backwardChannel) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(VHFForwardChannel forwardChannel) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(VHFSearch search) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(VHFSelectChannel selectChannel) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(VHFOff vhfOff) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(VHFOn vhfOn) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- SatCom -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SatComConnect connect) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(SatComOff off) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(SatComOn on) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(SatComReceive receive) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(SatComSend send) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------
    // --- TCAS -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(TCASOn tcasOn) {
        LogEngine.instance.write("+ Body.receive(" + tcasOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
                Method onMethod = tcasPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(tcasPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isTCASOn = isOn;
                FlightRecorder.instance.insert("Body", "TCAS (on):" + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTCASOn): " + PrimaryFlightDisplay.instance.isTCASOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASOn: " + PrimaryFlightDisplay.instance.isTCASOn);
    }

    @Subscribe
    public void receive(TCASConnect tcasConnect) {
        LogEngine.instance.write("+ Body.receive(" + tcasConnect.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasConnect.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
                Method connectMethod = tcasPortList.get(i).getClass().getDeclaredMethod("connect");
                LogEngine.instance.write("connectMethod = " + connectMethod);

                boolean isConnected = (boolean) connectMethod.invoke(tcasPortList.get(i));
                LogEngine.instance.write("isConnected = " + isConnected);

                PrimaryFlightDisplay.instance.isTCASConnected = isConnected;
                FlightRecorder.instance.insert("Body", "DroopNose (fullDown): " + isConnected);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTCASConnected): " + PrimaryFlightDisplay.instance.isTCASConnected);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASConnected: " + PrimaryFlightDisplay.instance.isTCASConnected);
    }

    @Subscribe
    public void receive(TCASScan tcasScan) {
        FlightRecorder.instance.insert("Body", "receive(" + tcasScan.toString() + ")");
    }

    @Subscribe
    public void receive(TCASAlarm tcasAlarm) {
        LogEngine.instance.write("+ Body.receive(" + tcasAlarm.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasAlarm.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
                Method alarmMethod = tcasPortList.get(i).getClass().getDeclaredMethod("alarm");
                LogEngine.instance.write("alarmMethod = " + alarmMethod);

                boolean isAlarm = (boolean) alarmMethod.invoke(tcasPortList.get(i));
                LogEngine.instance.write("isAlarm = " + isAlarm);

                PrimaryFlightDisplay.instance.isTCASAlarm = isAlarm;
                FlightRecorder.instance.insert("Body", "TCAS (alarm): " + isAlarm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTCASAlarm): " + PrimaryFlightDisplay.instance.isTCASAlarm);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASAlarm: " + PrimaryFlightDisplay.instance.isTCASAlarm);
    }

    @Subscribe
    public void receive(TCASDetermineAltitude tcasDetermineAltitude) {
        FlightRecorder.instance.insert("Body", "receive(" + tcasDetermineAltitude.toString() + ")");
    }

    @Subscribe
    public void receive(TCASSetAltitude tcasSetAltitude) {
        LogEngine.instance.write("+ Body.receive(" + tcasSetAltitude.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasSetAltitude.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
                Method setAltitudeMethod = tcasPortList.get(i).getClass().getDeclaredMethod("setAltitude");
                LogEngine.instance.write("setAltitudeMethod = " + setAltitudeMethod);

                int altitude = (int) setAltitudeMethod.invoke(tcasPortList.get(i));
                LogEngine.instance.write("altitudeTCAS = " + altitude);

                PrimaryFlightDisplay.instance.altitudeTCAS = altitude;
                FlightRecorder.instance.insert("Body", "TCAS (altitudeTCAS): " + altitude);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (altitudeTCAS): " + PrimaryFlightDisplay.instance.altitudeTCAS);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "altitudeTCAS: " + PrimaryFlightDisplay.instance.altitudeTCAS);
    }

    @Subscribe
    public void receive(TCASOff tcasOff) {
        LogEngine.instance.write("+ Body.receive(" + tcasOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tcasOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTCAS; i++) {
                Method offMethod = tcasPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOff = (boolean) offMethod.invoke(tcasPortList.get(i));
                LogEngine.instance.write("isOff = " + isOff);

                PrimaryFlightDisplay.instance.isTCASOn = isOff;
                FlightRecorder.instance.insert("Body", "TCAS (off):" + isOff);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTCASOff): " + PrimaryFlightDisplay.instance.isTCASOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTCASOff: " + PrimaryFlightDisplay.instance.isTCASOn);
    }
    // ----------------------------------------------------------------------------------------------------------------
    // --- TurbulentAirFlowSensor -----------------------------------------------------------------------------------------------
    public void receive(TurbulentAirFlowSensorBodyMeasure turbulentAirFlowSensorBodyMeasure) {
        FlightRecorder.instance.insert("Body", "receive(" + turbulentAirFlowSensorBodyMeasure.toString() + ")");
    }

}