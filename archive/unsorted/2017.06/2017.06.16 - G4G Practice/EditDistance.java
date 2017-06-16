package task;

import net.egork.chelper.util.InputReader;
import java.io.PrintWriter;

public class EditDistance {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        final char[] first = in.readToken().toCharArray();
        final char[] second = in.readToken().toCharArray();

        int[][] ed = new int[first.length + 1][second.length + 1];
        for (int i = 0 ; i < first.length; ++i) {
            ed[i][0] = i; // cancellazioni
        }
        for (int j = 0 ; j < second.length; ++j) {
            ed[0][j] = j; // cancellazioni
        }

        for (int i = 1; i <= first.length; i++) {
            for (int j = 1; j <= second.length; j++) {
                if (first[i - 1] == second[j - 1]) {
                    ed[i][j] = ed[i - 1][j - 1];
                } else {
                    ed[i][j] = 1 + Math.min(ed[i][j - 1], // insert first[i]
                                            Math.min(
                                            ed[i - 1][j], // delete first[i]
                                            ed[i - 1][j - 1] // replace
                    ));
                }
            }
        }

        out.println(ed[first.length][second.length]);
    }
}
