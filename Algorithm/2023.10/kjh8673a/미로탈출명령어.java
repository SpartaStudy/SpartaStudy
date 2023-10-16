import java.util.*;

class Solution {
    static int n, m, r, c, k;
    static String answer;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;

        answer = "impossible";
        dfs(x, y, "");

        return answer;
    }

    private static void dfs(int i, int j, String tmp) {
        if (isNotPossible(i, j, tmp.length())) {
            return;
        }

        if (tmp.length() == k) {
            if (i == r && j == c && answer.equals("impossible")) {
                answer = tmp;
            }
            return;
        }

        // 사전순으로 dfs탐색 -> 제일 먼저 나온 정답이 사전순으로 가장 빠르다
        dfs(i + 1, j, tmp + "d");
        dfs(i, j - 1, tmp + "l");
        dfs(i, j + 1, tmp + "r");
        dfs(i - 1, j, tmp + "u");
    }

    private static boolean isNotPossible(int i, int j, int cnt) {
        // 1. 이미 정답이 등록되어있다면 더이상 안해도 된다
        if (!answer.equals("impossible")) {
            return true;
        }

        // 2. 범위에서 벗어남
        if (i <= 0 || j <= 0 || i > n || j > m) {
            return true;
        }

        // 3. 남은 거리가 남은 이동 횟수보다 크면 불가능
        // 4. 남은 거리와 남은 이동 횟수의 홀 짝이 다르면 도착 못함
        int dist = Math.abs(r - i) + Math.abs(c - j);
        if (k - cnt < dist) {
            return true;
        }
        if ((k - cnt - dist) % 2 == 1) {
            return true;
        }

        return false;
    }
}