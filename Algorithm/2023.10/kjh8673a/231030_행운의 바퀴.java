import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] arr = new char[N];
        Arrays.fill(arr, '?');
        int idx = 0;
        boolean flag = true;
        int[] visit = new int[26];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            if (!flag) {
                continue;
            }

            idx = (s + idx) % N;
            if (arr[idx] != '?' && arr[idx] != c) {
                flag = false;
            } else {
                if (visit[c - 65] > 0 && visit[c - 65] != idx) {
                    flag = false;
                } else {
                    arr[idx] = c;
                    visit[c - 65] = idx;
                }
            }
        }

        if (!flag) {
            System.out.println("!");
        } else {
            IntStream.iterate(idx, i -> (i + N - 1) % N)
                    .limit(N)
                    .mapToObj(i -> arr[i])
                    .forEach(System.out::print);
        }
    }

}
