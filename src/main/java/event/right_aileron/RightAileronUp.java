package event.right_aileron;

import event.Subscriber;

public class RightAileronUp extends Subscriber {

    int degree;

    public RightAileronUp(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: RightAileron - Up";
    }

    public int getDegree() {
        return degree;
    }
}
