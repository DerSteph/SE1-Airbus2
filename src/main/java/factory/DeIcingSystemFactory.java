package factory;

import configuration.Configuration;

public class DeIcingSystemFactory extends Factory
{
    public static Object build()
    {
        return Factory.build(Configuration.instance.pathToDeIcingSystemJavaArchive, "DeIcingSystem");
    }
}
