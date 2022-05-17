package com.chenchen.Test;

import com.chenchen.entity.Author;
import com.chenchen.entity.Book;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {

        // 找出所有年龄小于18岁的作家名字
        //test1();

        // 将数组转化成stream流对象
        //test2();

        // 将map转化成stream流对象
        //test3();

        // 找出5年前年龄小于10岁的作家
        //test4();

        // 按作家年龄降序排列,并只输出年龄最大的两个作家
        //test5();

        // 打印所有书籍的名称,使用flatMap
        //test6();

        // 找出所有书记的分类，使用flatMap
        //test7();

        // 这些作家一共写了多少本书
        //test8();

        // 找出最贵与最便宜的书
        //test9();

        // 获取一个可以存放所有作家名字的集合分别使用list，set，map
        //test10();

        // 判断是否有29岁以上的作家
        //test11();

        // 是否存在未成年作家（18岁以下）
        //test12();

        // 判断是否作家都超过100岁
        //test13();

        // 获取任意一个年龄大于18的作家，存在返回姓名
        //test14();

        // 获取年龄最小的一个作家，存在返回姓名
        //test15();

        // 用reduce求所有作者的年龄和
        //test16();

        // 用reduce求作者年龄的最大最小值
        test17();
    }

    /**
     * 用reduce求作者年龄的最大最小值
     */
    private static void test17() {
        Optional<Integer> max = getAuthors().stream()
                .map(author -> author.getAge())
                .reduce((result, element) -> result > element ? result : element);
        System.out.println(max.get());
    }

    /**
     * 用reduce求所有作者的年龄和
     */
    private static void test16() {
        Integer reduce = getAuthors().stream()
                .map(author -> author.getAge())
                .reduce(0, (result, element) -> result + element);
        System.out.println(reduce);
    }

    private static void test15() {
        Optional<Author> first = getAuthors().stream()
                .sorted((o1, o2) -> {
                    int i = o1.getAge() - o2.getAge();
                    return i;
                })
                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));
    }

    /**
     * 获取任意一个年龄大于18的作家，存在返回姓名
     */
    private static void test14() {
        Optional<Author> any = getAuthors().stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        any.ifPresent(author -> System.out.println("姓名：" + author.getName()));
    }

    /**
     * 判断是否作家都超过100岁
     */
    private static void test13() {
        boolean b = getAuthors().stream().noneMatch(author -> author.getAge() < 100);
        System.out.println("判断是否作家都超过100岁:" + b);
    }

    /**
     * 是否存在未成年作家（18岁以下）
     */
    private static void test12() {
        boolean isExit = getAuthors().stream()
                .allMatch(author -> author.getAge() < 18);
        System.out.println("是否存在未成年作家：" + isExit);
    }

    /**
     * 判断是否有29岁以上的作家
     */
    private static void test11() {
        boolean b = getAuthors().stream().anyMatch(author -> author.getAge() > 29);
        System.out.println("是否存在29岁以上的作家：" + b);
    }

    /**
     * 获取一个可以存放所有作家名字的集合分别使用list，set，map
     */
    private static void test10() {
        // 作者姓名
        List<String> nameList = getAuthors().stream()
                .map(author -> author.getName())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(nameList);

        // 书名
        Set<String> bookNameList = getAuthors().stream()
                .flatMap(author -> author.getBookList().stream())
                .map(book -> book.getName())
                .collect(Collectors.toSet());
        System.out.println(bookNameList);

        // 作家，书map
        Map<String, List<Book>> collect = getAuthors().stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBookList()));
        System.out.println(collect);
    }

    /**
     * 找出最贵与最便宜的书
     */
    private static void test9() {
        Optional<Double> max = getAuthors().stream()
                .flatMap(author -> author.getBookList().stream())
                .map(book -> book.getScore())
                .max((o1, o2) -> o1.intValue() - o2.intValue());

        Optional<Double> min = getAuthors().stream()
                .flatMap(author -> author.getBookList().stream())
                .map(book -> book.getScore())
                .min((o1, o2) -> o1.intValue() - o2.intValue());

        System.out.println("图书最高价为：" + max.get());
        System.out.println("图书最低价为：" + min.get());
    }

    /**
     * 这些作家一共写了多少本书
     */
    private static void test8() {
        long count = getAuthors().stream()
                .flatMap(author -> author.getBookList().stream())
                .distinct()
                .count();
        System.out.println("一共写了" + count + "本书");
    }

    /**
     * 找出所有书记的分类，使用flatMap
     */
    private static void test7() {
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(author -> author.getBookList().stream())
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category -> System.out.println(category));
    }

    /**
     * 打印所有书籍的名称,使用flatMap
     */
    private static void test6() {
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(author -> author.getBookList().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
    }

    /**
     * 按作家年龄降序排列,并只输出年龄最大的两个作家
     */
    private static void test5() {
        List<Author> authors = getAuthors();
        authors.stream()
                .sorted((Author o1, Author o2) -> o2.getAge() - o1.getAge())
                .limit(2)
                .forEach(author -> System.out.println("姓名：" + author.getName() + " 年龄：" + author.getAge()));
    }

    /**
     * 找出5年前年龄小于10岁的作家
     */
    private static void test4() {
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(author -> author.getAge() - 5 > 0)    // 过滤5年前还未出生的作家
                .filter(author -> author.getAge() -5 < 10)    // 过滤5年前年龄大于10的作家
                .map(author -> author.getName())
                .forEach(name -> System.out.println(name));
    }

    /**
     * 将map转化成stream流对象
     */
    private static void test3() {
        Map<String, Integer> map = new HashMap<>();
        map.put("papapa", 1);
        map.put("hahaha", 2);
        map.put("lalala", 3);
        map.put("pupupu", 4);

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        entrySet.stream()
                .filter(entry -> entry.getValue() < 3)
                .forEach(entry -> System.out.println("key: " + entry.getKey() + " value: " + entry.getValue().toString()));
    }

    /**
     * 将数组转化成stream流对象
     */
    private static void test2() {
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Arrays.stream(ints).filter(i -> i < 7)
                .forEach(value -> System.out.println(value));
    }

    /**
     * 找出所有年龄小于18岁的作家名字
     */
    private static void test1() {
        getAuthors().stream()
                .distinct()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getName()));
    }

    // 初始化一些数据
    private static List<Author> getAuthors() {
        Author author1 = new Author(1L, "杨杰炜", "my introduction 1", 18, null);
        Author author2 = new Author(2L, "yjw", "my introduction 2", 19, null);
        Author author3 = new Author(2L, "yjw", "my introduction 2", 19, null);
        Author author4 = new Author(4L, "wdt", "my introduction 4", 29, null);
        Author author5 = new Author(5L, "wtf", "my introduction 5", 12, null);

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        // 上面是作者和书
        books1.add(new Book(1L, "类别,分类啊", "书名1", 45D, "这是简介哦"));
        books1.add(new Book(2L, "高效", "书名2", 84D, "这是简介哦"));
        books1.add(new Book(3L, "喜剧", "书名3", 83D, "这是简介哦"));

        books2.add(new Book(5L, "天啊", "书名4", 65D, "这是简介哦"));
        books2.add(new Book(6L, "高效", "书名5", 89D, "这是简介哦"));

        books3.add(new Book(7L, "久啊", "书名6", 45D, "这是简介哦"));
        books3.add(new Book(8L, "高效", "书名7", 44D, "这是简介哦"));
        books3.add(new Book(9L, "喜剧", "书名8", 81D, "这是简介哦"));

        author1.setBookList(books1);
        author2.setBookList(books2);
        author3.setBookList(books2);
        author4.setBookList(books3);
        author5.setBookList(books2);

        return new ArrayList<>(Arrays.asList(author1, author2, author3, author4, author5));
    }

}
