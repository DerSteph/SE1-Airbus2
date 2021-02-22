package event.rudder;

public class RudderRight {

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
