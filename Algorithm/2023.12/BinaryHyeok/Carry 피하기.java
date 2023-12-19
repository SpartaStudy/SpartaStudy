import java.util.*;
import java.io.*;

/*
    숫자들을 더했을 때 각각의 자릿수가 10을 넘지 않으면서 최대한 더할 수 있는 숫자의 개수를 구해야 한다.
    이차원 리스트를 사용하여 숫자의 자리수를 분리하여 사용하였고, 백트래킹을 통하여 모든 경우의 수를 고려하면서 찾는다.
    Ex) 522가 입력으로 주어졌을 때
    list.get(0)은 522를 나타냄
    list.get(0).get(0)는 522의 첫번째 자리수인 2를 나타냄
    list.get(0).get(2)는 522의 세번째 자리수인 5를 나타냄
*/
public class Main {
    static List<List<Integer>> list;
    static int answer;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 초기화
        list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(new ArrayList<>());
        }

        // 하나의 숫자를 받아서 자리수를 분리하여 리스트에 담는다.
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());
            while(num > 0){
                int a = num % 10;
                num /= 10;
                list.get(i).add(a);
            }
        }

        int[] sum = new int[9];
        answer = 0;
        backTracking(0, 0, sum);
        System.out.println(answer);
    }

    public static void backTracking(int cnt, int idx, int[] sum){
        // 현재 더한 숫자의 개수를 최대로 계속 갱신한다.
        if(cnt > answer){
            answer = cnt;
        }

        for(int i = idx; i < list.size(); i++){
            List<Integer> l1 = list.get(i);
            // 더하는 것이 가능하다면 각 자리수에 현재 숫자를 넣는다.
            if(isPossible(l1, sum)){
                int[] nSum = sum.clone();
                for(int j = 0; j < l1.size(); j++){
                    nSum[j] += l1.get(j);
                }
                backTracking(cnt + 1, i + 1, nSum);
            }
        }
    }

    // 더하려고 하는 숫자의 자리수를 나눈 list l1과 현재 각 자리의 숫자를 가지고 있는 sum을 가지고 각 자리수 계산
    public static boolean isPossible(List<Integer> l1, int[] sum){
        for(int i = 0; i < l1.size(); i++){
            if(sum[i] + l1.get(i) >= 10)
                return false;
        }

        return true;
    }
}