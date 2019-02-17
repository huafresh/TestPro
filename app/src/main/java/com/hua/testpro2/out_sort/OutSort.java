//package com.hua.testpro2.out_sort;
//
//import android.content.Context;
//
//import java.io.BufferedReader;
//import java.io.Closeable;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 外排序
// * Created by hua on 2019/2/16.
// */
//
//public class OutSort {
//
//    private static final String BAT_SEGMENT_PREFIX = "randomSegment";
//
//    /**
//     * 假设可用内存大小为500，其中输入缓存大小为400，输出缓存大小为100.
//     *
//     * @param source 待排序文件(10000个数据)
//     */
//    public static void outSortWithMerge(Context context, File source) {
//        Memory memory = new Memory();
//        List<File> segmentFiles = splitAndSortSegmentFile(context, source, memory);
//
//        SegmentMemory sm = new SegmentMemory(segmentFiles.size());
//
//        //从各个段文件中读取固定个数的数据参与归并
//        int num = -1;
//        for (int i = 0; i < segmentFiles.size(); i++) {
//            File file = segmentFiles.get(i);
//            num = readFromFile(sm, i, file);
//        }
//
//        //每段先贡献一个数据，构建败者树
//        int k = sm.segmentCount();
//        int[] b = new int[k];
//        for (int i = 0; i < k; i++) {
//            b[i] =
//        }
//
//
//        for (int i = 0; i < segmentArray.length; i++) {
//            MemorySegment memorySegment = segmentArray[i];
//            b[i] = memory[memorySegment.start = memorySegment.offset];
//        }
//        LoserTree loserTree = LoserTree.createLoserTree(b);
//
//        while () {
//            int topIndex = loserTree.topIndex();
//            MemorySegment segment = segmentArray[topIndex];
//
//            //取出来的段可能已经是空的了，则选择一个不为空的段
//            if (segment.finish) {
//                for (MemorySegment ms : segmentArray) {
//                    if (!ms.finish) {
//                        segment = ms;
//                        break;
//                    }
//                }
//            }
//
//            if (segment.finish) {
//                //所有段都结束了，
//                break;
//            }
//
//            //如果段数据已空，则尝试从文件读取
//            if (segment.start + segment.offset + 1 > segment.end) {
//                num = readFromFile(memory, segment.start, perSegmentSize, segment.file);
//                if (num == 0) {
//                    segment.finish = true;
//                    continue;
//                } else {
//                    segment.offset = 0;
//                    segment.end = segment.start + num - 1;
//                }
//            }
//
//            int newValue = memory[segment.start + (segment.offset++)];
//            int topValue = loserTree.update(newValue);
//            //如果输出缓冲区已满，则写入文件
//            if (outputOffset + 1 > outputLen) {
//                writeToResultFile(context, memory, outputIndex, outputOffset);
//                outputOffset = 0;
//            }
//            memory[outputIndex + outputOffset] = topValue;
//            outputOffset++;
//        }
//    }
//
//    private static void writeToResultFile(Context context, int[] memory, int offset, int size) {
//        File resultFile = new File(context.getExternalCacheDir(), "ordered.txt");
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(resultFile, true);
//            for (int i = 0; i < size; i++) {
//                writer.write(memory[offset + i]);
//                writer.write('\n');
//            }
//            writer.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            closeQuietly(writer);
//        }
//    }
//
//    /**
//     * 从file中读取数据到内存的index段，返回实际读取的数据个数。
//     */
//    private static int readFromFile(SegmentMemory memory, int index, File file) {
//        memory.invalidSegment(index);
//
//        FileInputStream fis = null;
//        BufferedReader reader = null;
//        int num = 0;
//        try {
//            fis = new FileInputStream(file);
//            reader = new BufferedReader(new InputStreamReader(fis));
//            String line = null;
//
//            while ((line = reader.readLine()) != null) {
//                int data = Integer.valueOf(line);
//                if (!memory.add(index, data)) {
//                    break;
//                }
//                num++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            closeQuietly(reader);
//        }
//        return num;
//    }
//
//
//    private static List<File> splitAndSortSegmentFile(Context context, File source, Memory memory) {
//        List<File> result = new ArrayList<>();
//        FileInputStream fis = null;
//        BufferedReader reader = null;
//        try {
//            fis = new FileInputStream(source);
//            reader = new BufferedReader(new InputStreamReader(fis));
//            String line = null;
//            int segmentNum = 0;
//            while ((line = reader.readLine()) != null) {
//                int data = Integer.valueOf(line);
//                if (!memory.add(data)) {
//                    //如果满了则排序后写入临时文件
//                    memory.sort();
//                    File batFile = new File(context.getExternalCacheDir(),
//                            BAT_SEGMENT_PREFIX + segmentNum + ".txt");
//                    result.add(batFile);
//                    PrintWriter writer = new PrintWriter(new FileOutputStream(batFile));
//                    try {
//                        for (int i = 0; i < memory.size(); i++) {
//                            writer.println(memory.get(i));
//                        }
//                    } finally {
//                        closeQuietly(writer);
//                    }
//                    segmentNum++;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            closeQuietly(reader);
//        }
//
//        return result;
//    }
//
//
//    private static void closeQuietly(Closeable closeable) {
//        if (closeable != null) {
//            try {
//                closeable.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 因为读取到内存的每段的数据并不是分数组存储的，而是都放在一个数组
//     * 因此使用此类记录每一段在内存中的位置。
//     */
//    static class MemorySegment {
//        private int id;
//        private int start;
//        private int end;
//        private int offset;
//        private File file;
//        private boolean finish = false;
//
//        MemorySegment(int id, int start, int end, File file) {
//            this.id = id;
//            this.start = start;
//            this.end = end;
//            this.offset = 0;
//            this.file = file;
//        }
//    }
//
//}
