import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[] calendar = new int[365 + 2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            calendar[start]++;
            calendar[end + 1]--;
        }

        int answer = 0;
        int length = 0;
        int height = 0;
        for (int i = 1; i < calendar.length; i++) {
            calendar[i] += calendar[i - 1];
            if (calendar[i] == 0) {
                answer += length * height;
                length = 0;
                height = 0;
            } else {
                length++;
                height = Math.max(calendar[i], height);
            }
        }

        System.out.println(answer);
    }

}