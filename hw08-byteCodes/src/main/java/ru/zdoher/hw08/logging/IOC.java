package ru.zdoher.hw08.logging;

import ru.zdoher.hw08.annotaion.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IOC {

    public static TestLogging createClass() {
        InvocationHandler invocationHandler = new MyTestLoggingImpl(new TestLoggingImpl());
        return (TestLogging) Proxy.newProxyInstance(IOC.class.getClassLoader(),
                new Class<?>[]{TestLogging.class}, invocationHandler);
    }


    static class MyTestLoggingImpl implements InvocationHandler {
        private final TestLogging testLogging;
        private Map<Method, Boolean> logAnnotationCache;

        MyTestLoggingImpl(TestLogging testLogging) {
            this.testLogging = testLogging;
            logAnnotationCache = new HashMap<>();
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            Method tempMethod = testLogging.getClass().getMethod(method.getName(), method.getParameterTypes());

            if (!logAnnotationCache.containsKey(method)) addToAnnotationCache(tempMethod);

            if (logAnnotationCache.get(tempMethod)) {
                System.out.println("executed method: " + method.getName() +
                        ", param: " + Arrays.deepToString(objects));
            }

            return method.invoke(testLogging, objects);
        }

        private void addToAnnotationCache(Method method) {
            boolean annotationLogExist = false;

            for (Annotation declaredAnnotation : method.getDeclaredAnnotations()) {
                if (declaredAnnotation.annotationType().equals(Log.class)) {
                    annotationLogExist = true;
                }
            }

            logAnnotationCache.put(method, annotationLogExist);
        }
    }
}
