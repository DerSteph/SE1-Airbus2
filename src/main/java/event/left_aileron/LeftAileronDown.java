package event.left_aileron;

import event.Subscriber;

public class LeftAileronDown extends Subscriber {

    int degree;

    public LeftAileronDown(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: LeftAileron - Down";
    }

    public int getDegree() {
        return degree;
    }
}
