import java.util.Arrays;

public class Sorting {
    
    public static int[] recursiveSort(int[] unsorted) {

        int length = unsorted.length;
        int[] sorted = new int[length];

        if (length == 1) {
            sorted = unsorted;
        } else {
            // recursive part
            int[] subArray = Arrays.copyOfRange(unsorted, 0, length - 1);
            subArray = recursiveSort(subArray);
            System.arraycopy(subArray, 0, sorted, 0, length - 1);
            sorted[length - 1] = unsorted[length - 1];
            // sorting part
            for (int i = length - 1; i > 0; i--) {
                int current = sorted[i];
                int next = sorted[i - 1];
                if (current < next) {
                    sorted[i] = next;
                    sorted[i - 1] = current;
                } else {
                    break;
                }
            }
        }

        return sorted;
    }

    public static int[] mergeSort(int[] unsorted) {

        int length = unsorted.length;
        int[] sorted = new int[length];

        if (length == 1) {
            sorted = unsorted;
        } else {
            int half = length / 2;
            int[] first = Arrays.copyOfRange(unsorted, 0, half);
            int[] second = Arrays.copyOfRange(unsorted, half, length);
            first = mergeSort(first);
            second = mergeSort(second);
            sorted = merge(first, second);
        }

        return sorted;
    }

    public static int[] heapSort(int[] unsorted) throws Exception {

        int[] sorted = new int[unsorted.length];
        Heap heap = new Heap();

        for (int value : unsorted) {
            heap.heapInsert(value);
        }

        for (int i = 0; i < unsorted.length; i++) {
            sorted[i] = heap.heapRemove();
        }

        return sorted;
    }

    private static int[] merge(int[] first, int[] second) {

        int length = first.length + second.length;
        int[] merged = new int[length];
        int firstIndex = 0;
        int secondIndex = 0;

        for (int i = 0; i < length; i++) {
            if (firstIndex >= first.length) {
                merged[i] = second[secondIndex++];
            } else if (secondIndex >= second.length) {
                merged[i] = first[firstIndex++];
            } else if (first[firstIndex] <= second[secondIndex]) {
                merged[i] = first[firstIndex++];
            } else {
                merged[i] = second[secondIndex++];
            }
        }

        return merged;
    }

}