package ru.zdoher.hw08.logging;

import ru.zdoher.hw08.annotaion.Log;

public class TestLoggingImpl implements TestLogging {

    @Log
    @Override
    public void calculation(int param) {
        System.out.println("Параметр " + param);
    }
}
