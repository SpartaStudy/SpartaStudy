'''
장난감 조립 - 백준 2637(골드2)
분류 : 위상 정렬, 방향 비순환 그래프
'''
import sys
from collections import deque as dq

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [main]
if __name__=="__main__":
    N = int(input())
    M = int(input())
    graph = [[] for _ in range(N + 1)]
    in_degree = [0] * (N + 1)
    for _ in range(M):
        X, Y, K = map(int, input().split())
        graph[X].append((Y, K))  # 간선 기록
        in_degree[Y] += 1        # 진입차수 기록

    # 각 부품의 필요한 갯수
    parts = [0] * (N + 1)
    parts[N] = 1

    # 중복 탐색 방지
    used = [False] * (N + 1)
    used[N] = True

    # 위상 정렬
    queue = dq([N])
    while queue:
        v = queue.popleft()
        for w, k in graph[v]:
            parts[w] += parts[v] * k
            in_degree[w] -= 1
        for i in range(1, N + 1):
            if in_degree[i] or used[i]: continue
            used[i] = True
            queue.append(i)
    
    # 결과 출력
    for i in range(1, N):
        if graph[i]: continue
        print(i, parts[i])