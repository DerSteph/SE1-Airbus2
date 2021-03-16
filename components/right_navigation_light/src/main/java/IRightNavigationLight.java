public interface IRightNavigationLight {
    public String version();
    public LightType setLightType(String type);
    public Position setPosition(String position);
    public boolean on();
    public boolean off();

}
