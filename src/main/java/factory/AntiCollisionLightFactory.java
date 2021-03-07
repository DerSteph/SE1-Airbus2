package factory;

import configuration.Configuration;

public class AntiCollisionLightFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToAntiCollisionLightJavaArchive, "AntiCollisionLight");
    }
}
