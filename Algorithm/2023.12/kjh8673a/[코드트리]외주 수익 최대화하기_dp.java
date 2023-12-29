import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] task = new int[n + 1][2];
        StringTokenizer st;
        for(int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            task[i] = new int[] {t, p};
        }

        int[] dp = new int[n + 2];
        for(int i = n; i > 0; i--) {
            if(i + task[i][0] > n + 1) {
                dp[i] = dp[i + 1];
            }else {
                dp[i] = Math.max(dp[i + 1], task[i][1] + dp[i + task[i][0]]);
            }
        }

        System.out.println(dp[1]);
    }
}