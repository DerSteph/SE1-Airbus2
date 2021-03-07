package event.kitchen;

public class KitchenFill {
    String trolley;

    public KitchenFill(String trolley){
        this.trolley=trolley;
    }

    @Override
    public String toString() {
        return "Event: Kitchen - Fill";
    }
}
