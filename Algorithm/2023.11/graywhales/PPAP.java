import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PPAP {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        //PPPAPAPPAP  A가 나올때 pCnt는 무조건 2이상이여야하고 끝난다음 pCnt=1이어야함.
        int pCnt=0;
        boolean flag=true;
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='P') pCnt++;
            else {
                if(pCnt>=2){
                    if(i<input.length()-1&&input.charAt(++i)=='P'){
                        pCnt--;
                    }else{
                        flag=false;
                        break;
                    }
                }else {
                    flag=false;
                    break;
                }
            }
        }
        if(pCnt!=1) flag=false;
        System.out.println(flag?"PPAP":"NP");
    }
}
