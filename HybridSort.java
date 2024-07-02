import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HybridSort {

    public static void main(String[] args) {
        double[] transactions = {0.5, 0.2, 0.9, 0.1, 0.4, 0.7, 0.8, 0.3, 0.6};
        double[] sortedTransactions = hybridSort(transactions);

        for (double transaction : sortedTransactions) {
            System.out.print(transaction + " ");
        }
    }

    public static double[] hybridSort(double[] transactions) {
        // Step 1: Quicksort
        quicksort(transactions, 0, transactions.length - 1);

        // Step 2: Bucket Sort
        return bucketSort(transactions);
    }

    private static void quicksort(double[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quicksort(arr, low, pi - 1);
            quicksort(arr, pi + 1, high);
        }
    }

    private static int partition(double[] arr, int low, int high) {
        double pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private static double[] bucketSort(double[] arr) {
        int n = arr.length;
        if (n <= 0) return arr;

        @SuppressWarnings("unchecked")
        List<Double>[] buckets = new List[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        double max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) (arr[i] * n / (max + 1));
            buckets[bucketIndex].add(arr[i]);
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
            for (double num : buckets[i]) {
                arr[index++] = num;
            }
        }

        return arr;
    }
}