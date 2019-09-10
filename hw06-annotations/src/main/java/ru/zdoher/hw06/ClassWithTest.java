package ru.zdoher.hw06;

import ru.zdoher.hw06.annotation.*;
import ru.zdoher.hw06.exception.ExceptionForTest;

public class ClassWithTest {

    @BeforeAll
    public static String beforeAllTest() {
        return "!!! beforeAllTest";
    }

    @AfterAll
    public static String afterAllTest() {
        return "!!! afterAllTest";
    }

    @Before
    public String beforeTest() {
        return "--- beforeTest";
    }

    @After
    public String afterTest() {
        return "--- afterTest";
    }

    @Test
    public String firstTest() {
        return "firstTest";
    }

    @Test
    public String testWithException() throws ExceptionForTest {
        throw new ExceptionForTest();
    }

    @Test
    private String secondTest() {
        return "secondTest";
    }

    @Test
    public String thirdTest() {
        return "thirdTest";
    }


}
