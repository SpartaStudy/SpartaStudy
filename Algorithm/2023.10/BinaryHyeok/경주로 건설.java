import java.util.*;

class Solution {
    class Road implements Comparable<Road>{
        int row, col, price, direction;
        
        public Road(int row, int col, int price, int direction){
            this.row = row;
            this.col = col;
            this.price = price;
            this.direction = direction;
        }
        
        @Override
        public int compareTo(Road r){
            return this.price - r.price;
        }
    }
    public static final int STRAIGHT_PRICE = 100;
    public static final int CORNER_PRICE = 500;
    public static int[] dr = {0, -1, 0, 1};
    public static int[] dc = {-1, 0, 1, 0};
    public int solution(int[][] board) {
        int N = board.length;
        int answer = 0;
        
        // visited배열을 가장 큰 정수로 초기화
        int[][][] visited = new int[N][N][4];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < 4; k++){
                    visited[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        // 처음 가능한 방향은 아래, 오른쪽 2가지
        PriorityQueue<Road> q = new PriorityQueue<>();
        q.offer(new Road(0, 0, 0, 2));
        q.offer(new Road(0, 0, 0, 3));
        
        while(!q.isEmpty()){
            Road road = q.poll();
            
            if(road.row == N - 1 && road.col == N - 1){
                answer = road.price;
                break;
            }
            
            for(int i = 0; i < 4; i++){
                int nr = road.row + dr[i];
                int nc = road.col + dc[i];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 1) continue;
                
                int price = roadPrice(road.direction, i);
                // 다음 지점의 기록된 최소비용보다, 현재 위치에서 이동하는 비용이 더 싸면 갱신
                if(road.price + price < visited[nr][nc][i]){
                    visited[nr][nc][i] = road.price + price;
                    q.offer(new Road(nr, nc, road.price + price, i));
                }
            }
        }
        return answer;
    }
  
    public int roadPrice(int currDir, int nextDir){
        if(currDir == nextDir) return STRAIGHT_PRICE;
        else return CORNER_PRICE + STRAIGHT_PRICE;
    }
}