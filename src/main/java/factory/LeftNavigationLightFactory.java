package factory;

import configuration.Configuration;

public class LeftNavigationLightFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToLeftNavigationLightJavaArchive, "LeftNavigationLight");
    }
}
