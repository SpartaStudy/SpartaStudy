import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> list;
    static char answer;
    static boolean flag;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        String moo = "moo";
        list.add(3);
        // 최대 depth를 구함
        int t = 0;
        for(int i = 1; i <= N; i++){
            int n = list.get(i - 1) * 2 + i + 3;
            list.add(n);
            if(n >= N){
                t = i;
                break;
            }
        }
     
        flag = false;
        moo(t, N);
        System.out.println(answer);

    }

    public static void moo(int t, int N){
        if(flag) return;
        
        if(t == 0){
            answer = getChar("moo", N - 1);
            flag = true;
            return;
        }

        int mid = t + 3;
        int prevLen = list.get(t - 1);

        if(prevLen >= N){
            moo(t - 1, N);
        }
        else if(prevLen + mid >= N){
             String str = "moo";
            for(int i = 0; i < t; i++){
                str += "o";
            }
            answer = getChar(str, N - prevLen - 1);
            flag = true;
        }
        else{
            moo(t - 1, N - prevLen - mid);
        }
    }

    public static char getChar(String str, int n){
        return str.charAt(n);
    }
}