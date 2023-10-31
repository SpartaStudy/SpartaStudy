'''
너구리 구구 - 백준18126(실버2)
분류 : 그래프 탐색
'''
import sys

# [A] 입력 함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 너비 우선 탐색
def BFS():
    cost = [0] * (N+1)         # 거리 기록
    visited = [False] * (N+1)  # 방문 처리
    visited[1] = True
    q = [1]
    while q:
        v = q.pop(0)
        for w, d in graph[v]:
            if visited[w]: continue
            if cost[w] >= cost[v] + d: continue
            visited[w] = True
            cost[w] = cost[v] + d
            q.append(w)
    return max(cost)

# [main]
if __name__ == '__main__':
    N = int(input())
    graph = [[] for _ in range(N+1)]
    for _ in range(N-1):
        a, b, c = map(int, input().split())
        graph[a].append((b, c))  # 양방향 간선
        graph[b].append((a, c))  #

    print(BFS())