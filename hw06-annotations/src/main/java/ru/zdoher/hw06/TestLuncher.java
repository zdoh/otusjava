package ru.zdoher.hw06;

import ru.zdoher.hw06.annotation.*;
import ru.zdoher.hw06.exception.NotStaticMethodException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestLuncher {
    private Class<? extends ClassWithTest> classWithTestClass;
    private List<Method> beforeAllList;
    private List<Method> afterAllList;
    private List<Method> beforeList;
    private List<Method> afterList;
    private List<Method> testList;
    private int goodTest;
    private int badTest;


    public TestLuncher(Class<? extends ClassWithTest> classWithTest) throws NotStaticMethodException {
        this.classWithTestClass = classWithTest;

        beforeAllList = new ArrayList<>();
        beforeList = new ArrayList<>();
        afterAllList = new ArrayList<>();
        afterList = new ArrayList<>();
        testList = new ArrayList<>();

        parseTestClass();
    }

    private void parseTestClass() throws NotStaticMethodException {
        for (Method declaredMethod : classWithTestClass.getDeclaredMethods()) {
            for (Annotation declaredAnnotation : declaredMethod.getDeclaredAnnotations()) {
                if (declaredAnnotation.annotationType().equals(Test.class)) testList.add(declaredMethod);
                if (declaredAnnotation.annotationType().equals(Before.class)) beforeList.add(declaredMethod);
                if (declaredAnnotation.annotationType().equals(After.class)) afterList.add(declaredMethod);
                if (declaredAnnotation.annotationType().equals(BeforeAll.class)) {
                    if (!Modifier.isStatic(declaredMethod.getModifiers()))
                        throw new NotStaticMethodException(declaredMethod.toString() + " is not static");
                    beforeAllList.add(declaredMethod);
                }
                if (declaredAnnotation.annotationType().equals(AfterAll.class)) {
                    if (!Modifier.isStatic(declaredMethod.getModifiers()))
                        throw new NotStaticMethodException(declaredMethod.toString() + " is not static");
                    afterAllList.add(declaredMethod);
                }
            }
        }

    }


    public void print() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        runTests();
        testStatistic();
    }


    private void runTests() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Method method : beforeAllList) {
            doBeforeAfterAll(method);
        }

        for (Method method : testList) {
            Object newInstance = classWithTestClass.getConstructors()[0].newInstance();
            for (Method beforeMethod : beforeList) {
                doBeforeAfter(beforeMethod, newInstance);
            }
            doMethod(method, newInstance);
            for (Method afterMethod : afterList) {
                doBeforeAfter(afterMethod, newInstance);
            }
        }

        for (Method method : afterAllList) {
            doBeforeAfterAll(method);
        }

    }

    private void doBeforeAfterAll(Method method) {
        try {
            if (Modifier.isPrivate(method.getModifiers())) {
                method.setAccessible(true);
                method.invoke(null);
                method.setAccessible(false);
            } else {
                method.invoke(null);
            }
        } catch (Exception e) {
            System.out.println(method.toString() + " threw Exception: " + e.getCause() + " " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doBeforeAfter(Method method, Object newInstance) {
        try {
            doInvoke(method, newInstance);
        } catch (Exception e) {
            System.out.println(method.toString() + " threw Exception: " + e.getCause() + " " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doMethod(Method method, Object newInstance) {
        try {
            doInvoke(method, newInstance);
            goodTest++;
        } catch (Exception e) {
            badTest++;
            System.out.println(method.toString() + " threw Exception: " + e.getCause() + " " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doInvoke(Method method, Object newInstance) throws Exception {
        if (Modifier.isPrivate(method.getModifiers())) {
            method.setAccessible(true);
            method.invoke(newInstance);
            method.setAccessible(false);
        } else {
            method.invoke(newInstance);
        }
    }

    private void testStatistic() {
        System.out.println("Тестирование завершено:");
        System.out.println("Удачных тестов: " + goodTest);
        System.out.println("Тестов с ошибками: " + badTest);
    }

}
