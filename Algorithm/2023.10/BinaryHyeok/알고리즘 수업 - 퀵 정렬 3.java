import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] B;
    static int flag;
    public static void main(String[] args) throws IOException{
        // 입력 값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        B = new int[N];
        flag = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        compareArray(A);
        quickSort(A, 0, N - 1);

        System.out.println(flag);
    }

    public static void quickSort(int[] A, int left, int right){

        if(left >= right) return;
        if(flag == 1) return;

        int pivot = partition(A, left, right);

        quickSort(A, left, pivot - 1);
        quickSort(A, pivot + 1, right);
    }

    public static int partition(int[] A, int left, int right){
        int lo = left;
        int hi = right;
        int pivot = A[right];

        while(lo < hi){
            // lo의 요소가 pivot보다 큰 값을 먼저 찾아야 됨
            while(lo < hi && A[lo] < pivot){
                lo++;
            }

            while(lo < hi && A[hi] >= pivot){
                hi--;
            }

            swap(A, lo, hi);
            compareArray(A); // 스왑해줄때마다 B배열과 비교
        }
        swap(A, hi, right);
        compareArray(A);
        return hi;
    }

    public static void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static boolean compareArray(int[] A){
        for(int i = 0; i < A.length; i++){
            if(A[i] != B[i]){
                return false;
            }
        }
        flag = 1;
        return true;
    }
}