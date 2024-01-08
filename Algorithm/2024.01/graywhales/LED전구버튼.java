import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
  루프찾기
*/
public class Main {
    static boolean[] bulbs;
    static int N;
    static long B;
    static long target = -1;
    static Map<String,Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        bulbs = new boolean[N];
        String tmp = "";
        for (int i = 0; i < N; i++) {
            String state = br.readLine();
            if(state.equals("1")){
                bulbs[i]=true;
            }
            tmp+=state;
        }
        map.put(tmp,0L);

        long i=0;
        //타겟 찾기
        while(target==-1){
            button(++i);
            if(i==B){
                target=i;
            }
        }
        //타겟 찾아서 출력
        for (String key:map.keySet()) {
            if(map.get(key)==target){
                for (int j = 0; j < N; j++) {
                    System.out.println(key.charAt(j));
                }
                break;
            }
        }
    }

    private static void button(long depth) {
        String tmp = "";
        boolean[] bulbsTmp = bulbs.clone();
        for (int i = 0; i < N; i++) {
            if(bulbsTmp[(i+N-1)%N]){
                bulbs[i]=!bulbs[i];
            }
            if(bulbs[i]){
                tmp+="1";
            }else{
                tmp+="0";
            }
        }
        if(!map.containsKey(tmp)){
            map.put(tmp,depth);
        }else{
            long start = map.get(tmp);
            long end = depth-1;
            long loop = depth - start;
            long remain = B-end;
            target = start+(remain-1)%loop;
        }
    }
}
