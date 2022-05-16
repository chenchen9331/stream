package com.chenchen.entity;

import lombok.*;

import java.util.List;

/**
 * 作家Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Author {

    /* id */
    private Long id;

    /* 姓名 */
    private String name;

    /* 作者介绍 */
    private String introduction;

    /* 年龄 */
    private Integer age;

    /* 作品集合 */
    private List<Book> bookList;
}
