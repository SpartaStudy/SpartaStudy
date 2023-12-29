package codetree;

import java.io.*;
import java.util.*;

public class 연속되는수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int num[] = new int[N];
		//set을 사용해 입력에 나온 수만 뽑아내기.
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(br.readLine());
			set.add(num[i]);
		}
		//iterator를 사용해서 set안에있는 수만 뽑아내자.
		Iterator<Integer> iter = set.iterator();
		//최대 길이.
		int answer = 0;
		while(iter.hasNext()) {
			//몇번 반복되는지 체크
			int cnt = 0;
			//뺄 수
			int mnum = 0;
			mnum = iter.next();
			//비교할 숫자.
			int cmp = 0;
			for(int i = 0; i < N; i++) {
				//같은수면 패스
				if(num[i] == mnum)
					continue;
				//비교할 수가 0이라면? 즉 첫번째라면?
				if(cmp == 0) {
					//비교할 수를 지정해주고
					cmp = num[i];
					//횟수를 추가시켜준다음
					cnt++;
					//for문 처음부터
					continue;
				}
				//두개가 서로 다르면?
				if(cmp != num[i]) {
					//비교할 수를 바꾸고.
					cmp = num[i];
					//cnt를 1로 초기화 시킨다.
					cnt = 1;
				//두개가 서로 같다면?
				}else {
					//cnt만 추가시켜주면 된다.
					cnt++;
				}	
				//cnt와 answer중 큰값을 answer에 저장해 가장 긴 연속되는 길이를 찾는다.
				answer = Math.max(answer, cnt);
			}
		}
		System.out.println(answer);
	}
}
