package oy.tol.tra;

import java.util.Comparator;
import java.util.function.Predicate;

public class Algorithms {

    public static <T> void reverse(T[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            T temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static <T> int slowLinearSearch(T targetValue, T[] sourceArray, int fromIndex, int toIndex) {
        for (int index = fromIndex; index < toIndex; index++) {
            if (sourceArray[index].equals(targetValue)) {
                return index;
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearch(T targetValue, T[] sourceArray, int fromIndex, int toIndex) {
        while (fromIndex <= toIndex) {
            int midIndex = fromIndex + (toIndex - fromIndex) / 2;
            int compareResult = sourceArray[midIndex].compareTo(targetValue);
            if (compareResult == 0) {
                return midIndex;
            } else if (compareResult < 0) {
                fromIndex = midIndex + 1;
            } else {
                toIndex = midIndex - 1;
            }
        }
        return -1;
    }


    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int beginIndex, int endIndex) {
        if (beginIndex >= endIndex) {
            return;
        }
        int partitionIndex = partition(array, beginIndex, endIndex);
        quickSort(array, beginIndex, partitionIndex - 1);
        quickSort(array, partitionIndex + 1, endIndex);
    }

    private static <E extends Comparable<E>> int partition(E[] array, int beginIndex, int endIndex) {
        E pivot = array[endIndex];
        int leftPointer = beginIndex;
        int rightPointer = endIndex;
        while (leftPointer < rightPointer) {
            while (leftPointer < rightPointer && array[leftPointer].compareTo(pivot) < 0) {
                leftPointer++;
            }
            while (leftPointer < rightPointer && array[rightPointer].compareTo(pivot) > 0) {
                rightPointer--;
            }
            swap(array, leftPointer, rightPointer);
        }
        return leftPointer;
    }

    private static <E> void swap(E[] array, int index1, int index2) {
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static <T> int partitionByRule(T[] array, int count, Predicate<T> rule) {
        int selectedIndex = 0;
        for (; selectedIndex < count; selectedIndex++) {
            if (rule.test(array[selectedIndex])) {
                break;
            }
        }
        if (selectedIndex >= count) {
            return count;
        }
        int nextIndex = selectedIndex + 1;
        while (nextIndex != count) {
            if (!rule.test(array[nextIndex])) {
                swap(array, selectedIndex, nextIndex);
                selectedIndex++;
            }
            nextIndex++;
        }
        return selectedIndex;
    }

    public static <T> void sortWithComparator(T[] array, Comparator<T> comparator) {
        boolean swapped;
        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }




}