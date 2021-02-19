package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.apu.APUStart;
import event.engine.EngineStart;
import event.gear.*;
import event.hydraulic_pump.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.APUFactory;
import factory.SlatFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.io.ObjectInputFilter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {

    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> slatPortList;
    // Add a new list for each service...
    private ArrayList<Object> apuList;
    private ArrayList<Object> gearList;
    private ArrayList<Object> hydraulicPumpList;
    private ArrayList<Object> airConditioningList;
    private ArrayList<Object> kitchenList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        slatPortList = new ArrayList<>();
        // Add a new list for each service...
        apuList = new ArrayList<>();
        gearList = new ArrayList<>();
        hydraulicPumpList = new ArrayList<>();
        airConditioningList = new ArrayList<>();
        kitchenList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            slatPortList .add(SlatFactory.build());
        }

        // Add a new iteration for each service...
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
        for (int i = 0; i < Configuration.instance.numberOfKitchen; i++) {
            kitchenList.add(APUFactory.build());
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

    // --- APU -----------------------------------------------------------------------------------------------

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

                LogEngine.instance.write("+");
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

                LogEngine.instance.write("+");
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

                LogEngine.instance.write("+");
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

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmAPU): " + PrimaryFlightDisplay.instance.rpmAPU);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmAPU: " + PrimaryFlightDisplay.instance.rpmAPU);
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

                LogEngine.instance.write("+");
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

                LogEngine.instance.write("+");
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

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (gearBrakePercentage): " + PrimaryFlightDisplay.instance.gearBrakePercentage);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "gearBrakePercentage: " + PrimaryFlightDisplay.instance.gearBrakePercentage);
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

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
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

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (gearBrakePercentage): " + PrimaryFlightDisplay.instance.gearBrakePercentage);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "gearBrakePercentage: " + PrimaryFlightDisplay.instance.gearBrakePercentage);
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

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

                LogEngine.instance.write("+");
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

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpBodyOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpBodyOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount);
    }

}