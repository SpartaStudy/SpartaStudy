import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int v;
        int cost;
        
        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    
    static List<List<Node>> list;
    static int[] answer;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        list = new ArrayList<>();
        for(int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }
        
        // gate와 summit의 정보를 찾기 쉽도록 배열에 저장
        int[] nodes = new int[n + 1];
        for(int gate : gates) {
            nodes[gate] = 1;
        }
        for(int summit : summits) {
            nodes[summit] = 2;
        }
        
        for(int i = 0; i < paths.length; i++) {
            int a = paths[i][0];
            int b = paths[i][1];
            int cost = paths[i][2];
            
            // gate는 나가는 방향만, summit은 들어오는 방향만 저장
            if(nodes[a] == 1 || nodes[b] == 2) {
                list.get(a).add(new Node(b, cost));
            }else if(nodes[b] == 1 || nodes[a] == 2) {
                list.get(b).add(new Node(a, cost));
            }else {
                list.get(a).add(new Node(b, cost));
                list.get(b).add(new Node(a, cost));
            }
        }

        answer = new int[] { 50_001, 10_000_001 };
        
        for(int gate : gates) {
            // gate를 시작으로 하는 dijkstra
            int[] dist = dijkstra(gate, n);
            
            // dijkstra배열에서 summit값 비교하여 정답 갱신
            for(int summit : summits) {
                if(answer[1] > dist[summit]) {
                    answer[0] = summit;
                    answer[1] = dist[summit];
                }else if(answer[1] == dist[summit]) {
                    answer[0] = Math.min(summit, answer[0]);
                }
            }
        }
        
        return answer;
    }
    
    private static int[] dijkstra(int st, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        boolean[] visit = new boolean[n + 1];
        
        pq.offer(new Node(st, 0));
        dist[st] = 0;
        
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            if(visit[node.v]) {
                continue;
            }
            visit[node.v] = true;
            
            for(Node next : list.get(node.v)) {
                if(!visit[next.v] && dist[next.v] > Math.max(dist[node.v], next.cost)) {
                    dist[next.v] = Math.max(dist[node.v], next.cost);
                    
                    // 정답 배열에 저장된 intensity의 최솟값보다 작거나 같을 때만 진행
                    // 더 크면 더이상 볼 필요없음
                    if(answer[1] >= dist[next.v]) {
                        pq.add(new Node(next.v, dist[next.v]));
                    }
                }
            }
        }
        
        return dist;        
    }
}