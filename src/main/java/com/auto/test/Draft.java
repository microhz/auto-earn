package com.auto.test;

/**
 * @author : jihai
 * @date : 2020/8/24
 * @description :
 */
public class Draft {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println(String.format("ALTER TABLE stock_change_log_%04d ADD INDEX sku_id(`sku_id`);", i));
        }
    }
}
