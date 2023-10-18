import java.util.*;
import java.io.*;
public class Main {
    /*
        selectionSort는 O(n^2)이라 n이 50만이면 시간초과. 또한 최대 swap이 n번 일어나고 탐색은 n^2/2만큼 일어남
        서로다른 정수만이 들어오기에 nlogn 복잡도를 가진방법으로 정렬을 index를 매겨 내림차순으로 하고 탐색을 하지않고 n번 직접 교환하면서 찾아내기.
        + 직접 교환할때 map에 있던 작은값의 index또한 교체해주기.
     */
    static int n,k;
    static int[] arr;
    static TreeMap<Integer,Integer> map =new TreeMap<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        arr=new int[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
            map.put(arr[i],i);
        }

        boolean flag=false;
        int maxIdx,idx=-1,idx2=0;
        int s=-1,b=-1;
        for(Integer i:map.keySet()){
            idx++;
            maxIdx=map.get(i);
            if(arr[n-1-idx]==i) continue;
            if(++idx2==k){
                s=arr[n-1-idx];
                b=i;
                flag=true;
                break;
            }
            swap(n-1-idx,maxIdx);
        }

        if(flag) System.out.println(s+" "+b);
        else System.out.println(-1);
    }
    private static void swap(int a, int b) {
        int tmp=arr[a];
        arr[a]=arr[b];
        arr[b]=tmp;
        map.put(arr[b],b);//바뀐 자리의 idx를 다시바꿔주기
    }
}
