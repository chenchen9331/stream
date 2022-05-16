package com.chenchen.Test;

import com.chenchen.entity.Author;
import com.chenchen.entity.Book;

import java.util.*;
import java.util.function.*;
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
        test8();
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
