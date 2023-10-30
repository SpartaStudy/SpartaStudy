import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입력 값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<String> list = new LinkedList<>();
        boolean[] checked = new boolean[26];

        for(int i = 0; i < N; i++){
            list.add("?");
        }
        String answer = "";
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            String alpha = st.nextToken();

            for(int j = 0; j < cnt; j++){
                list.addLast(list.removeFirst());
            }

            String str = list.removeFirst();
            int idx = 'Z' - alpha.charAt(0);
            // 같은 알파벳은 다시 나올 수 없다.
            if("?".equals(str) && checked[idx]){
                answer = "!";
                break;
            }
            else if("?".equals(str) || str.equals(alpha)){
                list.addFirst(alpha);
                checked[idx] = true;
            }
            else if(!str.equals(alpha)){
                answer = "!";
                break;
            }
        }

        if("".equals(answer)){
            list.addLast(list.removeFirst());
            while(!list.isEmpty()){
                answer += list.removeLast();
            }
        }

        System.out.println(answer);
    }
}