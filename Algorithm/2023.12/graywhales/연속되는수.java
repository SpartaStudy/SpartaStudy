import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
완탐 + 구현
 */
public class 연속되는수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            set.add(arr[i]);
        }
        int ans = 0;
        for (int delete : set) {
            int before = -1;
            int count = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i] == delete) continue;
                if (before == arr[i]) {
                    count++;
                } else {
                    before = arr[i];
                    count = 1;
                }
                ans = Math.max(ans, count);
            }
        }
        System.out.println(ans);
    }
}
