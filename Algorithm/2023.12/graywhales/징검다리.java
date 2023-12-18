import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
밟을 징검다리 수를 이진탐색
 */
public class 징검다리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (long i = 0; i < T; i++) {
            long N = Long.parseLong(br.readLine());
            // 근의 공식을 써도 풀린다. x^2+x-2n =0의 근의 소수버림으로 풀면 풀림.
            // long num= (long)Math.floor((-1+Math.sqrt(8*N+1))/2);
            // sb.append(num+"\n");
            sb.append(bs(N) + "\n");
        }
        System.out.println(sb);
    }

    private static long bs(long n) {
        long left = 1, right = 200000000;
        long ans = 0;
        //기존에 left<right 로직으로는 최댓값을 왜 못찾을까
        while (left <= right) {
            long mid = (left + right) / 2;
            if (check(mid, n)) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private static boolean check(long c, long n) {
        long sum = (c * (c + 1)) / 2;
        return sum <= n;
    }
}
