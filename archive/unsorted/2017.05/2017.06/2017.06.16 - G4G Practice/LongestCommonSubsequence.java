package main;

import java.io.PrintWriter;
import java.util.Scanner;

// net.egork.chelper.util.InputReader
public class LongestCommonSubsequence {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        final char[] first = in.nextLine().toCharArray();
        final char[] second = in.nextLine().toCharArray();

        final int[][] lcs = new int[first.length + 1][second.length + 1];

        for (int i = 0; i <= first.length; ++i) {
            for (int j = 0; j <= second.length; j++) {
                if (i == 0 || j == 0) {
                    lcs[i][j] = 0;
                } else if (first[i - 1] == second[j - 1]) {
                    lcs[i][j] = 1 + lcs[i - 1][j - 1];
                } else {
                    lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j]);
                }
            }
        }

        out.println(lcs[first.length][second.length]);
    }
}
