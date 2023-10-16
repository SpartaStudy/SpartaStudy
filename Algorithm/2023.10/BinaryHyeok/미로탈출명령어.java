import java.util.*;

class Solution {
    class Point implements Comparable<Point>{
        int row, col, moveCount;
        String path;
        
        public Point(int row, int col, int moveCount, String path){
            this.row = row;
            this.col = col;
            this.moveCount = moveCount;
            this.path = path;
        }
        
        // 사전순 정렬
        @Override
        public int compareTo(Point p){
            return this.path.compareTo(p.path);
        }
    }
    
    // d -> l -> r -> u 순서로 진행(사전순)
    public static int[] dr = {1, 0, 0, -1}; 
    public static int[] dc = {0, -1, 1, 0};
    public static String[] ds = {"d", "l", "r", "u"};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        String answer = "impossible";
        // k와 도착거리의 홀수 짝수가 맞지 않으면 도착 불가
        if((Math.abs(x - r) + Math.abs(y - c)) % 2 != k % 2){
            return answer;
        }
        
        int[][] board = new int[n][m];
        int sR = x - 1;
        int sC = y - 1;
        int eR = r - 1;
        int eC = c - 1;
        
        // 우선순위큐를 사용하여 사전순으로 가장 빠른 순서대로 정렬
        PriorityQueue<Point> q = new PriorityQueue<>();
        q.offer(new Point(sR, sC, 0, ""));
        
        while(!q.isEmpty()){
            Point p = q.poll();
            
            // 도착지점에 도착했을 때 k번 이동해서 도착한 경우
            if(p.row == eR && p.col == eC && p.moveCount == k){
                answer = p.path;
                break;
            }
            
            // 남은 이동거리가 도착지점보다 작을 때는 impossible
            int len = Math.abs(p.row - eR) + Math.abs(p.col - eC);
            if(len > k - p.moveCount) continue;
            
            // d l r u 순으로 큐에 넣는다.
            for(int i = 0; i < 4; i++){
                int nr = p.row + dr[i];
                int nc = p.col + dc[i];
               
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                q.offer(new Point(nr, nc, p.moveCount + 1, p.path + ds[i]));
            }
        }
        return answer;
    }
}