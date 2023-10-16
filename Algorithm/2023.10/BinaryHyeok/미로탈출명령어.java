import java.util.*;

class Solution {
    class Point{
        int row, col, moveCount;
        String path;
        
        public Point(int row, int col, int moveCount, String path){
            this.row = row;
            this.col = col;
            this.moveCount = moveCount;
            this.path = path;
        }
    }
    
    // d -> l -> r -> u 순서로 진행(사전순)
    public static int[] dr = {1, 0, 0, -1}; 
    public static int[] dc = {0, -1, 1, 0};
    public static String[] ds = {"d", "l", "r", "u"};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        if((Math.abs(x - r) + Math.abs(y - c)) % 2 != k % 2){
            return "impossible";
        }
        
        int[][] board = new int[n][m];
        int sR = x - 1;
        int sC = y - 1;
        int eR = r - 1;
        int eC = c - 1;
        
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(sR, sC, 0, ""));
        String answer = "";
        while(true){
            Point p = q.poll();
            
            if(p.row == eR && p.col == eC && p.moveCount == k){
                answer = p.path;
                break;
            }
            
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