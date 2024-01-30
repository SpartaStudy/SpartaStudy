'''
농장 관리 - 백준 골드 5
분류 : bfs
'''
import sys
from collections import deque as dq

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] bfs 탐색
def bfs(si, sj):
    global answer
    if visited[si][sj]: return
    q = dq([(si, sj)])
    visited[si][sj] = True
    flag = True
    while q:
        vi, vj = q.popleft()
        for di, dj in delta:
            ni, nj = vi + di, vj + dj
            if 0 > ni or 0 > nj or N <= ni or M <= nj: continue
            if farm[ni][nj] > farm[si][sj]:
                flag = False
            elif farm[ni][nj] == farm[si][sj]:
                if visited[ni][nj]: continue
                visited[ni][nj] = True
                q.append((ni, nj))
    if flag: answer += 1

# [Main]
if __name__ == "__main__":
    # [1] 데이터 입력 및 초기화
    N, M = map(int, input().split())
    farm = [list(map(int, input().split())) for _ in range(N)]
    delta = ((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))
    visited = [[False] * M for _ in range(N)]

    # [2] bfs탐색
    answer = 0
    for i in range(N):
        for j in range(M):
            bfs(i, j)

    print(answer)