package main;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;

public class CostCutting {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int[] salaries = new int[3];
        salaries[0] = in.nextInt();
        salaries[1] = in.nextInt();
        salaries[2] = in.nextInt();

        Arrays.sort(salaries);

        out.println("Case " + testNumber + ": " + salaries[1]);
    }
}
