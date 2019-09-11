package ru.zdoher.hw06;

import ru.zdoher.hw06.annotation.*;
import ru.zdoher.hw06.exception.ExceptionForTest;

public class ClassWithTest {

    @BeforeAll
    public static void beforeAllTest() {
        System.out.println("!!! beforeAllTest");
    }

    @AfterAll
    public static void afterAllTest() {
        System.out.println("!!! afterAllTest");
    }

    @Before
    public void beforeTest() {
        System.out.println("--- beforeTest");
    }

    @After
    public void afterTest() {
        System.out.println("--- afterTest");
    }

    @After
    public void afterTestWithException() {
        System.out.println(3/0);
    }

    @Test
    public void firstTest() {
        System.out.println("firstTest");
    }

    @Test
    public void testWithException() throws ExceptionForTest {
        throw new ExceptionForTest();
    }

    @Test
    private void secondTest() {
        System.out.println("secondTest");
    }

    @Test
    public void thirdTest() {
        System.out.println("thirdTest");
    }


}
