package com.hua.testpro2.out_sort;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by hua on 2019/2/16.
 */

public class RandomFileCreator {

    public static File createRandomFile(Context context) {

        Random random = new Random();
        File file = new File(context.getExternalCacheDir(), "random.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            for (int i = 0; i < 10000; i++) {
                int randomInt = random.nextInt(10000);
                pw.println(randomInt);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return file;
    }

}
