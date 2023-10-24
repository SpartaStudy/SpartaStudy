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
        int H = Integer.parseInt(st.nextToken());
        List<Integer> a = new ArrayList<>();// 석순
        List<Integer> b = new ArrayList<>();// 종유석

        for(int i = 0; i < N / 2; i++){
            int aLen = Integer.parseInt(br.readLine());
            int bLen = Integer.parseInt(br.readLine());
            a.add(aLen);
            b.add(bLen);
        }

        Collections.sort(a);
        Collections.sort(b);

        int count = 0;
        int min = N;

        for(int i = 1; i <= H; i++){
            int wallCount = lowerBound(a, i) + lowerBound(b, H - i + 1);
            if(wallCount < min){
                min = wallCount;
                count = 1;
            }
            else if(wallCount == min){
                count++;
            }
        }

        System.out.println(min + " " + count);
    }

    public static int lowerBound(List<Integer> list, int target){
        int left = 0;
        int right = list.size();

        while(left < right){
            int mid = (left + right) / 2;

            if(list.get(mid) >= target){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }

        return list.size() - right;
    }
}