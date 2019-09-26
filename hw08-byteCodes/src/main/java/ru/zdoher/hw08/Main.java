package ru.zdoher.hw08;

import ru.zdoher.hw08.logging.IOC;
import ru.zdoher.hw08.logging.TestLogging;

public class Main {
    public static void main(String[] args) {
        TestLogging aClass = IOC.createClass();
        aClass.calculation(9);
        aClass.calculation(1d);
        aClass.calculation("abc");
        aClass.calculation(1);
    }
}
