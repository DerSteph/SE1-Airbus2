package factory;

import configuration.Configuration;

public class LandingLightFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToLandingLightJavaArchive, "LandingLight");
    }
}
