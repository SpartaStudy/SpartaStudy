import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int revenue[][] = new int[n][2];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			//기간
			revenue[i][0] = Integer.parseInt(st.nextToken());
			//수익
			revenue[i][1] = Integer.parseInt(st.nextToken());
		}
		int dp[]  = new int[n+1];
		for(int i = n-1; i >= 0; i--) {
			//현재 있는 시간에서 남은 날 까지 일을 할 수 있는 경우를 말함.
			if(i + revenue[i][0] <= n) {
				//이전의 값과 그 구간에 있는 값 + 현재 비용해서 더 큰 값 dp[i]에 저장. 
				dp[i] = Math.max(dp[i+1], dp[i + revenue[i][0]] + revenue[i][1]);
			//현재 있는 시간에서 revenue[i][0]만큼 일을하면 남은날 보다 많은 경우.
			}else {
				//그냥 앞의 값을 그대로 가져오자.
				dp[i] = dp[i+1];
			}
		}
		System.out.println(dp[0]);
	}
}
