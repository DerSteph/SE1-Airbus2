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
    public int nitrogenBottleAmount;
    public int amountOfNitrogen;
    public boolean isVHFOn;
    public String selectedChannelVHF;
    public boolean isSatComConnected;
    public boolean isTCASOn;
    public boolean isTCASConnected;
    public boolean isTCASAlarm;
    public int altitudeTCAS;
    public int degreeDroopNose;
    public boolean isTurbulentAirFlowAlarm;
}