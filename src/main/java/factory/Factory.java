package factory;

import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public abstract class Factory {

    public static Object build(String configurationPath, String component) {
        Object port = null;

        try {
            URL[] urls = { new File(configurationPath).toURI().toURL() };
            URLClassLoader urlClassLoader = new URLClassLoader(urls, Factory.class.getClassLoader());
            Class clazz = Class.forName(component, true, urlClassLoader);

            String uncapitalized = firstCharToLowerCase(component);
            FlightRecorder.instance.insert(uncapitalized + "Factory", uncapitalized + "Class" + clazz.hashCode());

            Object instance = clazz.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert(component + "Factory", uncapitalized + "Instance: " + instance.hashCode());

            port = clazz.getDeclaredField("port").get(instance);
            FlightRecorder.instance.insert(component + "Factory", component + "Port: " + port.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return port;
    }

    private static String firstCharToLowerCase(String str) {

        if(str == null || str.length() == 0)
            return "";

        if(str.length() == 1)
            return str.toLowerCase();

        char[] chArr = str.toCharArray();
        chArr[0] = Character.toLowerCase(chArr[0]);

        return new String(chArr);
    }
}
