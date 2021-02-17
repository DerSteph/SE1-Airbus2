package event.right_aileron;

public class RightAileronDown {

    int degree;

    public RightAileronDown(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: RightAileron - Down";
    }

    public int getDegree() {
        return degree;
    }
}
