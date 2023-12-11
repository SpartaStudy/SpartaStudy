import java.util.*;
/*
  bfs + prioirtyqueue
*/
class Solution {
    class Node implements Comparable<Node>{
        int num;
        int time;

        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time==o.time?this.num-o.num:this.time-o.time;
        }
    }
    List<Node>[] graph;
    int[] ans = new int[]{50001,10000001};
    int min=10000001;
    Set<Integer> summit = new HashSet<>();
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<paths.length;i++){
            int a=paths[i][0];
            int b=paths[i][1];
            int t=paths[i][2];
            min=Math.min(min,t);
            graph[a].add(new Node(b,t));
            graph[b].add(new Node(a,t));
        }
        boolean[] visit=new boolean[n+1];
        for(int i=0;i<gates.length;i++){
            visit[gates[i]]=true; //입구들은 일단 다 방문처리
        }
        int minsum=50001; //가장 작은 summit
        for(int i=0;i<summits.length;i++){
            summit.add(summits[i]);
            minsum=Math.min(minsum,summits[i]);
        }

        boolean[] visited;
        for(int i=0;i<gates.length;i++){
            if(ans[1]==min&&ans[0]==minsum) break; //최적의 값일 경우 그냥 종료 (없으면 시간초과..)
            visited = visit.clone();
            int gate=gates[i];
            bfs(gate,visited);
        }

        return ans;
    }

    private void bfs(int gate, boolean[] visit) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(gate,0));
        visit[gate]=false;
        int max=0; //지금까지 가장 큰 intensity
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int num = now.num;
            int time = now.time;
            if(visit[num]) continue;
            visit[num]=true;
            
            max=Math.max(max,time); //intensity 갱신

            if(max>ans[1]){ //가지치기
                return;
            }
            if(summit.contains(num)){ //정상이라면?
                if(max==ans[1]){ //정상 오름차순을 위해...
                    ans[0]=Math.min(ans[0],num);
                    continue;
                }
                ans[0]=num;
                ans[1]=max;
                continue;
            }

            for(Node next : graph[num]){
                if(visit[next.num]) continue;
                pq.add(next);
            }

        }

    }


}
