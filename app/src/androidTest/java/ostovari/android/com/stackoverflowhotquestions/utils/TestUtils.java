package ostovari.android.com.stackoverflowhotquestions.utils;

import java.lang.reflect.Method;

/**
 * Created by hostova1 on 03/08/2014.
 */
public final class TestUtils {
    public static Method getMethod(String methodName, Class targetClass,Class ... argClasses) throws NoSuchMethodException {
        Method method = targetClass.getDeclaredMethod(methodName, argClasses);
        method.setAccessible(true);
        return method;
    }
}
