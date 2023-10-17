import java.io.*;
import java.util.*;

public class 퀵정렬3
{
    static int n;
    static int[] arr;
    static int[] ans;
    static int isSame=0;
    
    public static void main(String[] args) throws IOException
    {
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
        
        quickSort(0,n-1);
        
        System.out.println(isSame);
    }
    public static void quickSort(int l,int r){
        if(l<r){
    	    // 1.pivot 정하기
    	    int p = partition(); 
    	    // 3.남은 배열 분할하여 재귀 호출
    	    quickSort(arr, l, p-1);  // 정복(Conquer)
    	    quickSort(arr, p+1, r); // 정복(Conquer)
	    }
    }
    public static int partition(int l,int r){
        int p = arr[r]; // 가장 오른쪽값을 피벗으로 설정
        int i = l-1;
        for(int j=l;j<=r-1;j++){
    	    if(arr[j]<=p) swap(arr,++i,j);
    	}
    	if(i+1 != r) swap(arr,i+1,r);
        return 1+1;
    }
    public void swap(int a,int b){
    	int tmp=arr[b];
    	arr[b]=arr[a];
    	arr[a]=tmp;
    	if(isSame==0) check();
    }
    public void check(){
        boolean flag=false;
        for(int i=0;i<n;i++){
            if()
        }
    }
}
