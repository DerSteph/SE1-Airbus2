package factory;

import configuration.Configuration;

public class LandingLight {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToLandingLightJavaArchive, "LandingLight");
    }
}
