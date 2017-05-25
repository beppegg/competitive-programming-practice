package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class DivisionOfNlogonia {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int k = in.nextInt(); // number of queries

        if (k == 0 && in.hasNext() == false) {
            throw new UnknownError();
        }
        int n = in.nextInt(); // division point coord
        int m = in.nextInt(); // division point other coord

        for (int q = 0; q < k; ++q) {
            int x = in.nextInt();
            int y = in.nextInt();

            if (x == n || y == m) {
                out.println("divisa");
            } else {
                char[] coords = new char[2];
                if (y > m) {
                    coords[0] = 'N';
                } else {
                    coords[0] = 'S';
                }

                if (x > n) {
                    coords[1] = 'E';
                } else {
                    coords[1] = 'O';
                }

                out.println(coords);
            }
        }
    }
}
