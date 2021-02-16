package event.rudder;

import event.Subscriber;

public class RudderRight extends Subscriber {

    int degree;

    public RudderRight(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: Rudder - Right";
    }

    public int getDegree() {
        return degree;
    }
}
