import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
이분 탐색으로 jealous 가능한 값 찾기
 */
public class 보석상자 {
    static int N, M;
    static int[] arr;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        N = Integer.parseInt(input.split(" ")[0]);
        M = Integer.parseInt(input.split(" ")[1]);
        arr = new int[M];
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
        System.out.println(bs());
    }

    private static int bs() {
        int left = 1, right = max;
        while (left < right) {
            int mid = (left + right) / 2;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    private static boolean check(int jealous) {
        int cnt = 0;
        for (int jewel : arr) {
            cnt += ((jewel + jealous - 1) / jealous);
        }
        return cnt <= N;
    }
}
