package com.hua.testpro2.out_sort;

import java.io.File;

/**
 * 假设可用内存大小为500，其中输入缓存大小为400，输出缓存大小为100.
 * 这里数字的单位是[个int]
 * Created by hua on 2019/2/17.
 */

public class Memory {

    static final int MAX_SIZE = 500;
    protected int[] data = new int[MAX_SIZE];
    private int offset = -1;

    public void set(int index, int value) {
        if (index < 0 || index > MAX_SIZE - 1) {
            throw new ArrayIndexOutOfBoundsException("index too large");
        }
        data[index] = value;
    }

    public boolean add(int value) {
        if (offset + 1 < MAX_SIZE) {
            data[++offset] = value;
            return true;
        }
        return false;
    }

    public int get(int index) {
        if (index < 0 || index > MAX_SIZE - 1) {
            throw new ArrayIndexOutOfBoundsException("index too large");
        }
        return data[index];
    }

    public void clear() {
        this.offset = -1;
    }

    public int size() {
        return offset != -1 ? offset : 0;
    }

    public void sort() {
        //简单起见，就采用冒泡排序
        int len = data.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (data[i] > data[j]) {
                    int tmp = data[i];
                    data[i] = data[j];
                    data[j] = tmp;
                }
            }
        }
    }


}
