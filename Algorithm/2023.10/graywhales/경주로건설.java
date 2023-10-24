import java.util.*;

class Solution {
    /*
        n의 크기가 3이상 25이하
        bfs + min값 가지치기
        visited를 depth 하나 더 둬서 before 방향에 따라 4개 더추가. min값 교체하면서 가기.
    */
    static class Node implements Comparable<Node>{
        int r,c,before,cost;
        
        public Node(int r,int c,int before,int cost){
            this.r=r;
            this.c=c;
            this.before=before;
            this.cost=cost;
        }
        
        @Override
        public int compareTo(Node o){
            return this.cost-o.cost;
        }
    }
    static int n;
    static int[][] board;
    static int[][][] visited;
    static int min=1000000;
    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};
    
    public int solution(int[][] board) {
        n=board.length;
        this.board=board;
        visited=new int[n][n][4];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<4;k++){
                    visited[i][j][k]=1000000;
                }
            }
        }
        bfs();
        
        return min;
    }
    public void bfs(){
        PriorityQueue<Node> pq=new PriorityQueue<>();
        if(board[1][0]==0){//하
            pq.add(new Node(1,0,1,100));
            visited[1][0][1]=100;
        }
        if(board[0][1]==0){//우
            pq.add(new Node(0,1,3,100));
            visited[0][1][3]=100;
        }

        Node node;
        int r,c,b,cost,nr,nc,cost2;
        while(!pq.isEmpty()){
            node = pq.poll();
            r=node.r;
            c=node.c;
            b=node.before;
            cost=node.cost;
            
            // if(cost>=min) continue;
            if(r==n-1&&c==n-1){
                min=cost;
                return;
            }
            
            for(int i=0;i<4;i++){
                nr=r+dr[i];
                nc=c+dc[i];
                
                if(nr<0||nc<0||nr>=n||nc>=n) continue;
                if(board[nr][nc]==1) continue;
                
                if(i==b) cost2=cost+100;
                else cost2=cost+600;
                
                if(visited[nr][nc][i]<=cost2) continue;
                pq.add(new Node(nr,nc,i,cost2));
                visited[nr][nc][i]=cost2;
                
            }
        }
        
    }
}
