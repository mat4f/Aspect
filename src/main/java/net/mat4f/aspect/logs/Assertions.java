package net.mat4f.aspect.logs;

import net.mat4f.aspect.Aspect;
import static net.mat4f.aspect.logs.Logger.*;

public class Assertions {
    private static boolean ASP_ASSERT_INITIALIZED = false;
    private static void aspRequireInitialization() {
        if (ASP_ASSERT_INITIALIZED) return;
        ASP_ASSERT_INITIALIZED = true;
        Aspect.aspAddSwitch("ASP_ASSERTIONS");
        // Set Defaults
        Aspect.aspEnable("ASP_ASSERTIONS");
    }

    public static void aspAssert(boolean expression) {
        aspRequireInitialization();
        aspAssert(expression, "Unknown Reason");
    }

    public static void aspAssert(boolean expression, String failure_cause) {
        aspRequireInitialization();
        if (expression) return;
        aspFatal("Assertion Failed", failure_cause, Thread.currentThread().getStackTrace());
        System.exit(-1);
    }
}
