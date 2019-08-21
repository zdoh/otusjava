package ru.zdoher.hw01;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

public class HelloOtus {
    public static void main(String[] args) {
        ListMultimap<String, String> listMultimap = MultimapBuilder.treeKeys().arrayListValues().build();
        System.out.println("Create and print ListMultimap from google.guava");
        listMultimap.put("年", "半年");
        listMultimap.put("年", "中年");
        listMultimap.put("年", "四年");
        listMultimap.put("新", "新車");
        listMultimap.put("新", "新聞");
        System.out.println(listMultimap);
        System.out.println();

        System.out.println("Add to kanji 新 word 新婚 and print ListMultimap");
        listMultimap.put("新", "新婚");
        System.out.println(listMultimap);
        System.out.println();

        System.out.println("Remove from kanji 年 two word 四年 and 半年 and print ListMultimap");
        listMultimap.remove("年", "半年");
        listMultimap.remove("年", "四年");
        System.out.println(listMultimap);
        System.out.println();

        System.out.println("Remove kanji 新 and print ListMultimap");
        listMultimap.removeAll("新");
        System.out.println(listMultimap);
        System.out.println();



    }
}
