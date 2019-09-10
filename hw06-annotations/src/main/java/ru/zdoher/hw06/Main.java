package ru.zdoher.hw06;

public class Main {
    public static void main(String[] args) throws Exception {
        TestLuncher testLuncher = new TestLuncher(ClassWithTest.class);
        testLuncher.print();
    }

}
