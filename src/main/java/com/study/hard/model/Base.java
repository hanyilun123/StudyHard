package com.study.hard.model;

public class Base {
    private class A{
        A(){
            int i = 100;
            System.out.println(i);
        }
    };

    private static class B {
        static int i = 200;

    }
}
