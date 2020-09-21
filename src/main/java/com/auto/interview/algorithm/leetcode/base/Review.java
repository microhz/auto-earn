package com.auto.interview.algorithm.leetcode.base;

import java.lang.annotation.*;

/**
 * @author : jihai
 * @date : 2020/9/20
 * @description :
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Review {

    int value();
}
