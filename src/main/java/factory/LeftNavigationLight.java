package factory;

import configuration.Configuration;

public class LeftNavigationLight {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToLeftNavigationLightJavaArchive, "NavigationLight");
    }
}
