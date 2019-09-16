package ru.zdoher.hw08.logging;

import ru.zdoher.hw08.annotaion.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class IOC {

    public static TestLogging createClass() {
        InvocationHandler invocationHandler = new MyTestLoggingImpl(new TestLoggingImpl());
        return (TestLogging) Proxy.newProxyInstance(IOC.class.getClassLoader(),
                new Class<?>[]{TestLogging.class}, invocationHandler);
    }


    static class MyTestLoggingImpl implements InvocationHandler {
        private final TestLogging testLogging;

        MyTestLoggingImpl(TestLogging testLogging) {
            this.testLogging = testLogging;
        }


        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

            Method tempMethod = testLogging.getClass().getMethod(method.getName(), method.getParameterTypes());

            for (Annotation declaredAnnotation : tempMethod.getDeclaredAnnotations()) {
                if (declaredAnnotation.annotationType().equals(Log.class)) {
                    System.out.println("executed method: " + method.getName() +
                            ", param: " + Arrays.deepToString(objects));
                }
            }

            return method.invoke(testLogging, objects);
        }


    }
}
