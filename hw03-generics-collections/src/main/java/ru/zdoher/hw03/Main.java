package ru.zdoher.hw03;


import java.util.Collections;
import java.util.List;

public class Main {
    private static final int SIZE = 100;

    public static void main(String[] args) {
        String[] tempData = new String[SIZE];
        for (int i = 0; i < SIZE; i++) {
            tempData[i] = String.valueOf(i);
        }

        System.out.println("Проверка Collections.addAll(Collection<? super T> c, T... elements)");
        List<String> diyArrayList = new DIYarrayList<>(true);
        Collections.addAll(diyArrayList, tempData);
        System.out.println(diyArrayList);
        System.out.println();


        System.out.println("Проверка Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)");
        List<String> diyArrayList2 = new DIYarrayList<>(diyArrayList.size(), true);
        Collections.copy(diyArrayList2, diyArrayList);
        System.out.println(diyArrayList2);
        System.out.println();



        System.out.println("Проверка Collections.static <T> void sort(List<T> list, Comparator<? super T> c)");
        Collections.shuffle(diyArrayList2);
        System.out.println("shuffle:");
        System.out.println(diyArrayList2);
        Collections.sort(diyArrayList2);
        System.out.println("sort:");
        System.out.println(diyArrayList2);


    }
}
