package factory;

import configuration.Configuration;

public class BatteryFactory extends Factory
{
    public static Object build() {
        return Factory.build(Configuration.instance.pathToBatteryJavaArchive, "Battery");
    }
}
