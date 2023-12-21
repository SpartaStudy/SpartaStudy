import java.io.*;

public class Main {
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int num[] = new int[N];
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		carry(N, num, 0, 0, 0);
		System.out.println(answer);
	}

	
	
	private static void carry(int n, int[] num, int start, int sum, int count) {
		answer = Math.max(count , answer);
		for(int i = start; i < n; i++) {
			if(check(sum, num[i])) {
				carry(n,num, i+1, sum+num[i], count + 1);
			}
		}
	}

	//체크해줄 수(carry가 발생하는지 체크할 함수입니다.
	private static boolean check(int num1, int num2) {
		int div = 100000000;
		int a = 0;
		int b = 0;
		while(div != 0) {
			a = num1 / div;
			b = num2 / div;
			if(a + b > 9)
				return false;
			num1 %= div;
			num2 %= div;
			div /= 10;
		}
		return true;
	}
}
