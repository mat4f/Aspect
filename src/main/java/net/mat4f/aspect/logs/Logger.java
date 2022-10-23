package net.mat4f.aspect.logs;

import net.mat4f.aspect.Aspect;

import java.io.PrintStream;

public class Logger  {
    private static boolean  ASP_LOG_INITIALIZED = false;
    private static final String[] ASP_LOG_LEVELS      = {
        "INTERNAL", "FATAL", "ERROR", "WARNING", "INFO", "DEBUG", "TRACE"
    }; // 0 = INTERNAL, 1 = FATAL, etc.
    private static final Integer INTERNAL = 0, FATAL = 1, ERROR = 2, WARNING = 3, INFO = 4, DEBUG = 5, TRACE = 6;
    private static void aspRequireInitialization() {
        if (ASP_LOG_INITIALIZED) return;
        ASP_LOG_INITIALIZED = true;
        Aspect.aspAddSwitch("ASP_LOG_INTERNALS");
        Aspect.aspAddSwitch("ASP_LOG_FATALS");
        Aspect.aspAddSwitch("ASP_LOG_ERRORS");
        Aspect.aspAddSwitch("ASP_LOG_WARNINGS");
        Aspect.aspAddSwitch("ASP_LOG_INFOS");
        Aspect.aspAddSwitch("ASP_LOG_DEBUGS");
        Aspect.aspAddSwitch("ASP_LOG_TRACES");
        // Set Defaults
        Aspect.aspEnable("ASP_LOG_INTERNALS");
        Aspect.aspEnable("ASP_LOG_FATALS");
        Aspect.aspEnable("ASP_LOG_ERRORS");
        Aspect.aspEnable("ASP_LOG_WARNINGS");
        Aspect.aspEnable("ASP_LOG_INFOS");
        Aspect.aspEnable("ASP_LOG_DEBUGS");
        Aspect.aspEnable("ASP_LOG_TRACES");
    }

    private static void aspLogOutput(Integer level, String message, PrintStream stream) {
        aspRequireInitialization();
        stream.println("[ " + ASP_LOG_LEVELS[level] + " ] " + message);
    }

    public static String aspGetStackTrace(String prefix, StackTraceElement[] trace, String suffix) {
        aspRequireInitialization();
        StringBuilder result = new StringBuilder();
        for (StackTraceElement el : trace) result.append(prefix).append(el).append(suffix);
        return result.toString();
    }

    // Internal, Fatal and Error are seen as Error Logs and Require a Stack Trace to be included in the Method Call
    public static void aspInternal(String error, String cause, StackTraceElement[] stackTrace) {
        aspRequireInitialization();
        if (Aspect.aspIsDisabled("ASP_LOG_INTERNALS")) return;
        aspLogOutput(INTERNAL, error + " : " + cause + aspGetStackTrace("\r\n\tat ", stackTrace, ""), System.err);
    }

    // Internal, Fatal and Error are seen as Error Logs and Require a Stack Trace to be included in the Method Call
    public static void aspFatal(String error, String cause, StackTraceElement[] stackTrace) {
        aspRequireInitialization();
        if (Aspect.aspIsDisabled("ASP_LOG_FATALS")) return;
        aspLogOutput(FATAL, error + " : " + cause + aspGetStackTrace("\r\n\tat ", stackTrace, ""), System.err);
    }

    // Internal, Fatal and Error are seen as Error Logs and Require a Stack Trace to be included in the Method Call
    public static void aspError(String error, String cause, StackTraceElement[] stackTrace) {
        aspRequireInitialization();
        if (Aspect.aspIsDisabled("ASP_LOG_ERRORS")) return;
        aspLogOutput(ERROR, error + " : " + cause + aspGetStackTrace("\r\n\tat ", stackTrace, ""), System.err);
    }

    public static void aspWarning(String message) {
        aspRequireInitialization();
        if (Aspect.aspIsDisabled("ASP_LOG_WARNINGS")) return;
        aspLogOutput(WARNING, message, System.out);
    }

    public static void aspInfo(String message) {
        aspRequireInitialization();
        if (Aspect.aspIsDisabled("ASP_LOG_INFOS")) return;
        aspLogOutput(INFO, message, System.out);
    }

    public static void aspDebug(String message) {
        aspRequireInitialization();
        if (Aspect.aspIsDisabled("ASP_LOG_DEBUGS")) return;
        aspLogOutput(DEBUG, message, System.out);
    }

    public static void aspTrace(String message) {
        aspRequireInitialization();
        if (Aspect.aspIsDisabled("ASP_LOG_TRACES")) return;
        aspLogOutput(TRACE, message, System.out);
    }

}
