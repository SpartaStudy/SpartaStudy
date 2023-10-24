import java.util.*;

class Solution {
    static class Node {
        int r;
        int c;
        int dir;
        int cost;
        
        public Node(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    static int[][] vector = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public int solution(int[][] board) {
        int len = board.length;
        
        int[][] track = new int[len][len];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                track[i][j] = board[i][j];
                if(track[i][j] != 1) {
                    track[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        boolean[][][] visit = new boolean[len][len][4];
        for(int i = 0; i < 4; i++) {
            visit[0][0][i] = true;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, -1, 0));
        track[0][0] = 0;
        
        int answer = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            
            if(cur.r == len - 1 && cur.c == len - 1) {
                answer = Math.min(answer, cur.cost);
            }
            
            for(int i = 0; i < 4; i++) {
                int nr = cur.r + vector[i][0];
                int nc = cur.c + vector[i][1];
                
                if(nr < 0 || nc < 0 || nr >= len || nc >= len || track[nr][nc] == 1) {
                    continue;
                }
                
                int cost = 100;
                if(cur.dir != -1 && cur.dir != i) {
                    cost += 500;
                }
                
                if(!visit[nr][nc][i] || cur.cost + cost <= track[nr][nc]) {
                    queue.add(new Node(nr, nc, i, cur.cost + cost));
                    track[nr][nc] = cur.cost + cost;
                    visit[nr][nc][i] = true;
                }
                
            }
        }
        
        return answer;
    }

}