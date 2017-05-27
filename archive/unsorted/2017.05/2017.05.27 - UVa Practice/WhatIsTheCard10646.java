package main;

import java.util.Scanner;
import java.io.PrintWriter;

public class WhatIsTheCard10646 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String[] deck = new String[52];
        for (int i = 0; i < 52; i++) {
            deck[i] = in.next();
        }

        int pileLength = 52 - 25;

        int y = 0;
        for (int i = 0; i < 3; i++) {
            int x = value(deck[pileLength - 1]);
            y += x;
            pileLength -= 1 + 10 - x;
        }

        int targetCardIndex = y >= pileLength ? y + 25 - pileLength : y;
        out.printf("Case %d: %s\n", testNumber, deck[targetCardIndex + 1]);
    }

    private int value(String s) {
        if (Character.isDigit(s.charAt(0))) {
            return s.charAt(0) - '0';
        } else {
            return 10;
        }
    }
}
