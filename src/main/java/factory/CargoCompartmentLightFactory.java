package factory;

import configuration.Configuration;

public class CargoCompartmentLightFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToCargoCompartmentLightJavaArchive, "CargoCompartmentLight");
    }
}
