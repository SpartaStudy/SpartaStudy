import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    그래프 using bfs
    문제의 입력이 from to로 주어지지 않았고 양방향이라서
    visit 체크하면서 가야했던 문제
 */

public class 너구리구구 {
    static class Edge {
        int from;
        int to;
        int distance;
        Edge next;

        public Edge(int from, int to, int distance, Edge next) {
            this.from = from;
            this.to = to;
            this.distance = distance;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        long[] path = new long[n];
        Edge[] adjList = new Edge[n];
        int from, to, distance;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            distance = Integer.parseInt(st.nextToken());
            adjList[from] = new Edge(from, to, distance, adjList[from]);
            adjList[to] = new Edge(to, from, distance, adjList[to]);
        }
        Queue<Edge> q = new ArrayDeque<>();
        boolean[] visit= new boolean[n];
        visit[0]=true;
        for (Edge edge = adjList[0]; edge!=null; edge = edge.next) {
            q.add(edge);
            visit[edge.to]=true;
        }
        Edge now;
        while (!q.isEmpty()) {
            now = q.poll();
            path[now.to] = path[now.from] + now.distance;
            for (Edge edge = adjList[now.to]; edge!= null; edge = edge.next) {
                if(visit[edge.to]) continue;
                visit[edge.to]=true;
                q.add(edge);
            }
        }
        long max=0;
        for(int i=0;i<n;i++){
            if(max<path[i]) max=path[i];
        }
        System.out.println(max);
    }
}
