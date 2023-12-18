import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[] arrA = new int[N + 1];
        for(int i = 1; i < N + 1; i++) {
            arrA[i] = Integer.parseInt(br.readLine().trim());
        }

        int M = Integer.parseInt(br.readLine().trim());
        int[] arrB = new int[M];
        for(int i = 0; i < M; i++) {
            arrB[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(arrB);

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = 1; i < N + 1 - M + 1; i++) {
            int[] tmp = new int[M];
            for(int j = 0; j < M; j++) {
                tmp[j] = arrA[i + j];
            }
            Arrays.sort(tmp);

            if(checkIsBeautiful(tmp, arrB)) {
                sb.append(i).append("\n");
                count++;
            }
        }

        System.out.println(count);
        System.out.println(sb);
    }

    private static boolean checkIsBeautiful(int[] a, int[] b) {
        int dist = a[0] - b[0];
        for(int i = 1; i < a.length; i++) {
            if(a[i] - b[i] != dist) {
                return false;
            }
        }

        return true;
    }

}