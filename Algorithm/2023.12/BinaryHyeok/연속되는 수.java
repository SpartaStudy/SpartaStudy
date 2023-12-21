import java.util.*;
import java.io.*;

/*
    입력으로 받은 값을 set에 저장해 놓고, 하니씩 제외시켜보면서 연속하여 동일한 숫자가 나오는 최대 값을 찾는다.
*/
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int n =Integer.parseInt(st.nextToken());
            list.add(n);
            set.add(n);
        }
        int maxCnt = 0;
        for(Integer K : set){
            int prevNum = -1;
            int cnt = 0;
            for(int i = 0; i < list.size(); i++){
                int currNum = list.get(i);
                if(currNum == K) 
                    continue;
                else if(prevNum == currNum) 
                    cnt++;
                else 
                    cnt = 0;
                prevNum = currNum;
                maxCnt = Math.max(maxCnt, cnt);
            }
        }
        System.out.println(maxCnt + 1);
    }
}