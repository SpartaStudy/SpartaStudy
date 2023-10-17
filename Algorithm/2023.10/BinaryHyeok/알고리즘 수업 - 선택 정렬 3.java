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
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        int changeCnt = 0;
        loop : for(int i = N - 1; i > 0; i--){
            int maxIdx = i;

            for(int j = 0; j < i; j++){
                if(A[j] > A[maxIdx]){
                    maxIdx = j;
                }
            }

            if(maxIdx == i) continue;

            int tmp = A[i];
            A[i] = A[maxIdx];
            A[maxIdx] = tmp;

            if(++changeCnt == K){
                System.out.println(A[maxIdx] + " " + A[i]);
                break loop;
            }
        }
        if(changeCnt < K){
            System.out.println(-1);
        }
    }
}