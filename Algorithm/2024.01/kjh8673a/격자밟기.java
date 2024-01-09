import java.io.*;
import java.util.*;

public class Main {
    static int[][] vector = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        boolean[][] visited = new boolean[5][5];
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            visited[r - 1][c - 1] = true;
        }

        visited[0][0] = true;
        visited[4][4] = true;

        answer = 0;
        if ((25 - K) % 2 != 0) {
            dfs(0, 0, 4, 4, visited, 25 - (K + 2));
        }

        System.out.println(answer);
    }

    private static void dfs(int ar, int ac, int br, int bc, boolean[][] visited, int cnt) {
        if (ar == br && ac == bc) {
            if (cnt == 0) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nar = ar + vector[i][0];
            int nac = ac + vector[i][1];
            if (isInvalid(nar, nac) || visited[nar][nac]) {
                continue;
            }
            for (int j = 0; j < 4; j++) {
                int nbr = br + vector[j][0];
                int nbc = bc + vector[j][1];
                if (isInvalid(nbr, nbc) || visited[nbr][nbc]) {
                    continue;
                }

                visited[nar][nac] = true;
                visited[nbr][nbc] = true;
                int m = nar == nbr && nac == nbc ? 1 : 2;
                dfs(nar, nac, nbr, nbc, visited, cnt - m);
                visited[nar][nac] = false;
                visited[nbr][nbc] = false;
            }
        }
    }

    private static boolean isInvalid(int r, int c) {
        if (r < 0 || c < 0 || r >= 5 || c >= 5) {
            return true;
        }
        return false;
    }
}