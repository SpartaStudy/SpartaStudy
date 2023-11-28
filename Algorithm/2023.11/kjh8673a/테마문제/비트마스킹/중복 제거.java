import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] number = new int[1 << 20];
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            int x = num / 32;
            int y = num % 32;
            if ((number[x] & (1 << y)) == 0) {
                number[x] |= (1 << y);
                sb.append(num + " ");
            }
        }

        System.out.println(sb);
    }

}