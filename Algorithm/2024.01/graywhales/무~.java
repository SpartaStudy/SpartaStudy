import java.util.Scanner;
/*
분할정복
지훈이형 코드보고 다시 해보기...
*/
public class 무 {
    static char ans;
    static int N;
    static int[] S = new int[28];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S[0]=3;
        dnc(26);

        L:while(true){
            for(int i=26;i>=0;i--){
                if(N>S[i]){
                    N-=S[i];
                    if(N==1){
                        ans='m';
                        break L;
                    }else if(N<=i+4){
                        ans='o';
                        break L;
                    }else{
                        N-=i+4;
                    }
                }
                if(i==0){
                    if(N==1){
                        ans='m';
                        break L;
                    }
                    if(N<=3){
                        ans='o';
                        break L;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    static int dnc(int n){
        if(n==0) return S[n];
        if(S[n]==0){
            S[n]=dnc(n-1)+(n+3)+dnc(n-1);
        }
        return S[n];
    }

}
