import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
/*
     큐
     대기는 큐로 관리, 주차장단위요금, 차량무게, 주차위치는 배열로 관리
     주차자리는 불린배열
 */
public class 주차장 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[] Rs = new int[n];
        int[] Wk = new int[m+1];


        Queue<Integer> q = new ArrayDeque<>();

        for(int i=0;i<n;i++){
            Rs[i]=sc.nextInt();
        }
        for(int i=1;i<=m;i++){
            Wk[i]=sc.nextInt();
        }

        int count = 0;
        int answer = 0;

        boolean[] pl = new boolean[n];
        int[] plNums = new int[m+1];

        for(int i=0;i<2*m;i++){
            int car = sc.nextInt();

            if(car>0){
                q.offer(car);
            }else{
                int plNum=plNums[-car];
                pl[plNum]=false;
                count--;
                answer+=Wk[-car]*Rs[plNum];
            }

            if(count<n&&!q.isEmpty()){
                for(int j=0;j<n;j++){
                    if(!pl[j]){
                        plNums[q.poll()]=j;
                        pl[j]=true;
                        count++;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);

    }
}
