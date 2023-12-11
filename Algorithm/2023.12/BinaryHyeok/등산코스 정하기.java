import java.util.*;

/*
    출발지에서 산봉우리까지의 가장 적은 weight로 가게된다면, 돌아올때도 왔던길로 가면된다. 따라서 출발지에서 산봉우리까지 가는 것만 고려하면 됨
    따라서 출발지, 산봉우리일때는 단방향으로 노드를 넣어주고, 쉼터일경우는 양방향으로 넣어준다.
    다익스트라를 사용하여 가장 적은 weight로 산봉우리로 가는 길을 찾는다.
*/
class Solution {
    class Node{
        int ed, w;
        
        public Node(int ed, int w){
            this.ed = ed;
            this.w = w;
        }
    }
    static List<List<Node>> pathGraph;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        pathGraph = new ArrayList<>();
        
        for(int i = 0; i < n + 1; i++){
            pathGraph.add(new ArrayList<>());
        }
        
        for(int[] path : paths){
            int st = path[0];
            int ed = path[1];
            int w= path[2];
            
            // 출발지점이 출입구이거나 도착지점이 산봉우리라면 단방향입력
            if(isGate(st, gates) || isSummit(ed, summits)){
                pathGraph.get(st).add(new Node(ed, w));
            }
            else if(isGate(ed, gates) || isSummit(st, summits)){
                pathGraph.get(ed).add(new Node(st, w));
            }
            // 도착지점이 쉼터면 양방향 입력
            else{
                pathGraph.get(st).add(new Node(ed, w));
                pathGraph.get(ed).add(new Node(st, w));
            }
        }
        
        return dijkstra(n, gates, summits);
    }
    
    public int[] dijkstra(int n, int[] gates, int[] summits){
        Queue<Node> q = new LinkedList<>();
        int[] intensity = new int[n + 1];
        
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        // 출발점들을 모두 넣어준다.
        for(int gate : gates){
            q.add(new Node(gate, 0));
            intensity[gate] = 0;
        }
        
        while(!q.isEmpty()){
            Node currN = q.poll();
            
            // 현재 웨이트가 저장되있는 intensity보다 크다면 갈 필요없다.
            if(currN.w > intensity[currN.ed]) continue;
            
            for(int i = 0; i < pathGraph.get(currN.ed).size(); i++){
                Node nextN = pathGraph.get(currN.ed).get(i);
                
                // intensity는 웨이트 중 가장 큰 값이므로 max를 이용해서 구해준다.
                int dist = Math.max(intensity[currN.ed], nextN.w);
                // 구한 intensity가 저장되어있는 것 보다 작다면, 갱신한다.
                if(intensity[nextN.ed] > dist){
                    intensity[nextN.ed] = dist;
                    q.add(new Node(nextN.ed, dist));
                }
            }
            
        }
        
        int minPath = Integer.MAX_VALUE;
        int minWeight = Integer.MAX_VALUE;
        
        Arrays.sort(summits);
        
        for(int summit : summits){
            if(intensity[summit] < minWeight){
                minPath = summit;
                minWeight = intensity[summit];
            }
        }
        
        return new int[]{minPath, minWeight};
    }
    
    public boolean isGate(int p, int[] gates){
        for(int g : gates){
            if(p == g)
                return true;
        }
        return false;
    }
    
    public boolean isSummit(int p, int[] summits){
        for(int s : summits){
            if(p == s)
                return true;
        }
        return false;
    }
}