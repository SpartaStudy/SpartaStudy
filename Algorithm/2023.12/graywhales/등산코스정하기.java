/*
  bfs + prioirtyqueue
*/

import java.util.*;

class Solution {

    class Node implements Comparable<Node> {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    int[] ans = new int[]{0, 10000001};
    List<Node>[] graph;
    PriorityQueue<Node> pq;
    boolean[] summit;
    boolean[] visit;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {        
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        //connect
        for (int i = 0; i < paths.length; i++) {
            int a = paths[i][0];
            int b = paths[i][1];
            int t = paths[i][2];
            graph[a].add(new Node(b, t));
            graph[b].add(new Node(a, t));
        }
        //gate pq에 추가
        pq = new PriorityQueue<>();
        for (int i = 0; i < gates.length; i++) {
            pq.add(new Node(gates[i], 0));
        }
        //summit 등록
        summit = new boolean[n + 1];
        for (int i = 0; i < summits.length; i++) {
            summit[(summits[i])] = true;
        }
        
        visit = new boolean[n + 1];
        bfs();

        return ans;
    }


    private void bfs() {
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int num = now.num;
            int weight = now.weight;

            if (visit[num]) continue;
            visit[num] = true;
            //프루닝
            if (weight > ans[1]) {
                return;
            }
            //정상일 경우
            if (summit[num]) {
                //오름차순 로직
                if (weight == ans[1]) {
                    ans[0] = Math.min(ans[0], num);
                    continue;
                }
                ans[0] = num;
                ans[1] = weight;
                continue;
            }
            //인접노드 확인후 추가
            for (Node next : graph[num]) {
                if (visit[next.num]) continue;
                pq.add(new Node(next.num, Math.max(weight, next.weight)));
            }
        }
    }
}
