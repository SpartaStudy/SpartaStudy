//dp:최적 부분 구조
class Solution {
    int[][] memo;
    int n;
    
    public int solution(int[][] triangle) {
        memo=triangle;
        n=triangle.length;
        
        for(int i=1;i<n;i++){
            memo[i][0]+=memo[i-1][0];//끝부분은 그냥 더하기
            memo[i][i]+=memo[i-1][i-1];
            for(int j=1;j<i;j++){//중간부분 들은 위에 두개중 큰 값 최적 부분 찾기
                memo[i][j]+=Math.max(memo[i-1][j-1],memo[i-1][j]);
            }
        }
        
        int ans=0;
        for(int i=0;i<n;i++){
            ans=Math.max(ans,memo[n-1][i]);
        }
        return ans;
    }
}
