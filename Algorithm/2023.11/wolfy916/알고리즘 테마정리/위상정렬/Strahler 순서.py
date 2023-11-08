'''
Strahler 순서 - 백준(골드3)
분류 : 위상 정렬, 방향 비순환 그래프
'''
import sys
from collections import deque as dq

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [main]
if __name__=="__main__":
    T = int(input())
    for _ in range(T):
        # 테케 번호, 노드수, 간선수
        K, M, P = map(int, input().split())
        graph = [[] for _ in range(M + 1)]
        in_degree = [[] for _ in range(M + 1)]
        for _ in range(P):
            A, B = map(int, input().split())
            graph[A].append(B)      # 간선 정보 기록
            in_degree[B].append(A)  # 간선 정보 역방향 기록

        # 진입차수가 0인 노드들을 큐에 담는다.
        queue = dq([])
        strahler = [0] * (M + 1)
        for i in range(1, M + 1):
            if in_degree[i]: continue
            strahler[i] = 1  # 강의 근원
            queue.append(i)

        # bfs 방식
        while queue:
            v = queue.popleft()
            for w in graph[v]:
                # w 노드로 물을 흘려보내는 노드들의 가장 큰 strahler 순서 값
                maxV = max(map(lambda x: strahler[x], in_degree[w]))
                # maxV count
                cnt = sum(map(lambda x: 1 if strahler[x] == maxV else 0, in_degree[w]))
                # strahler 순서값 정하기
                strahler[w] = maxV + 1 if cnt > 1 else maxV
                queue.append(w)

        print(K, max(strahler))