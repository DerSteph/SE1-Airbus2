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
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

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


    public Body() {
        weatherRadarPortList = new ArrayList<>();
        slatPortList = new ArrayList<>();
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

        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            slatPortList .add(SlatFactory.build());
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

    @Subscribe
    public void receive(WeatherRadarScan weatherRadarScan) {
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarScan.toString() + ")");
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
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(OxygenBottleRefill refill) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- NitrogenBottle -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(NitrogenBottleTakeOut takeOut) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(NitrogenBottleRefill refill) {
        throw new RuntimeException("Not implemented yet.");
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
    @Subscribe
    public void receive(TurbulentAirFlowSensorBodyMeasure turbulentAirFlowSensorBodyMeasure) {
        LogEngine.instance.write("+ Body.receive(" + turbulentAirFlowSensorBodyMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + turbulentAirFlowSensorBodyMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTurbulentAirFlowSensorBody; i++) {
                Method alarmMethod = turbulentAirFlowSensorPortList.get(i).getClass().getDeclaredMethod("alarm");
                LogEngine.instance.write("alarmMethod = " + alarmMethod);

                boolean isAlarm = (boolean) alarmMethod.invoke(turbulentAirFlowSensorPortList.get(i));
                LogEngine.instance.write("isTurbulentAirFlowAlarm = " + isAlarm);

                PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm = isAlarm;
                FlightRecorder.instance.insert("Body", "TurbulentAirFlowAlarm (isTurbulentAirFlowAlarm):" + isAlarm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTurbulentAirFlowAlarm): " + PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTurbulentAirFlowAlarm: " + PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);
    }
}