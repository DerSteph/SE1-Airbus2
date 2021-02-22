package event.engine;

public class EngineDecreaseRPM {
    int value;
    public EngineDecreaseRPM(int value){
        this.value=value;
    }
    public String toString() {
        return "Event: Engine - Decrease RPM";
    }

    public int getValue() {
        return value;
    }
}