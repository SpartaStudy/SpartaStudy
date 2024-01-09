import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
그리디?
3개중 2개이상 지켜지는 순서가 원본 -> 가장 큰 두개의 합을 우선순위로
*/
public class 데이터순서복원 {
    static class Num implements Comparable<Num>{
        int idx,w;
        public Num(int idx, int w) {
            this.idx = idx; this.w = w;
        }
        @Override
        public int compareTo(Num o) {
            return this.w-o.w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][3]; 
        for (int j = 0; j < 3; j++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                arr[num][j]=i;
            }
        }
        PriorityQueue<Num> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            int min = N+1;
            for (int j = 0; j < 3; j++) {
                sum+=arr[i][j];
                min = Math.min(min,arr[i][j]);
            }
            sum-=min;
            pq.add(new Num(i, sum));
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            sb.append(pq.poll().idx+" ");
        }
        System.out.println(sb);
    }
}
