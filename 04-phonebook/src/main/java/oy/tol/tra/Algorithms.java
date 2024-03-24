package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms {
    public static <T extends Comparable<T>> void sort(T[] array) {
        int i = array.length - 1;
        while (i > 0) {
            int j = i;
            while (j > 0) {
                int k = 0;
                while (k <= j - 1) {
                    if (array[k].compareTo(array[k + 1]) > 0) {
                        T tmp = array[k];
                        array[k] = array[k + 1];
                        array[k + 1] = tmp;
                    }
                    k++;
                }
                j--;
            }
            i--;
        }
    }

    public static <T> void swap(T[] array, int x, int y) {
        T tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }

    public static <T> void reverse(T[] array) {
        int i = 0;
        while (i < array.length / 2) {
            T temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
            i++;
        }
    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
        int l = fromIndex - 1;
        int r = toIndex + 1;
        while (l + 1 != r) {
            int mid = (l + r) / 2;
            if (fromArray[mid].compareTo(aValue) >= 0) {
                r = mid;
            } else {
                l = mid;
            }
        }
        if (r >= toIndex + 1) {
            return -1;
        }
        if (!fromArray[r].equals(aValue)) {
            return -1;
        }
        return r;
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        E x = array[end];
        int i = begin - 1;
        int j = begin;
        while (j <= end - 1) {
            if (array[j].compareTo(x) <= 0) {
                i = i + 1;
                E tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
            j++;
        }
        E tmp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = tmp;
        return i + 1;
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        while (begin < end) {
            int q = partition(array, begin, end);
            quickSort(array, begin, q - 1);
            begin = q + 1;
        }
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <T> int partitionByRule(T[] array, int count, Predicate<T> rule) {
        int index = 0;
        while (index < count) {
            if (rule.test(array[index])) {
                break;
            }
            index++;
        }

        if (index >= count) {
            return count;
        }

        int nextIndex = index + 1;

        while (nextIndex != count) {
            if (!rule.test(array[nextIndex])) {
                swap(array, index, nextIndex);
                index++;
            }
            nextIndex++;
        }
        return index;
    }
}