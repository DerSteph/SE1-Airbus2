package factory;

import configuration.Configuration;

public class FuelTankFactory extends Factory
{
    public static Object build() {
        return Factory.build(Configuration.instance.pathToFuelTankJavaArchive, "FuelTank");
    }
}
