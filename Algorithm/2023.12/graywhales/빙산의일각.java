import java.util.*;
//가장 높은거부터 확인
//힙큐사용
public class 빙산의일각 {
    static class Iceberg implements Comparable<Iceberg>{
        int idx;
        int height;

        public Iceberg(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }

        @Override
        public int compareTo(Iceberg o) {
            return o.height-this.height;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Iceberg> pq = new PriorityQueue<>();
        boolean[] visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            int h = sc.nextInt();
            list.add(h);
            pq.add(new Iceberg(i,h));
        }
        int count = 0;
        int ans = 0;
        while(!pq.isEmpty()){
            Iceberg now = pq.poll();
            int idx =now.idx;
            visit[idx] = true;

            boolean left = false;
            boolean right = false;
            if(now.idx-1>=0 && visit[now.idx-1]) left = true;
            if(now.idx+1<N && visit[now.idx+1]) right = true;

            if(left && right){
                count--;
            }else if(!(left||right)){
                count++;
            }
            if(!pq.isEmpty()&&pq.peek().height!=now.height)
                ans=Math.max(ans,count);
        }
        System.out.println(ans);
    }
}

