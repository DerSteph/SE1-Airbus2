package factory;

import configuration.Configuration;

public class LogoLight {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToLogoLightJavaArchive, "LogoLight");
    }
}
