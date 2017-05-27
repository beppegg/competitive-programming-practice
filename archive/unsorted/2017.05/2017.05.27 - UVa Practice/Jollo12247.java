package main;

import java.util.Comparator;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.function.Predicate;

public class Jollo12247 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        byte[] princess = new byte[3];
        byte[] prince = new byte[2];

        for (int i = 0; i < 3; i++) {
            princess[i] = in.nextByte();
            if (0 == princess[i]) {
                throw new UnknownError();
            }
        }

        for (int i = 0; i < 2; i++) {
            prince[i] = in.nextByte();
        }

        sort(prince, (b1, b2) -> b1 - b2);
        sort(princess, (b1, b2) -> b2 - b1);

        byte beatPrinceCardIndex = -1;
        byte beatenCount = 0;
        for (byte i = 0; i < 2; ++i) {
            for (byte j = 0; j < 3; j++) {
                if (beatPrinceCardIndex < 0 || beatPrinceCardIndex != j) {
                    if (prince[i] < princess[j]) {
                        beatenCount++;
                        beatPrinceCardIndex = j;
                        break;
                    }
                }
            }
        }

        if (beatenCount > 1) {
            out.println("-1");
        } else {
            byte minWin = beatenCount == 0? 1 : princess[(byte) (beatPrinceCardIndex == 2 ? 1 : 2)];
            while (isContained(minWin, prince) || isContained(minWin, princess)) {
                ++minWin;
            }
            out.println(minWin <= 52 ? minWin : -1);
        }
    }

    private static void sort(byte[] array, Comparator<Byte> order) {
        if (order.compare(array[0], array[1]) < 0) {
            swap(array, 0, 1);
        }
        if (array.length > 2) {
            if (order.compare(array[1], array[2]) < 0) {
                swap(array, 1, 2);
            }
            if (order.compare(array[0], array[1]) < 0) {
                swap(array, 0, 1);
            }
        }
    }

    private static void swap(byte[] prince, int i, int j) {
        byte temp = prince[i];
        prince[i] = prince[j];
        prince[j] = temp;
    }

    private static boolean isContained(byte target, byte[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return true;
            }
        }
        return false;
    }


}
