public interface ILeftNavigationLight {

    String version();
    
    LightType setLight(String type);
    
    Position setPosition (String position);

    boolean on();

    boolean off();
    
}
