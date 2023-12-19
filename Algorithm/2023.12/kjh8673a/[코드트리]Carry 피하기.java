import java.io.*;
import java.util.*;

public class Main {
    static int n, len;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());
        arr = new int[n];
        len = 0;
        for(int i = 0; i < n; i++) {
            String num = br.readLine().trim();
            arr[i] = Integer.parseInt(num);
            len = Math.max(num.length(), len);
        }

        int answer = 0;
        for(int i = 1; i < (1 << n); i++) {
            if(!checkIsCarry(i)) {
                answer = Math.max(answer, Integer.bitCount(i));
            }
        }

        System.out.println(answer);
    }

    private static boolean checkIsCarry(int selected) {
        int[] tmp = new int[len];
        for(int i = 0; i < n; i++) {
            if((selected & (1 << i)) > 0) {
                int num = arr[i];
                int idx = 0;
                while(num > 0) {
                    tmp[idx] += num % 10;
                    if(tmp[idx] >= 10) {
                        return true;
                    }
                    num /= 10;
                    idx++;
                }
            }
        }

        return false;
    }
}