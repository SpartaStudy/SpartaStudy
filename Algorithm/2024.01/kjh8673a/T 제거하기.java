import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();
        char tail = t.charAt(t.length() - 1);

        char[] arr = new char[s.length()];
        int idx = 0;

        loop:
        for(int i = 0; i < s.length(); i++) {
            arr[idx++] = s.charAt(i);
            
            if(idx >= t.length() && arr[idx - 1] == tail) {
                int start = idx - t.length();
                for(int j = start; j < start + t.length(); j++) {
                    if(arr[j] != t.charAt(j - start)) {
                        continue loop;
                    }
                }

                idx = start;            
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < idx; i++) {
            sb.append(arr[i]);
        }

        System.out.println(sb);
    }
}