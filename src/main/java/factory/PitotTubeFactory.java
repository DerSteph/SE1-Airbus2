package factory;

import configuration.Configuration;

public class PitotTubeFactory extends Factory
{
    public static Object build()
    {
        return Factory.build(Configuration.instance.pathToPitotTubeJavaArchive, "PitotTube");
    }
}
