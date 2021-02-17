package event.rudder;

public class RudderLeft {

    int degree;

    public RudderLeft(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: Rudder - Left";
    }

    public int getDegree() {
        return degree;
    }
}
