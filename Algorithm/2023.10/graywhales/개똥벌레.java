import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 개똥벌레 {
    /*
        누적합 풀이
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n=Integer.parseInt(input.split(" ")[0]);
        int h=Integer.parseInt(input.split(" ")[1]);
        int[] arrA=new int[h];
        int[] arrB=new int[h];

        int height;
        for(int i=0;i<n;i++){
            height=Integer.parseInt(br.readLine());
            if(i%2==0){
                arrA[height-1]++;
            }else{
                arrB[h-height]++;
            }
        }
        for(int i=h-1;i>0;i--){
            arrA[i-1]+=arrA[i];
        }
        for(int i=1;i<h;i++){
            arrB[i]+=arrB[i-1];
        }
        int sum;
        int min=Integer.MAX_VALUE;
        int minCnt=0;
        for(int i=0;i<h;i++){
            sum=arrA[i]+arrB[i];
            if(min>sum){
                min=sum;
                minCnt=1;
            }else if(min==sum){
                minCnt++;
            }
        }
        System.out.printf("%d %d\n",min,minCnt);
    }
}
