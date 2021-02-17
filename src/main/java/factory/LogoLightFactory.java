package factory;

import configuration.Configuration;

public class LogoLightFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToLogoLightJavaArchive, "LogoLight");
    }
}
