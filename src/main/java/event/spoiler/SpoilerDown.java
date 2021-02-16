package event.spoiler;

public class SpoilerDown {

    int degree;

    public SpoilerDown(int degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Event: Spoiler - Down";
    }

    public int getDegree() {
        return degree;
    }

}
