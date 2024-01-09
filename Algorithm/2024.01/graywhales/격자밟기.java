import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
백트래킹
*/
public class Main {
    static boolean[][] visit = new boolean[5][5];
    static int k;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            visit[r][c] = true;
        }
        visit[0][0] = true;
        visit[4][4] = true;
        if (k % 2 == 0 && k <= 16) {
            dfs(0, 0, 4, 4, 0, true);

        }
        System.out.println(ans);
    }

    private static void dfs(int r1, int c1, int r2, int c2, int depth, boolean turn) {
        if (turn) {
            for (int i = 0; i < 4; i++) {
                int nr = r1 + dr[i];
                int nc = c1 + dc[i];
                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
                if (visit[nr][nc]) continue;
                visit[nr][nc] = true;
                dfs(nr, nc, r2, c2, depth, false);
                visit[nr][nc] = false;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                int nr = r2 + dr[i];
                int nc = c2 + dc[i];
                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;
                if (depth == (22 - k) / 2 && nr == r1 && nc == c1) {
                    ans++;
                }
                if (visit[nr][nc]) continue;
                visit[nr][nc] = true;
                dfs(r1, c1, nr, nc, depth + 1, true);
                visit[nr][nc] = false;
            }
        }

    }
}
