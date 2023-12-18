'''
효율적인 해킹 - 백준 1325 (실버1)
분류 : BFS
'''
import sys
from collections import deque as dq

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] bfs
def bfs(start):
    cnt = 1
    visited = [False] * (N + 1)
    visited[start] = True
    q = dq([start])
    while q:
        v = q.popleft()
        for w in graph[v]:
            if visited[w]: continue
            visited[w] = True
            cnt += 1
            q.append(w)
    return cnt

# [Main]
if __name__ == '__main__':
    N, M = map(int, input().split())
    graph = [[] for _ in range(N + 1)]
    for _ in range(M):
        a, b = map(int, input().split())
        graph[b].append(a)

    maxV = 1
    answer = []

    for i in range(1, N + 1):
        cnt = bfs(i)
        if cnt > maxV:
            maxV = cnt
            answer = [i]
        elif cnt == maxV:
            answer.append(i)

    print(*answer)