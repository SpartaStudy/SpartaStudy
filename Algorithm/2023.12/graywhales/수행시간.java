import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 수행시간 {
    /*
    위상정렬? dp? 브루트포스?
    예제 30+1+5+16+9+36+2+1+3 = 103
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] comp = new int[n][2];
        StringTokenizer st;
        int maxRank=0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int rank = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            maxRank = Math.max(maxRank, rank);
            comp[i][0]=rank;
            comp[i][1]=time;
        }
        List<Integer>[] ranks = new ArrayList[maxRank+1];
        for(int i=1;i<=maxRank;i++){
            ranks[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            ranks[comp[i][0]].add(i);//해당계급 리스트에 컴퓨터 넣기
        }
        int[] dist = new int[n];
        for (int i: ranks[1]) {//랭크1 dist계산
            dist[i]=comp[i][1];
        }
        for(int i=1;i<maxRank;i++){//랭크
            for (int j : ranks[i+1]) {
                for (int k : ranks[i]) {
                    dist[j]=Math.max(dist[j],dist[k]+(j-k)*(j-k));
                }
                dist[j]+=comp[j][1];
            }
        }
        int ans = 0;
        for (int i : ranks[maxRank]) {//답찾기
            ans = Math.max(ans, dist[i]);
        }
        System.out.println(ans);
    }
}
