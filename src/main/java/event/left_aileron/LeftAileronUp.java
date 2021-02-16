package event.left_aileron;

import event.Subscriber;

public class LeftAileronUp extends Subscriber {

    int degree;

    public LeftAileronUp(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: LeftAileron - Up";
    }
}
