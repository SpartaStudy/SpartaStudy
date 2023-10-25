import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] bottom = new int[N / 2];
        int[] top = new int[N / 2];
        for (int i = 0; i < N / 2; i++) {
            bottom[i] = Integer.parseInt(br.readLine());
            top[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bottom);
        Arrays.sort(top);

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i < H + 1; i++) {
            int tmp = binarySearch(0, N / 2, i, bottom) + binarySearch(0, N / 2, H - i + 1, top);

            if (tmp < min) {
                min = tmp;
                cnt = 1;
            } else if (tmp == min) {
                cnt++;
            }
        }

        System.out.println(min + " " + cnt);
    }

    // lower bound - key값보다 같거나 큰 값이 나오는 위치 r
    private static int binarySearch(int l, int r, int key, int[] arr) {
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (arr[mid] < key) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        // r값보다 아래있는거는 파괴하고 지나감
        return arr.length - r;
    }

}
