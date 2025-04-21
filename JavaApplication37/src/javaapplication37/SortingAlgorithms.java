/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication37;

/**
 *
 * @author ADMIN
 */
import java.util.Random;

public class SortingAlgorithms {

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Merge Sort
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Radix Sort
    public static void radixSort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void countSort(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];

        for (int i = 0; i < arr.length; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    // Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // Hàm hiển thị mảng
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Random rand = new Random();

        // Nhập số lượng phần tử
        System.out.print("Nhập số lượng phần tử: ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int n = scanner.nextInt();

        // Tạo mảng ngẫu nhiên với n phần tử
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(1000) + 1;
        }

        // Bubble Sort
        int[] arrBubble = arr.clone();
        long startTime = System.nanoTime();
        bubbleSort(arrBubble);
        long endTime = System.nanoTime();
        System.out.println("Bubble Sort:");
//        printArray(arrBubble);
        System.out.println("Thời gian: " + (endTime - startTime) / 1e6 + " ms");

        // Insertion Sort
        int[] arrInsertion = arr.clone();
        startTime = System.nanoTime();
        insertionSort(arrInsertion);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort:");
//        printArray(arrInsertion);
        System.out.println("Thời gian: " + (endTime - startTime) / 1e6 + " ms");

        // Selection Sort
        int[] arrSelection = arr.clone();
        startTime = System.nanoTime();
        selectionSort(arrSelection);
        endTime = System.nanoTime();
        System.out.println("Selection Sort:");
//        printArray(arrSelection);
        System.out.println("Thời gian: " + (endTime - startTime) / 1e6 + " ms");

        // Quick Sort
        int[] arrQuick = arr.clone();
        startTime = System.nanoTime();
        quickSort(arrQuick, 0, arrQuick.length - 1);
        endTime = System.nanoTime();
        System.out.println("Quick Sort:");
//        printArray(arrQuick);
        System.out.println("Thời gian: " + (endTime - startTime) / 1e6 + " ms");

        // Merge Sort
        int[] arrMerge = arr.clone();
        startTime = System.nanoTime();
        mergeSort(arrMerge);
        endTime = System.nanoTime();
        System.out.println("Merge Sort:");
//        printArray(arrMerge);
        System.out.println("Thời gian: " + (endTime - startTime) / 1e6 + " ms");

        // Radix Sort
        int[] arrRadix = arr.clone();
        startTime = System.nanoTime();
        radixSort(arrRadix);
        endTime = System.nanoTime();
        System.out.println("Radix Sort:");
//        printArray(arrRadix);
        System.out.println("Thời gian: " + (endTime - startTime) / 1e6 + " ms");

        // Heap Sort
        int[] arrHeap = arr.clone();
        startTime = System.nanoTime();
        heapSort(arrHeap);
        endTime = System.nanoTime();
        System.out.println("Heap Sort:");
//        printArray(arrHeap);
        System.out.println("Thời gian: " + (endTime - startTime) / 1e6 + " ms");
    }
}

