import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        boolean flag = true;
        int pCnt = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'P') {
                pCnt++;
            } else {
                if (pCnt >= 2 && i < str.length() - 1 && str.charAt(i + 1) == 'P') {
                    pCnt--;
                    i++;
                } else {
                    flag = false;
                    break;
                }
            }
        }

        if (pCnt > 1) {
            flag = false;
        }

        System.out.println(flag ? "PPAP" : "NP");
    }

}