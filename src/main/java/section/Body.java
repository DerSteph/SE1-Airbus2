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
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
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


    public Body() {
        weatherRadarPortList = new ArrayList<>();
        slatPortList = new ArrayList<>();
        wasteWaterTankPortList = new ArrayList<>();
        portableWaterTankPortList = new ArrayList<>();
        oxygenBottlePortList = new ArrayList<>();
        nitrogenBottlePortList = new ArrayList<>();
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

    // --- OxygenBottle -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(NitrogenBottleTakeOut takeOut) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(NitrogenBottleRefill refill) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------


}