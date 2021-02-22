package factory;

import configuration.Configuration;

public class CostOptimizerFactory {

    public static Object build() {
        return Factory.build(Configuration.instance.pathToCostOptimizerJavaArchive, "CostOptimizer");
    }
}
