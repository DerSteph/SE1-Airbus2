package factory;

import configuration.Configuration;

public class RudderFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToRudderJavaArchive, "Rudder");
    }
}
