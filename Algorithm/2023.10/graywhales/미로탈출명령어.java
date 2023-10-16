import java.util.*;
/*
    bfs사용 사전순으로 정렬 위해 우선순위큐 사용
    + 가지치기를 위한 이동가능 여부 판별(impossible,도착가능한지, 범위 벗어나는지)
*/

class Solution {
    
    static class Node implements Comparable<Node>{
        String str;
        int x,y,cnt;
        
        public Node(String str,int x,int y,int cnt){
            this.str=str;
            this.x=x;
            this.y=y;
            this.cnt=cnt;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.str.compareTo(o.str);
        }
    }
    
    static int n,m,r,c,k;
    static String answer;
    static int[] dx={1,0,0,-1};
    static int[] dy={0,1,-1,0};
    static String[] ds={"d","r","l","u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        
        answer = "impossible";
        
        int dist=Math.abs(x-r)+Math.abs(y-c);
        
        if(k-dist<0||(k-dist)%2!=0){//불가능 조건
            answer="impossible";
        }else{
            answer=bfs(x,y);
        }
        
        return answer;
    }
    
    private static String bfs(int sx,int sy){
        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.offer(new Node("",sx,sy,0));
        int x,y,cnt,nx,ny;
        String str;
        while(!pq.isEmpty()){
            Node node=pq.poll();
            x=node.x;
            y=node.y;
            cnt=node.cnt;
            str=node.str;
            if(cnt==k&&x==r&&y==c){
                return node.str;
            }
            //도착지까지 이동불가
            if(Math.abs(r-x)+Math.abs(c-y)>k-cnt) continue;
            for(int i=0;i<4;i++){
                nx=x+dx[i];
                ny=y+dy[i];
                if(nx<1||ny<1||nx>n||ny>m||cnt>k) continue;
                pq.offer(new Node(str+ds[i],nx,ny,cnt+1));
            }
        }
        return "x";
    }
}
