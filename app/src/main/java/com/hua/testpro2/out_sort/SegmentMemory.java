package com.hua.testpro2.out_sort;

import java.util.List;

/**
 * 因为读取到内存的每段的数据并不是存储在不同的数组的，而是都放在一个数组里。
 * 因此使用此类记录每一段在内存中的位置信息。
 * Created by hua on 2019/2/17.
 */

public class SegmentMemory extends Memory {
    private Segment[] segments;

    //输出缓冲区起始位置等信息
    private int outputStart;
    private int outputLen;
    private int outputOffset;

    public SegmentMemory(int segmentCount) {
        int segmentPerSize = MAX_SIZE / (segmentCount + 1);
        this.segments = new Segment[segmentCount];
        for (int i = 0; i < segmentCount; i++) {
            segments[i] = new Segment(i, i * segmentPerSize, segmentPerSize);
        }
        this.outputStart = segmentPerSize * segmentCount;
        this.outputLen = MAX_SIZE - outputStart;
        this.outputOffset = 0;
    }

    public boolean add(int index, int value) {
        Segment segment = segments[index];
        if (segment.hasSpace()) {
            set(segment.nextIndex(), value);
            return true;
        }

        return false;
    }

    public void invalidSegment(int index) {
        Segment segment = segments[index];
        segment.invalid();
    }

    public int segmentCount(){
        return segments.length;
    }

    /**
     * 段信息
     */
    static class Segment {
        private int segmentId;
        private int start;
        private int segmentSize;
        //段内有效数据偏移
        private int offset;

        //段内有效数据的偏移值
        private int offset1;
        private int offset2;

        Segment(int segmentId, int start, int segmentSize) {
            this.segmentId = segmentId;
            this.start = start;
            this.segmentSize = segmentSize;
            this.offset = -1;
        }

        boolean valid() {
            return offset > 0;
        }

        void invalid(){
            offset = -1;
        }

        boolean hasSpace() {
            return offset + 1 < segmentSize;
        }

        int nextIndex() {
            return start + offset + 1;
        }

    }

}
