package factory;

import configuration.Configuration;

public class SpoilerFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToSpoilerJavaArchive, "SpoilerFactory");
    }
}
