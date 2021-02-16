package factory;

import configuration.Configuration;

public class CargoComponentLightFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToCargoCompartmentLightJavaArchive, "CargoComponentLight");
    }
}
