import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int from;
        int cnt;

        public Node(int from, int cnt) {
            this.from = from;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for (int i = 1; i <= N + 1; i++) {
            list.add(new ArrayList<>());
        }

        int[] in_degree = new int[N + 1];
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            list.get(X).add(new Node(Y, K));
            in_degree[Y]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] need = new int[N + 1];

        queue.add(N);
        need[N] = 1;

        while (!queue.isEmpty()) {
            int num = queue.poll();

            for (Node next : list.get(num)) {
                int from = next.from;
                int cnt = next.cnt;

                need[from] += need[num] * cnt;
                in_degree[from]--;

                if (in_degree[from] == 0) {
                    queue.add(from);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            if (list.get(i).size() == 0) {
                sb.append(i + " " + need[i]).append("\n");
            }
        }

        System.out.println(sb);
    }

}