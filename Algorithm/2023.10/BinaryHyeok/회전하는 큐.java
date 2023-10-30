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
        int M = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();

        for(int i = 1; i <= N; i++){
            list.add(i);
        }

        int answer = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int ele = Integer.parseInt(st.nextToken());
            int idx = list.indexOf(ele);

            // 오른쪽으로 시프트해서 뽑는게 더 빠름(3번연산)
            if(idx >= (list.size() + 1) / 2){
                for(int j = 0; j < list.size() - idx; j++){
                    answer++;
                    list.add(0, list.remove(list.size() - 1));
                }
            }
            // 왼쪽 시프트해서 뽑는게 더 빠름(2번연산)
            else{
                for(int j = 0; j < idx; j++){
                    answer++;
                    list.add(list.remove(0));
                }
            }
            // 정답뽑기(1번연산)
            list.remove(0);
        }
        System.out.println(answer);
    }
}