package com.hua.test_java;

import java.util.List;

/**
 * Created by hua on 2019/3/3.
 */

class Solution {
    private static final int MAX_MINUTE = 24 * 60;

    public int findMinDifference(List<String> timePoints) {
        int size = timePoints.size();
        //先把字符串转分钟数
        int[] minuteArray = new int[size];
        for (int i = 0; i < size; i++) {
            String timePoint = timePoints.get(i);
            String[] split = timePoint.split(":");
            minuteArray[i] = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
        }
        //快排序
        sortArrayWithQuick(minuteArray, 0, size - 1);

        //遍历每一个分钟数，计算与左右两边的差值
        int smallestMinute = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            int minute = minuteArray[i];
            int left = 0;
            if (i == 0) {
                left = MAX_MINUTE - minuteArray[size - 1];
            } else {
                left = minuteArray[i] - minuteArray[i - 1];
            }

            int right = 0;
            if (i == size - 1) {
                right = MAX_MINUTE - minuteArray[i];
            } else {
                right = minuteArray[i + 1] - minuteArray[i];
            }

            smallestMinute = Math.min(Math.min(left, right), smallestMinute);
        }

        return smallestMinute;
    }

    private static void sortArrayWithQuick(int[] array, int start, int end) {
        if (start == end) {
            return;
        }
        int basePos = (start + end) / 2;
        part(array, start, end, basePos);
        sortArrayWithQuick(array, start, basePos);
        sortArrayWithQuick(array, basePos + 1, end);
    }

    private static void part(int[] array,
                             int start,
                             int end,
                             int basePos) {
        int base = array[basePos];
        array[basePos] = array[end];
        int i = start;
        int j = end;
        while (true) {
            while (i <= basePos) {
                if (array[i] > base) {
                    array[j] = array[i];
                    break;
                }
                i++;
            }

            while (j >= basePos) {
                if (array[j] <= base) {
                    array[i] = array[j];
                    break;
                }
                j--;
            }

            if (i == j) {
                array[i] = base;
                break;
            }
        }
    }
}
