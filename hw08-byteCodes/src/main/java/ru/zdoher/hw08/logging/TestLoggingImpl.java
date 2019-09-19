package ru.zdoher.hw08.logging;

import ru.zdoher.hw08.annotaion.Log;

public class TestLoggingImpl implements TestLogging {

    @Log
    @Override
    public void calculation(int param) {
        System.out.println("Параметр для метода с int " + param);
    }

    @Log
    @Override
    public void calculation(double param) {
        System.out.println("Параметр для метода с double " + param);
    }

    @Override
    public void calculation(String param) {
        System.out.println("Параметр для метода со string " + param);
    }
}
