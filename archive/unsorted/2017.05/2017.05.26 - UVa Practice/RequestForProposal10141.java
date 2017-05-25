package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class RequestForProposal10141 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int p = in.nextInt();

        if (0 == n && 0 == p) {
            throw new UnknownError();
        }

        if (testNumber > 1) {
            out.println();
        }

        in.nextLine();
        for (int i = 0; i < n; i++) {
            in.nextLine();
        }

        double maxPrice = Double.MAX_VALUE;
        double maxCompliance = 0;
        String maxName = "";
        double eps = 10E-9;
        for (int i = 0; i < p; i++) {
            String name = in.nextLine();
            double price = in.nextDouble();
            int reqMet = in.nextInt();

            double compl = n > 0? ((double) reqMet) / n : 1d;
            if (compl > maxCompliance
                    || (Math.abs(compl - maxCompliance) < eps && price < maxPrice)) {
                maxCompliance = compl;
                maxPrice = price;
                maxName = name;
            }

            in.nextLine();
            for (int j = 0; j < reqMet; j++) {
                in.nextLine();
            }
        }
        out.printf("RFP #%d\n%s\n", testNumber, maxName);
    }
}
