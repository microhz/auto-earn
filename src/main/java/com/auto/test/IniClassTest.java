package com.auto.test;

/**
 * @author : jihai
 * @date : 2021/3/27
 * @description :
 */
public class IniClassTest {


    /**
     * static 代码块和 类变量赋值，按顺序进行
     *
     */
    static {
        i = 1;
    }
    static int i = 0;
    public static void main(String[] args) {
        System.out.println(i);

        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        new B();
    }
}

interface A {
    int a = 1;
}

class C {
    static int c = 1;
}

class B extends C implements A {
    static {
        System.out.println("sub print interface A " + a);
        System.out.println("sub print super class C " + c);
    }
}
