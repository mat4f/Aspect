package net.mat4f.aspect;

import java.util.*;

public class Aspect {
    public static final ArrayList<String> ASP_SWITCHES = new ArrayList<>();
    public static final HashMap<String, String> ASP_VALUES = new HashMap<>();
    public static void aspEnable(String value) {
        if (ASP_SWITCHES.contains(value)) {
            ASP_VALUES.put(value, "ASP_TRUE");
        }
    }
    public static void aspDisable(String value) {
        if (ASP_SWITCHES.contains(value)) {
            ASP_VALUES.put(value, "ASP_FALSE");
        }
    }
    public static void aspProperty(String key, String value) {
        if (ASP_SWITCHES.contains(key)) {
            ASP_VALUES.put(key, value);
        }
    }
    public static void aspAddSwitch(String value) {
        if (!ASP_SWITCHES.contains(value)) ASP_SWITCHES.add(value);
    }
    public static boolean aspIsEnabled(String value) {
        return (ASP_VALUES.get(value).equals("ASP_TRUE"));
    }
    public static boolean aspIsDisabled(String value) {
        return (ASP_VALUES.get(value).equals("ASP_FALSE"));
    }
}
