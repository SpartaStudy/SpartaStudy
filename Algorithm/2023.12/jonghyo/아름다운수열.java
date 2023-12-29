import java.io.*;
import java.util.*;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int answer = 0;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int arrA[] = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		int arrB[] = new int[M];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			arrB[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arrB);
		for(int i = 0; i < N-M+1; i++) {
			int tmp[] = copyArr(i, M, arrA);
			if(check(tmp, arrB, M)) {
				answer++;
				sb.append(i+1).append("\n");
			}
		}
		System.out.println(answer);
		System.out.println(sb);
		
	}
	
	//배열을 복사해준다.
	private static int[] copyArr(int i, int m, int[] arrA) {
		int tmp [] = new int[m];
		int cnt = 0;
		for(int a = i; a < i + m; a++) {
			tmp[cnt++] = arrA[a];
		}
		Arrays.sort(tmp);
		return tmp;
	}

	private static boolean check(int[] tmp, int[] arrB, int M) {
		//비교할 수 
		int num = 0;
		//몇개가 같은지 비교.
		int cnt = 0;
		for(int i = 0; i < M; i++) {
			//처음 시작하는 부분이라면?
			if(i == 0) {
				//두개의 수가 다르다면?
				if(tmp[i] != arrB[i]) {
					num = tmp[i] - arrB[i];
				}
				cnt++;
			}else {
				//이후로 만약 더한것과 다르면 볼 필요가 없어짐.
				if(tmp[i] == arrB[i] + num) {
					cnt++;
				}else {
					break;
				}
			}
		}
		if(cnt == M) {
			return true;
		}
		return false;
	}
}