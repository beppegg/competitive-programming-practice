package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class TheSnail573 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int h = in.nextInt();
        int u = in.nextInt();
        int d = in.nextInt();
        int f = in.nextInt();

        if ( h == 0 ) {
            throw new UnknownError();
        }

        double fatigueLoss = u * f / 100d;
        double distance = 0d;
        int day = 0;
        while (distance >= 0 && distance < h) {
            distance += u - Math.min(u, day++ * fatigueLoss);
            if (distance > h) {
                break;
            } else {
                distance -= d;
            }
        }
        out.printf("%s on day %d\n", (distance > 0) ? "success" : "failure", day );
    }
}
