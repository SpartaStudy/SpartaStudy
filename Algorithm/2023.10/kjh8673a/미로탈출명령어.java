import java.util.*;

class Solution {
    static int n, m, r, c, k;
    static String answer;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;
        
        answer = "impossible";
        dfs(x, y, "");
        
        return answer;
    }
    
    private static void dfs(int i, int j, String tmp) {
        if(isNotPossible(i, j, tmp.length())) {
            return;
        }
        
        if(tmp.length() == k) {
            if(i == r && j == c && answer.equals("impossible")) {
                answer = tmp;
            }
            return;
        }
        
        dfs(i + 1, j, tmp + "d");
        dfs(i, j - 1, tmp + "l");
        dfs(i, j + 1, tmp + "r");
        dfs(i - 1, j, tmp + "u");
    } 
    
    private static boolean isNotPossible(int i, int j, int cnt) {
        if(!answer.equals("impossible")) {
            return true;
        }
        
        if(i <= 0 || j <= 0 || i > n || j > m) {
            return true;
        }
        
        int dist = Math.abs(r - i) + Math.abs(c - j);
        if(k - cnt < dist) {
            return true;
        }
        if((k - cnt - dist) % 2 == 1) {
            return true;
        }
        
        return false;
    }
}