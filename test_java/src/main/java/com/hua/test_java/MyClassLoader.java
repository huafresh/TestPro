package com.hua.test_java;

/**
 * Created by hua on 2019/3/9.
 */

public class MyClassLoader extends ClassLoader {
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public MyClassLoader() {
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("myclassloader thread = " + Thread.currentThread());
        return super.findClass(name);
    }
}
