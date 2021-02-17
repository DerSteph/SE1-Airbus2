package section;

import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.deicing_system.DeIcingSystemActivate;
import event.deicing_system.DeIcingSystemDeIce;
import event.deicing_system.DeIcingSystemDeactivate;
import event.deicing_system.DeIcingSystemRefill;
import event.engine_oil_tank.EngineOilTankDecreaseLevel;
import event.engine_oil_tank.EngineOilTankIncreaseLevel;
import event.fuel_tank.FuelTankRefill;
import event.fuel_tank.FuelTankTakeOut;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import factory.DeIcingSystemFactory;
import factory.EngineOilTankFactory;
import factory.FuelTankFactory;
import factory.SlatFactory;
import recorder.FlightRecorder;

import java.util.ArrayList;

public class Wing extends Subscriber {

    private ArrayList<Object> slatPortList;
    private ArrayList<Object> deIcingSystemPortList;
    private ArrayList<Object> engineOilTankPortList;
    private ArrayList<Object> fuelTankPortList;
    // Add a new list for each service...

    public Wing() {
        slatPortList = new ArrayList<>();
        deIcingSystemPortList = new ArrayList<>();
        engineOilTankPortList = new ArrayList<>();
        fuelTankPortList = new ArrayList<>();
        // Add a new list for each service...
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            slatPortList .add(SlatFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemWing; i++)
        {
            deIcingSystemPortList.add(DeIcingSystemFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfEngineOilTank; i++)
        {
            engineOilTankPortList.add(EngineOilTankFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfFuelTank; i++)
        {
            fuelTankPortList.add(FuelTankFactory.build());
        }
        // Add a new iteration for each service...
    }

    // --- Slat -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SlatUp slatUp) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(SlatDown slatDown) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(SlatFullDown slatFullDown) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(SlatNeutral slatNeutral) {
        throw new RuntimeException("Not implemented yet.");
    }

    // --- DeIcingSystem -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(DeIcingSystemActivate deIcingSystemActivate){
        FlightRecorder.instance.insert("Wing", "receive("+ deIcingSystemActivate.toString() + ")");
    }

    @Subscribe
    public void receive(DeIcingSystemDeactivate deIcingSystemDeactivate){
        FlightRecorder.instance.insert("Wing", "receive("+ deIcingSystemDeactivate.toString() + ")");
    }

    @Subscribe
    public void receive(DeIcingSystemDeIce deIcingSystemDeIce){
        FlightRecorder.instance.insert("Wing", "receive("+ deIcingSystemDeIce.toString() + ")");
    }

    @Subscribe
    public void receive(DeIcingSystemRefill deIcingSystemRefill){
        FlightRecorder.instance.insert("Wing", "receive("+ deIcingSystemRefill.toString() + ")");
    }

    // --- EngineOilTank -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(EngineOilTankIncreaseLevel engineOilTankIncreaseLevel){
        FlightRecorder.instance.insert("Wing", "receive("+ engineOilTankIncreaseLevel.toString() + ")");
    }

    @Subscribe
    public void receive(EngineOilTankDecreaseLevel engineOilTankDecreaseLevel){
        FlightRecorder.instance.insert("Wing", "receive("+ engineOilTankDecreaseLevel.toString() + ")");
    }

    // --- FuelTank -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(FuelTankTakeOut fuelTankTakeOut){
        FlightRecorder.instance.insert("Wing", "receive("+ fuelTankTakeOut.toString() + ")");
    }

    @Subscribe
    public void receive(FuelTankRefill fuelTankRefill){
        FlightRecorder.instance.insert("Wing", "receive("+ fuelTankRefill.toString() + ")");
    }

    // ----------------------------------------------------------------------------------------------------------------
}