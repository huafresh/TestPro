package com.hua.test_java;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import sun.misc.Unsafe;

public class JavaMain {

    public static void main(String[] args) {

//        ClassLoader classLoader = TestClass.class.getClassLoader();
//        while (classLoader != null) {
//            System.out.println(classLoader);
//            classLoader = classLoader.getParent();
//        }

//        System.out.println(System.getProperty("sun.boot.class.path"));
//        System.out.println("------------------");
//        System.out.println(System.getProperty("java.class.path"));


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                MyClassLoader myClassLoader = new MyClassLoader();
//                try {
//                    System.out.println("main thread = " + Thread.currentThread());
//                    myClassLoader.loadClass("haha");
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        List<String> data = new ArrayList<>();
        data.add("23:58");
        data.add("00:03");
        data.add("00:04");
        int minDifference = new Solution().findMinDifference(data);
        System.out.println("min = " + minDifference);
    }

}
