package com.hua.testpro2.out_sort;

/**
 * 败者树
 * Created by hua on 2019/2/17.
 */

public class LoserTree {

    private Record[] ls;

    private LoserTree(Record[] ls) {
        this.ls = ls;
    }

    public static LoserTree createLoserTree(int[] b) {
        int len = b.length;
        Record[] ls = new Record[len];
        for (Record record : ls) {
            record.fromIndex = -1;
            record.value = -1;
        }

        int victorIndex = getVictorIndex(b, 0, len / 2, ls, 1);
        ls[0] = new Record(b[victorIndex], victorIndex);
        return new LoserTree(ls);
    }

    /**
     * 返回当前最小值在b数组中的索引
     */
    public int topIndex() {
        return ls[0].fromIndex;
    }

    /**
     * 更新一个新值，返回原来的最小值
     */
    public int update(int value) {
        int topIndex = topIndex();
        int topValue = ls[0].value;
        int parentIndex = (ls.length + topIndex) / 2;
        while (parentIndex >= 0) {
            Record parent = ls[parentIndex];
            if (value > parent.value) {
                int tmp = parent.value;
                parent.value = value;
                value = tmp;
            }
            parentIndex /= 2;
        }

        return topValue;
    }

    private static int getVictorIndex(int[] b, int bLeft, int bRight,
                                      Record[] ls, int lsIndex) {
        int victorIndex, loserIndex;
        if (bRight - bLeft == 1) {
            victorIndex = b[bLeft] < b[bRight] ? bLeft : bRight;
            loserIndex = b[bLeft] < b[bRight] ? bRight : bLeft;
            ls[lsIndex] = new Record(b[loserIndex], loserIndex);
            return victorIndex;
        }

        int leftVictorIndex = getVictorIndex(b, bLeft, (bLeft + bRight) / 2, ls, lsIndex + 1);
        int rightVictorIndex = getVictorIndex(b, bLeft, (bLeft + bRight) / 2, ls, lsIndex + 2);

        int leftVictor = b[leftVictorIndex];
        int rightVictor = b[rightVictorIndex];

        victorIndex = leftVictor < rightVictor ? leftVictorIndex : rightVictorIndex;
        loserIndex = leftVictor < rightVictor ? rightVictorIndex : leftVictorIndex;
        ls[lsIndex] = new Record(b[loserIndex], loserIndex);

        return victorIndex;
    }

    static class Record {
        private int value;
        private int fromIndex;

        Record(int value, int fromIndex) {
            this.value = value;
            this.fromIndex = fromIndex;
        }
    }

}
