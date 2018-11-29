import java.lang.management.ThreadMXBean;
import java.lang.management.ManagementFactory;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) throws Exception {

        System.out.println("Part 1:\n");
        part1();

        System.out.println("\n\nPart 2:\n");
        part2();

        System.out.println("\nBonus:");
        bonus();
    }

    private static void part1() {

        int[] array = {23, 17, 39, 7, 12, 68, 14, 46, 21, 123};
        Heap heap = new Heap();

        for (int value : array) {
            heap.heapInsert(value);
        }

        heap.printHeap();
    }

    private static void part2() throws Exception {

        long[] cpuTime = new long[10];
        for (int i = 0; i < 10; i++) {

            int[] unsorted = getRandomArray((i + 1) * 10000);
            int[] sorted;

            ThreadMXBean thread = ManagementFactory.getThreadMXBean();
            long cpuStart = thread.getCurrentThreadCpuTime();

            for (int j = 0; j < 32; j++) {
                sorted = Sorting.heapSort(unsorted);
            }

            cpuTime[i] = (thread.getCurrentThreadCpuTime() - cpuStart) / 32;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("array size: " + ((i + 1) * 10000) + ", cpu time:" + cpuTime[i] + " ns");
        }
    }

    public static void bonus() throws Exception {

        // recursive sort
        long[] cpuTime = new long[30];
        for (int i = 0; i < 10; i++) {

            int[] unsorted = getRandomArray((i + 1) * 1000);
            int[] sorted;

            ThreadMXBean thread = ManagementFactory.getThreadMXBean();
            long cpuStart = thread.getCurrentThreadCpuTime();

            for (int j = 0; j < 16; j++) {
                sorted = Sorting.recursiveSort(unsorted);
            }

            cpuTime[i] = (thread.getCurrentThreadCpuTime() - cpuStart) / 16;
        }

        System.out.println("\nRecursive Sort:");
        for (int i = 0; i < 10; i++) {
            System.out.println("array size: " + ((i + 1) * 1000) + ", cpu time:" + cpuTime[i] + " ns");
        }

        // merge sort
        for (int i = 0; i < 10; i++) {

            int[] unsorted = getRandomArray((i + 1) * 1000);
            int[] sorted;

            ThreadMXBean thread = ManagementFactory.getThreadMXBean();
            long cpuStart = thread.getCurrentThreadCpuTime();

            for (int j = 0; j < 256; j++) {
                sorted = Sorting.mergeSort(unsorted);
            }

            cpuTime[i] = (thread.getCurrentThreadCpuTime() - cpuStart) / 256;
        }

        System.out.println("\nMerge Sort:");
        for (int i = 0; i < 10; i++) {
            System.out.println("array size: " + ((i + 1) * 1000) + ", cpu time:" + cpuTime[i] + " ns");
        }

        // heap sort
        for (int i = 0; i < 10; i++) {

            int[] unsorted = getRandomArray((i + 1) * 1000);
            int[] sorted;

            ThreadMXBean thread = ManagementFactory.getThreadMXBean();
            long cpuStart = thread.getCurrentThreadCpuTime();

            for (int j = 0; j < 128; j++) {
                sorted = Sorting.heapSort(unsorted);
            }

            cpuTime[i] = (thread.getCurrentThreadCpuTime() - cpuStart) / 128;
        }

        System.out.println("\nHeap Sort:");
        for (int i = 0; i < 10; i++) {
            System.out.println("array size: " + ((i + 1) * 1000) + ", cpu time:" + cpuTime[i] + " ns");
        }
    }

    private static int[] getRandomArray(int size) {

        Random rand = new Random();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt();
        }

        return array;
    }
}