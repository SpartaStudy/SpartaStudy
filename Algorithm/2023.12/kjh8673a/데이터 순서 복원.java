import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[3][N];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] answer = new int[N];
        boolean[] visited = new boolean[N + 1];
        int idx = N - 1;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                int num = arr[j][i];
                if (visited[num]) {
                    continue;
                }
                if (!queue.contains(num)) {
                    queue.add(num);
                } else {
                    answer[idx--] = num;
                    visited[num] = true;
                }
            }
            while (!queue.isEmpty()) {
                int num = queue.poll();
                if (!visited[num]) {
                    answer[idx--] = num;
                    visited[num] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i] + " ");
        }

        System.out.println(sb);
    }

}