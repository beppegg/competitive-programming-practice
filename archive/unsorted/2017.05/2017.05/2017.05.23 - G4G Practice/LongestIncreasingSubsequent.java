package main;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSubsequent {
    public void solve(int testNumber, Scanner input, PrintWriter out) {
        int cases = input.nextInt();
        for (int c = 0; c < cases; ++c) {
            int arrayLength = input.nextInt();
            int[] numbers = new int[arrayLength];
            for (int i = 0; i < arrayLength; ++i) {
                numbers[i] = input.nextInt();
            }

            int[] lookup = new int[arrayLength];
            Arrays.fill(lookup, -1);
            lookup[0] = 1;
            int max = 1;
            for (int i = 0; i < arrayLength; ++i) {
                max = Math.max(max, solve(numbers, i, lookup));
            }
            out.println(max);
        }
    }

    public static int solve(int[] numbers, int pos, int[] lookup) {
        if (lookup[pos] < 0) {
            int max = 1;
            for (int i = 0; i < pos; ++i) {
                if (numbers[pos] > numbers[i]) {
                    max = Math.max(max, solve(numbers, i, lookup) + 1);
                }
            }

            lookup[pos] = max;
        }
        return lookup[pos];
    }
}
