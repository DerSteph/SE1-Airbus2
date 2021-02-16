package section;

import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import factory.SlatFactory;

import java.util.ArrayList;

public class Wing extends Subscriber {

    private ArrayList<Object> slatPortList;
    // Add a new list for each service...

    public Wing() {
        slatPortList = new ArrayList<>();
        // Add a new list for each service...
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            slatPortList .add(SlatFactory.build());
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

    // ----------------------------------------------------------------------------------------------------------------
}