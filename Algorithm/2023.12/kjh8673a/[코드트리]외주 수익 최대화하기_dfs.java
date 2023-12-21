import java.io.*;
import java.util.*;

public class Main {
    static int n, answer;
    static int[][] task;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        task = new int[n + 1][2];
        StringTokenizer st;
        for(int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            task[i] = new int[] {t, p};
        }

        answer = 0;
        dfs(1, 0);

        System.out.println(answer);
    }

    private static void dfs(int day, int cost) {
        if(day == n + 1) {
            answer = Math.max(answer, cost);
            return;
        }else if(day > n + 1) {
            return;
        }

        dfs(day + 1, cost);
        dfs(day + task[day][0], cost + task[day][1]);
    }
}