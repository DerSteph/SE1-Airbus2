public class Brake {

    private String id;
    private long dateOfManufacturing;
    private int percentage = 0;

    public Brake(String id, long dateOfManufacturing){
        this.id=id;
        this.dateOfManufacturing=dateOfManufacturing;
    }

    public int setPercentage(int percentage) {
        if(percentage>=0 && percentage <=100){
            this.percentage = percentage;
        }
        return this.percentage;
    }

    public int getPercentage() {
        return this.percentage;
    }
}
