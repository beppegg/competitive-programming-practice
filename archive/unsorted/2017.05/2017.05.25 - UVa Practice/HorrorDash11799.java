package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class HorrorDash11799 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();

        int m = -1;
        for (int i = 0; i < n; ++i) {
            int c = in.nextInt();
            if (c > m) {
                m = c;
            }
        }
        out.printf("Case %d: %s\n", testNumber, m);
     }
}
