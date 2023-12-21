package main;

import java.util.Date;

public class Logger {

    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_GREEN = "\u001B[32m";
    private static final String TEXT_YELLOW = "\u001B[33m";
    private static final String TEXT_MAGENTA = "\u001B[35m";
    private static final String TEXT_DEFAULT = "\u001B[39m";
    private static final String LEVEL_INFO = "INFO ";
    private static final String LEVEL_WARN = "WARN ";
    private static final String LEVEL_ERROR = "ERROR";
    private static final String LEVEL_FATAL = "FATAL";
    private static final String LOG_FORMAT = "%s | %s : %s\n";

    public static void info(String message) {
        System.out.printf(LOG_FORMAT, new Date(), TEXT_GREEN + LEVEL_INFO + TEXT_DEFAULT, message);
    }

    public static void warn(String message) {
        System.out.printf(LOG_FORMAT, new Date(), TEXT_YELLOW + LEVEL_WARN + TEXT_DEFAULT, message);
    }

    public static void error(String message) {
        System.out.printf(LOG_FORMAT, new Date(), TEXT_RED + LEVEL_ERROR + TEXT_DEFAULT, message);
    }

    public static void fatal(String message) {
        System.out.printf(LOG_FORMAT, new Date(), TEXT_MAGENTA + LEVEL_FATAL + TEXT_DEFAULT, message);
    }

}
