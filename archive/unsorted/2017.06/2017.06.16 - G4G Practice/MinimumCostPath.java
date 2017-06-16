package task;

import net.egork.chelper.util.InputReader;
import java.io.PrintWriter;

public class MinimumCostPath {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        int m = in.readInt();

        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.readInt();
            }
        }

        int[][] mcp = new int[n][m];
        mcp[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            mcp[i][0] = mcp[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < m; i++) {
            mcp[0][i] = mcp[0][i - 1] + matrix[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                mcp[i][j] = matrix[i][j] + Math.min(mcp[i - 1][j], Math.min(mcp[i][j - 1], mcp[i - 1][j - 1]));
            }
        }

        out.println(mcp[n - 1][m - 1]);
    }
}
