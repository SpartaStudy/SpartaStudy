import java.util.*;
import java.io.*;

public class Main {

    static int N, ans;
    static int[] arr_b;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr_a = new int[N];
        arr_b = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr_a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr_b[i] = Integer.parseInt(st.nextToken());
        }

        ans = 0;
        if (check(arr_a)) {
            ans = 1;
        } else {
            quick_sort(arr_a, 0, N - 1);
        }

        System.out.println(ans);
    }

    private static void quick_sort(int[] a, int left, int right) {
        if (left < right && ans == 0) {
            int pivot = partition(a, left, right);
            quick_sort(a, left, pivot - 1);
            quick_sort(a, pivot + 1, right);
        }
    }

    private static int partition(int[] a, int left, int right) {
        int pivot = a[right];
        int i = left - 1;

        for (int j = left; j <= right - 1; j++) {
            if (a[j] <= pivot) {
                swap(a, ++i, j);
            }
        }
        if (i + 1 != right) {
            swap(a, i + 1, right);
        }

        return i + 1;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;

        if (i != j) {
            if (check(a)) {
                ans = 1;
            }
        }
    }

    private static boolean check(int[] a) {
        for (int i = 0; i < N; i++) {
            if (a[i] != arr_b[i]) {
                return false;
            }
        }
        return true;
    }

}
