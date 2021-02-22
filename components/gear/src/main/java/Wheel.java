public class Wheel {
    private String id;
    private long dateOfManufacturing;
    private Brake brake;

    public Wheel(String id, long dateOfManufacturing, Brake brake){
        this.id=id;
        this.dateOfManufacturing=dateOfManufacturing;
        this.brake=brake;
    }

    public Brake getBrake() {
        return brake;
    }
}
