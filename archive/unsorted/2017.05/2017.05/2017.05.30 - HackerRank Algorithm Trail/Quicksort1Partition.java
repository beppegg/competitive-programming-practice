package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class Quicksort1Partition {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int pivot = arr[0];
        int[] lesser = new int[n];
        int[] eq = new int[n];
        int[] greater = new int[n];
        int lIdx = 0, eIdx = 0, gIdx = 0;
        for (int i = 0; i < n; ++i) {
            if (arr[i] > pivot) {
                greater[gIdx++] = arr[i];
            } else if (arr[i] == pivot) {
                eq[eIdx++] = arr[i];
            } else {
                lesser[lIdx++] = arr[i];
            }
        }
        printArr(lesser, lIdx, out, "");
        printArr(eq, eIdx, out, " ");
        printArr(greater, gIdx, out, " ");
        out.println();
    }

    private void printArr(int[] arr, int length, PrintWriter out, String sep) {
        for (int i = 0; i < length; i++) {
            out.print(sep + arr[i]);
            sep = " ";
        }
    }
}
