/*
DP, 경우의 수 구하기
총 2n+1+top 중 마름모가 될 위치를 고르는 경우의수
mid는 총 3/4가지 경우의수(안고름,왼쪽,오른쪽 or 위)가 있다.
n번 선형탐색
*/
class Solution {
    int mod = 10007;
    int[][] dp;
    public int solution(int n, int[] tops) {
        dp = new int[n][2];
        
        dp[0][0] = 2 + tops[0];
        dp[0][1] = 1; //오른쪽
        
        for(int i=1;i<n;i++){
            dp[i][0] = dp[i-1][0] * (2 + tops[i]) + dp[i-1][1] * (1 + tops[i]);
            dp[i][1] = dp[i-1][0] + dp[i-1][1];
            dp[i][0] %= mod;
            dp[i][1] %= mod;
        }
        
        int ans = (dp[n-1][0]+dp[n-1][1])%mod;
        return ans;
    }
}
