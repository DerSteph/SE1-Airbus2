package event.rudder;

import event.Subscriber;

public class RudderLeft extends Subscriber {

    int degree;

    public RudderLeft(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: Rudder - Left";
    }
}
