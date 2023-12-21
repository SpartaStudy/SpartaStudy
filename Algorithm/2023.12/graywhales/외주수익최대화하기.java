import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
dp or dfs
냅색스러운
*/
public class 외주수익최대화하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        int[][] dp = new int[n][n+1];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            arr[i][0]=t;
            arr[i][1]=p;
        }
        for(int i=arr[0][0];i<=n;i++){
            dp[0][i]=arr[0][1];
        }
        for(int i=1;i<n;i++){
            int t = arr[i][0];
            int p = arr[i][1];
            int tmp = dp[i-1][i]+p;
            for (int j = 0; j <= n; j++) {
                dp[i][j]=dp[i-1][j];
                if(j<i+t) continue;
                dp[i][j]=Math.max(dp[i][j],tmp);
            }
        }

        System.out.println(dp[n-1][n]);
        
    }
}
