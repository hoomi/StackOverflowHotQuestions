package ostovari.android.com.stackoverflowhotquestions.utils;

import android.util.Log;

import ostovari.android.com.stackoverflowhotquestions.BuildConfig;


@SuppressWarnings("ALL")
public final class Logger {

    protected final static boolean SHOW_LINE_NUMBER = true;
    protected final static boolean DEBUG = true;

    public static void i(Object object, String message) {
        if (BuildConfig.DEBUG && DEBUG) {
            String debugMessage = message;
            if (SHOW_LINE_NUMBER) {
                debugMessage = addAdditionalInformation() + message;
            }
            if (object instanceof String) {
                Log.i((String) object, debugMessage);
            } else {
                Log.i(object.getClass().getSimpleName(), debugMessage);
            }
        }
    }

    public static void d(Object object, String message) {
        if (BuildConfig.DEBUG && DEBUG) {
            String debugMessage = message;
            if (SHOW_LINE_NUMBER) {
                debugMessage = addAdditionalInformation() + message;
            }
            if (object instanceof String) {
                Log.d((String) object, debugMessage);
            } else {
                Log.d(object.getClass().getSimpleName(), debugMessage);
            }
        }
    }

    public static void w(Object object, String message) {
        if (BuildConfig.DEBUG && DEBUG) {
            String debugMessage = message;
            if (SHOW_LINE_NUMBER) {
                debugMessage = addAdditionalInformation() + message;
            }
            if (object instanceof String) {
                Log.w((String) object, debugMessage);
            } else {
                Log.w(object.getClass().getSimpleName(), debugMessage);
            }
        }
    }

    public static void e(Object object, String message) {
        if (BuildConfig.DEBUG && DEBUG) {
            String debugMessage = message;
            if (SHOW_LINE_NUMBER) {
                debugMessage = addAdditionalInformation() + message;
            }
            if (object instanceof String) {
                Log.e((String) object, debugMessage);
            } else {
                Log.e(object.getClass().getSimpleName(), debugMessage);
            }
        }
    }

    public static void v(Object object, String message) {
        if (BuildConfig.DEBUG && DEBUG) {
            String debugMessage = message;
            if (SHOW_LINE_NUMBER) {
                debugMessage = addAdditionalInformation() + message;
            }
            if (object instanceof String) {
                Log.v((String) object, debugMessage);
            } else {
                Log.v(object.getClass().getSimpleName(), debugMessage);
            }
        }
    }

    private static String addAdditionalInformation() {
        StackTraceElement trace = Thread.currentThread().getStackTrace()[4];
        return trace.getFileName() + " => " + trace.getClassName() + " => " + trace.getMethodName() + " => " + trace.getLineNumber() + "\n";
    }
}
