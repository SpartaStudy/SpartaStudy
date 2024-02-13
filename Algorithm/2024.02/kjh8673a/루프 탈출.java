import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 1; i < n + 1; i++) {
            int a = Integer.parseInt(br.readLine());
            list.get(i).add(a);
        }

        int answer = 0;
        for(int i = 1; i < n + 1; i++) {
            answer += findLoop(i);
        }

        System.out.println(answer);
    }

    private static int findLoop(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        boolean[] visited = new boolean[n + 1];

        while(!queue.isEmpty()) {
            int node = queue.poll();

            for(int next : list.get(node)) {
                if(visited[next]) {
                    return 0;
                }

                visited[next] = true;
                queue.add(next);
            }
        }

        return 1;
    }

}