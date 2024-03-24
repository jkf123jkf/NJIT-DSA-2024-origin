package oy.tol.tra;

public class Algorithms {

    public static <T extends Comparable<T>> int binarySearch(T target, T[] array, int left, int right) {
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle].compareTo(target) > 0) {
                right = middle - 1;
            } else if (array[middle].compareTo(target) < 0) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void fastSort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int begin, int end) {
        T pivot = array[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        T temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1;
    }
}
