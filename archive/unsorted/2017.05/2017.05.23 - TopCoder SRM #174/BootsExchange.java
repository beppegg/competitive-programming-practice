package main;

import java.util.Arrays;

public class BootsExchange {
    public int leastAmount(int[] left, int[] right) {
        Arrays.sort(left);
        int leftLength = left.length;
        for (int i = 0; i < right.length; ++i) {
            final int pairIdx = Arrays.binarySearch(left, right[i]);
            if (pairIdx >= 0) {
                System.arraycopy(left, pairIdx + 1, left, pairIdx, --leftLength - pairIdx);
                left[leftLength] = Integer.MAX_VALUE;
            }
        }
        return leftLength;

    }
}
