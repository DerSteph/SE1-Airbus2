package event.slat;

import event.Subscriber;

public class SlatDown extends Subscriber {

    int degree;

    public SlatDown(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: Slat - Down";
    }
}
