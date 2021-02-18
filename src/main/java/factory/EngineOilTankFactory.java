package factory;

import configuration.Configuration;

public class EngineOilTankFactory extends Factory
{
    public static Object build()
    {
        return Factory.build(Configuration.instance.pathToEngineOilTankJavaArchive, "EngineOilTank");
    }
}
