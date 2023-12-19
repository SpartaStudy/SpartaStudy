import java.util.Scanner;
/*
백트래킹
*/
public class Carry피하기 {
    static int n;
    static int[] arr;
    static int max = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        dfs(0,0,0);
        System.out.println(max);
    }
    static void dfs(int depth,int count,int sum){
        max = Math.max(max,count);
        for(int i=depth;i<n;i++){
            if(isCarry(sum,arr[i])) {
                dfs(i+1,count+1,sum+arr[i]);
            }
        }
    }
    private static boolean isCarry(int sum,int num){
        while(true){
            if(sum%10+num%10>=10) return false;
            sum/=10; num/=10;
            if(sum==0||num==0) break;
        }
        return true;
    }
}
