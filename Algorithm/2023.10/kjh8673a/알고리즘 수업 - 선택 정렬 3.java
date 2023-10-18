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
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        cnt = 0;
        ans_a = 0;
        ans_b = 0;

        selection_sort(arr);

        if (ans_a == 0) {
            System.out.println(-1);
        } else {
            System.out.println(ans_a + " " + ans_b);
        }
    }

    private static void selection_sort(int[] a) {
        for (int i = 0; i < N - 1; i++) {
            if (ans_a != 0) {
                break;
            }

            int idx = i;
            for (int j = i + 1; j < N; j++) {
                if (a[idx] > a[j]) {
                    idx = j;
                }
            }

            if (i != idx) {
                swap(a, i, idx);
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        cnt++;
        if (cnt == K) {
            ans_a = a[i];
            ans_b = a[j];
        }
    }

}
