package factory;

import configuration.Configuration;

public class RadarAltimeterFactory extends Factory
{
    public static Object build()
    {
        return Factory.build(Configuration.instance.pathToRadarAltimeterJavaArchive, "RadarAltimeter");
    }
}
