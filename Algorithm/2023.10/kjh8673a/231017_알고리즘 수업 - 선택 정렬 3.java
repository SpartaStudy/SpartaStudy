import java.util.*;
import java.io.*;

public class Main {

    static int[] arr;
    static int N, K, cnt, ans_a, ans_b;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            map.put(arr[i], i);
        }

        cnt = 0;
        ans_a = 0;
        ans_b = 0;
        for (int i = N - 1; i >= 0; i--) {
            int m = map.pollLastEntry().getValue();
            if (i == m) {
                continue;
            }

            cnt++;
            boolean flag = swap(arr, map, i, m);

            if (flag) {
                break;
            }
        }

        if (ans_a == 0) {
            System.out.println(-1);
        } else {
            System.out.println(ans_a + " " + ans_b);
        }
    }

    private static boolean swap(int[] a, TreeMap<Integer, Integer> map, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        map.put(a[j], j);

        if (cnt == K) {
            ans_a = a[j];
            ans_b = a[i];
            return true;
        }

        return false;
    }

}
