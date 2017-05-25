package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class EventPlanning11559 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        if (!in.hasNext()) {
            throw new UnknownError();
        }
        int partecipants = in.nextInt();
        int budget = in.nextInt();
        int hotelsNumber = in.nextInt();
        int weeksNumber = in.nextInt();

        int minWage = Integer.MAX_VALUE;
        for (int h = 0; h < hotelsNumber; ++h) {
            int hotelPrice = in.nextInt() * partecipants;
            if (hotelPrice > budget) {
                in.nextLine();in.nextLine();
            } else {
                for (int w = 0; w < weeksNumber; ++w) {
                    int beds = in.nextInt();
                    if (beds >= partecipants && hotelPrice < minWage) {
                        minWage = hotelPrice;
                    }
                }
            }
        }

        if (minWage <= budget) {
            out.println(minWage);
        } else {
            out.println("stay home");
        }
    }
}
