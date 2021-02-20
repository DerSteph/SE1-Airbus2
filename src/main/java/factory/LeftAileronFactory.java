package factory;

import configuration.Configuration;

public class LeftAileronFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToLeftAileronJavaArchive, "LeftAileron");
    }
}
