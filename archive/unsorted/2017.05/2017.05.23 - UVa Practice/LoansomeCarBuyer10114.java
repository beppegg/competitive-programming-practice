package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class LoansomeCarBuyer10114 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int loanDuration = in.nextInt();

        if (loanDuration < 0) {
            throw new UnknownError();
        }

        double downPayment = in.nextDouble(); // anticipo!
        double loanAmount = in.nextDouble();
        int depNum = in.nextInt();
        int[] depreciationsMonths = new int[depNum];
        double[] depreciationAmount = new double[depNum];
        for (int i = 0; i < depNum; i++) {
            depreciationsMonths[i] = in.nextInt();
            depreciationAmount[i] = in.nextDouble();
        }

        double dueAmount = loanAmount;
        double installment = dueAmount / loanDuration;
        double value = (loanAmount + downPayment) * (1 - depreciationAmount[0]);
        double epsilon = 10E-2;
        int depreciationIndex = 0;
        if (value - dueAmount > epsilon) {
            out.println("0 months");
        } else {
            for (int month = 1; month <= loanDuration; ++month) {
                while (depreciationIndex < depNum - 1 && depreciationsMonths[depreciationIndex + 1] <= month) {
                    depreciationIndex++;
                }
                dueAmount -= installment;
                value *= 1 - depreciationAmount[depreciationIndex];

                if (value - dueAmount > epsilon) {
                    out.println(month + " month" + (month > 1 ? "s" : ""));
                    break;
                }
            }
        }
    }
}
