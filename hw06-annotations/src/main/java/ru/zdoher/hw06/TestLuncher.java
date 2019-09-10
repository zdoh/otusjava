package ru.zdoher.hw06;

import ru.zdoher.hw06.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestLuncher {
    private Class<? extends ClassWithTest> classWithTestClass;
    private List<Method> beforeAllList;
    private List<Method> afterAllList;
    private List<Method> beforeList;
    private List<Method> afterList;
    private List<Method> testList;


    public TestLuncher(Class<? extends ClassWithTest> classWithTest) {
        this.classWithTestClass = classWithTest;

        beforeAllList = new ArrayList<>();
        beforeList = new ArrayList<>();
        afterAllList = new ArrayList<>();
        afterList = new ArrayList<>();
        testList = new ArrayList<>();

        for (Method declaredMethod : classWithTestClass.getDeclaredMethods()) {
            for (Annotation declaredAnnotation : declaredMethod.getDeclaredAnnotations()) {
                if (declaredAnnotation.annotationType().equals(Test.class)) testList.add(declaredMethod);
                if (declaredAnnotation.annotationType().equals(Before.class)) beforeList.add(declaredMethod);
                if (declaredAnnotation.annotationType().equals(After.class)) afterList.add(declaredMethod);
                if (declaredAnnotation.annotationType().equals(BeforeAll.class)) beforeAllList.add(declaredMethod);
                if (declaredAnnotation.annotationType().equals(AfterAll.class)) afterAllList.add(declaredMethod);
            }
        }


    }

    public void print() {
        for (Method method : beforeAllList) {
            doMethod(method);
        }

        for (Method method : testList) {
            for (Method beforeMethod : beforeList) {
                doMethod(beforeMethod);
            }

            doMethod(method);

            for (Method afterMethod : afterList) {
                doMethod(afterMethod);
            }
        }



        for (Method method : afterAllList) {
            doMethod(method);
        }
    }

    private void doMethod(Method method) {
        try {
            if (Modifier.isPrivate(method.getModifiers())) {
                Object newInstance = classWithTestClass.getConstructors()[0].newInstance();
                method.setAccessible(true);

                System.out.println(method.invoke(newInstance));
                method.setAccessible(false);
            } else {
                Object newInstance = classWithTestClass.getConstructors()[0].newInstance();
                System.out.println(method.invoke(newInstance));
            }
        } catch (Exception e) {
            System.out.println("got some exception");
        }
    }


}
