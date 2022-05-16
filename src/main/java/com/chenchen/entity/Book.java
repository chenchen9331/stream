package com.chenchen.entity;

import lombok.*;

/**
 * 图书Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Book {

    /* id */
    private Long id;

    /* 分类 */
    private String category;

    /* 书名 */
    private String name;

    /* 售价 */
    private Double score;

    /* 介绍 */
    private String introduction;
}
