import java.io.*;
import java.util.*;
/*
    위상정렬 문제
    각 부품별 필요한 부품을 맵에다 넣기.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        StringTokenizer st;
        int n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());

        HashMap<Integer,HashMap<Integer,Integer>> parts= new HashMap<>();
        //부품별 필요한 부품 해쉬맵 생성
        for(int i=1;i<=n;i++){
            parts.put(i,new HashMap<>());
        }
        int[] indegree = new int[n];
        int x,y,k;
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            x=Integer.parseInt(st.nextToken());
            y=Integer.parseInt(st.nextToken());
            k=Integer.parseInt(st.nextToken());
            indegree[y]++;
            parts.get(x).put(y,k);
        }

        int[] partCount = new int[n+1];
        Queue<Integer> q=new ArrayDeque<>();
        q.offer(n);
        partCount[n]=1;
        int now;
        while(!q.isEmpty()){
            now=q.poll();
            for(int next:parts.get(now).keySet()){
                //필요한 부품 갯수=완제품 만들기 위한 부품갯수 *완제품갯수
                partCount[next]+=parts.get(now).get(next)*partCount[now];
                if(--indegree[next]==0){
                    q.offer(next);
                }
            }
        }
        for(int i=1;i<=n;i++){
            if(parts.get(i).isEmpty()){
                sb.append(i+" "+partCount[i]+"\n");
            }
        }
        System.out.println(sb);
    }
}
