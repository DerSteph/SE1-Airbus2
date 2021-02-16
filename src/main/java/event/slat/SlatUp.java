package event.slat;

import event.Subscriber;

public class SlatUp extends Subscriber {

    int degree;

    public SlatUp(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: Slat - Up";
    }

    public int getDegree() {
        return degree;
    }
}
