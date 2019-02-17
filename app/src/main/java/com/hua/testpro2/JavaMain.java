package com.hua.testpro2;

import android.util.Log;

import java.util.Stack;

/**
 * Created by hua on 2019/2/15.
 */

public class JavaMain {

    public static void main(String[] args) {
        Suffix suffix = new Suffix("9+(3-1)*3+10/2");
        suffix.printSuffix();
        suffix.calculateSuffix();
        suffix.calculateSuffix2("4 2 * 2 3 * 5 + * 6 -");
    }


}
