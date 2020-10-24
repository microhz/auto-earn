package com.auto.interview.algorithm.leetcode.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author : jihai
 * @date : 2020/10/23
 * @description : 短连接生成服务
 */
public class ShortUrlGenerator {

    /**
     * 内存缓存的最大值
     */
    private int maxId = 0;

    /**
     * 步长，每次DB获取的长度
     */
    private int delta = 10;

    /**
     * 生成后DB的长短链接映射
     */
    private Map<String, String> dbMap = new HashMap<>();

    public static void main(String[] args) {
        ShortUrlGenerator shortUrlGenerator = new ShortUrlGenerator();
        for (int i = 0; i < 10000; i++) {
            String s = "http://longLinkDomain/" + i;
            String shortUrl = shortUrlGenerator.generate(s);
            System.out.println("生成短链接：" + shortUrl + ", 获取到原始链接为：" + shortUrlGenerator.get(shortUrl));
        }
    }

    // 301重定向长链接
    private String get(String shortUrl) {
        return dbMap.get(shortUrl);
    }

    private static final String DOMAIN = "http://micro/";
    private Table table = new Table();

    public String generate(String str) {
        if (maxId % delta == 0) {
            // 重启，或则内存的id区间消耗完了，请求DB获取区间
            maxId = table.auto();
        }
        int id = maxId --;
        // 0,9,  a,z  A,Z
        // 转换为62进制的4位短链接
        String s = parseInteger2Char(id, 62, (o) -> {
            if (o <= 9) return (char) ('0' + o);
            if (o >= 10 && o < 36) return (char) ('a' + (o - 10));
            return (char) ('A' + (o - 36));
        });
        String ret = DOMAIN + s;
        dbMap.put(ret, str);
        return ret;
    }

    /**
     *
     * @param id 需要转换的id自增
     * @param hex  转换的进制，我这里是用62
     * @param mapperFunction  对应的进制需要用一个数字映射字符的逻辑，回调函数
     * @return
     */
    private String parseInteger2Char(Integer id, int hex, Function<Integer, Character> mapperFunction) {
        /*
        进制转换
         */
        Stack<Integer> stack = new Stack<>();
        while (true) {
            int n = id % hex;
            stack.push(n);
            id /= hex;
            if (id < hex) {
                stack.push(id);
                break;
            }
        }
        /*
        短链接字符映射
         */
        StringBuffer sb = new StringBuffer();
        while (! stack.isEmpty()) {
            sb.append(mapperFunction.apply(stack.pop()));
        }
        return sb.toString();
    }

}

/**
 * 模拟DB自增获取操作
 */
class Table {
    // 原子类，避免并发问题
    private AtomicInteger id = new AtomicInteger(0);

    // 此处统计DB请求次数
    int count = 0;
    public Integer auto() {
        count ++;
        System.out.println("DB请求次数 : " + count);
        return id.getAndAdd(1000);
    }
}
