class Solution {
    static class Hole {
        int r;
        int c;
        
        public Hole(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static int M, N;
    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(lock[i][j] == 0) {
                    cnt++;
                }
            }
        }
        
        boolean answer = false;
        
        // 자물쇠의 홈이 0개면 해볼필요없음.
        if(cnt == 0) {
            answer = true;
        }else {
            answer = check(key, lock);
        }
        
        return answer;
    }
    
    private static boolean check(int[][] key, int[][] lock) {
        if(match(key, lock)) {
            return true;
        }
        for(int i = 0; i < 3; i++) {
            key = rotate(key);
            if(match(key, lock)) {
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean match(int[][] key, int[][] lock) {
        for(int i = N - 1; i >= 1 - M; i--) {
            for(int j = N - 1; j >= 1 - M; j--) {
                boolean flag = true;
                for(int r = 0; r < N; r++) {
                    for(int c = 0; c < N; c++) {
                        // lock의 (r, c)와 key의 (r - i,c - j)가 대응한다.
                        if(r - i < 0 || c - j < 0 || r - i >= M || c - j >= M) {
                            // lock의 위치에 대응되는 key가 없을 때(범위를 벗어남) lock이 0이라면 실패
                            if(lock[r][c] == 0) {
                                flag = false;
                                break;
                            }
                        }else {
                            // 둘 다 1이거나 0이면 실패
                            if((lock[r][c] == 1 && key[r - i][c - j] == 1) || (lock[r][c] == 0 && key[r - i][c - j] == 0)) {
                                flag = false;
                                break;
                            }
                        }
                    }
                }
                
                if(flag) {
                    return true;
                }                
            }
        }
        
        return false;
    }
    
    private static int[][] rotate(int[][] key) {
        int[][] tmp = new int[M][M];
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M; j++){
                tmp[i][j] = key[M - j - 1][i];
            }
        }
        
        return tmp;
    }
    
    
}