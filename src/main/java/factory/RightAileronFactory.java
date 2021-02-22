package factory;

import configuration.Configuration;

public class RightAileronFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToRightAileronJavaArchive, "RightAileron");
    }
}
