package event.left_aileron;

public class LeftAileronDown {

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
