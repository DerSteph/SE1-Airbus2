package event.right_aileron;

import event.Subscriber;

public class RightAileronDown extends Subscriber {

    int degree;

    public RightAileronDown(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: RightAileron - Down";
    }
}
