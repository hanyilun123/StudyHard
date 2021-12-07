package com.study.hard.model;

import java.io.*;
import java.util.HashSet;

public class TestJava {

    public static void bark(){
        System.out.println("父类静态");
    }


    public static void main(String[] args) {
        TestJava t1 = new TestJava();
        TestJava t2 = new StudyJava();
        t1.bark();
        t2.bark();
    }

}



