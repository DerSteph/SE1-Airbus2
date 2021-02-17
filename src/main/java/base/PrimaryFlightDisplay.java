package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;
    public int degreeSlat;
    public boolean isWasteWaterTankLocked;
    public int capacityWasteWater;
    public boolean isPortableWaterTankLocked;
    public int amountPortableWater;
    public int oxygenBottleAmount;
    public int amountOfNitrogen;
    public boolean isVHFOn;
    public String selectedChannelVHF;
}