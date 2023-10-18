import java.io.*;
import java.util.*;

public class Main{
    static int n;
    static int[] arr;
    static int[] ans;
    static int isSame=0;
    static boolean possible=true;
    /*
        오른쪽 퀵정렬 구현후 같은지 교체할때마다 체크하는 부분 넣어주기.
        수도코드 잘안됨.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        n=Integer.parseInt(br.readLine());

        arr=new int[n];
        ans=new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            ans[i]=Integer.parseInt(st.nextToken());
        }
        check();
        if(isSame==0) quickSort(0,n-1);

        System.out.println(isSame);
    }
    public static void quickSort(int l,int r){
        if(isSame==0&&possible&&l<r){
            // 1.pivot 정하기
            int p = partition(l,r); //분할
            //가지치기
            if(arr[p]!=ans[p]) {
                possible = false;
                return;
            }
            // 3.남은 배열 분할하여 재귀 호출
            quickSort(l, p-1); //왼쪽 부분 배열 정렬
            quickSort(p+1, r); //오른쪽 부분 배열 정렬
        }
    }
    public static int partition(int l,int r){
        if(isSame==1) return 0;
        int p = arr[r]; // 가장 오른쪽값을 피벗으로 설정
        int lo=l;
        int hi=r;
        // lo가 hi보다 작을 때 까지만 반복한다.
        while(lo < hi) {

            // lo에 있는값이 p보다 클때까지 증가
            while (lo < hi && arr[lo] < p) {
                lo++;
            }
            // hi에 있는값이 p보다 작거나 같을때까지 감소
            while (lo < hi && arr[hi] >= p) {
                hi--;
            }
            // p보다 큰값과 p보다 작은값을 교환
            swap(lo,hi);
        }
        // 처음의 pivot의 위치와 마지막 hi의 위치와 변경해주기.
        swap(hi,r);
        // pivot의 위치 반환
        return hi;
    }
    public static void swap(int a,int b){
        int tmp=arr[b];
        arr[b]=arr[a];
        arr[a]=tmp;
        if(isSame==0&&possible) check();
    }
    public static void check(){
        for(int i=0;i<n;i++){
            if(arr[i]!=ans[i]) return;
        }
        isSame=1;
    }
}
