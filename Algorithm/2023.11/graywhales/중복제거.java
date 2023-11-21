import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//2^25 => 3200만
//2^20 => 100만
//boolean 1바이트 > 8비트 총 boolean 배열 사용보다 1/8 절약가능
//메모리 사용을 줄이기 위해 int형이 32비트이기에 bit를 이용
//몫을 배열의 index로 사용하고 나머지를 masking 할 값으로 사용한다.
//최대 2^25을 비트화 시켜 set으로 사용하는 방법
//java.util.BitSet 을 사용해도 동일한 작동을 함

public class 중복제거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splits = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        int[] set = new int[(1<<20)+10];
        for (String str : splits) {
            int a = Integer.parseInt(str);
            int idx = a / 32;
            int mask = a % 32;
            if((set[idx] & (1 << mask)) != 0) continue;

            sb.append(a+" ");
            set[idx] |= (1 << mask);
        }
        System.out.println(sb);
    }
}
