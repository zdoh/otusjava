package ru.zdoher.hw09;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        fillList();
    }

    public static void fillList() {
        final int MAX_LIST_SIZE = 1_000_000_000;
        final int STEP = 10_000;

        List<Integer> list = new ArrayList<>();
        int count = STEP;


        while(MAX_LIST_SIZE > count) {
            for (int i = 0; i < count; i++) {
                list.add(i);
            }

            list.subList(count / 2, list.size() - 1).clear();

            count += STEP;
            if (count % 1_000_000 == 0) System.out.println(count);
        }


    }
}
