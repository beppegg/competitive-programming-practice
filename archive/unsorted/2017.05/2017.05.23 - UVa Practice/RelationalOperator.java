package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class RelationalOperator {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int first = in.nextInt();
        int second = in.nextInt();

        out.println(first < second
                ? "<"
                : (first == second
                    ? "="
                    : ">"));
    }
}
