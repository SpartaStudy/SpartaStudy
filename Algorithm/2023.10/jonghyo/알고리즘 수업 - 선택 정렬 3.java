import java.io.*;
import java.util.*;

public class Main {
	static int A = 0;
	static int B = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int list[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		selection_sort(N, K, list);
		if(A == 0 && B == 0) {
			System.out.println(-1);
		}else {
			System.out.println(A + " " + B);			
		}
	}
	private static void selection_sort(int n, int k, int[] list) {
		int tmp = 0;
		int temp = 0;
		int cnt = 0;
		for(int i = n-1; i >= 1; i--) {
			tmp = i;
			for(int j = i - 1; j >= 0; j--) {
				if(list[j] > list[tmp])
					tmp = j;
			}
			if(i != tmp) {
				temp = list[i];
				list[i] = list[tmp];
				list[tmp] = temp;
				cnt++;
			}
			if(cnt == k) {
				A = list[tmp];
				B = list[i];
				return;
			}
		}
		
	}
}
