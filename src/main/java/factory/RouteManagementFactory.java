package factory;

import configuration.Configuration;

public class RouteManagementFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToRouteManagementJavaArchive, "RouteManagement");
    }
}
