import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
  완탐!
*/
public class 아름다운수열 {
    static int n,m;
    static BufferedReader br;
    private static int readInt() throws IOException {
        return Integer.parseInt(br.readLine().split(" ")[0]);
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = readInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = readInt();
        }
        m = readInt();
        int[] s = new int[m];
        for (int i = 0; i < m; i++) {
            s[i] = readInt();
        }

        Arrays.sort(s);
        for (int i = m-1; i >=0; i--) {
            s[i]-=s[0];
        }

        //찾기
        int ans = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n-m+1;i++){
            int[] tmp = Arrays.copyOfRange(arr,i,i+m);
            Arrays.sort(tmp);
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if(s[j]!=tmp[j]-tmp[0]) flag=false;
            }
            if(flag){
                sb.append(i+1+"\n");
                ans++;
            }
        }
        sb.insert(0,ans+"\n");

        System.out.println(sb);
    }
}
