import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[k][2];
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = Math.min(a, b);
            arr[i][1] = Math.max(a, b);
        }

        Arrays.sort(arr, (o1, o2) -> {
           if (o1[0] == o2[0]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]); 
        });

        int answer = 0;
        int line = 0;
        for(int i = 0; i < k; i++) {
            if(arr[i][0] < line) {
                continue;
            }else {
                line = arr[i][1];
                answer++;
            }
        }
        if(line <= n) {
            answer++;
        }        

        System.out.println(answer);
    }
}