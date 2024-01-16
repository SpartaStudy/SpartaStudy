'''
격자 밟기 - 코드트리 골드5
분류 : 백트랙킹, 구현, 시뮬레이션
'''
import sys

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 백트랙킹 dfs 함수
def dfs(ai, aj, bi, bj):
    global answer, cnt
    # [1] 모든 칸 방문한 경우
    if cnt < 1:
        if (ai, aj) == (bi, bj):
            answer += 1
        return

    # [2] 이동 중 같은 칸에 방문한 경우
    if (ai, aj) == (bi, bj): return
    
    # [3] 이동 구현
    for dai, daj in delta:
        nai, naj = dai + ai, daj + aj
        if nai < 0 or naj < 0 or nai >= 5 or naj >= 5: continue
        if visited[nai][naj]: continue
        visited[nai][naj] = True
        cnt -= 1
        for dbi, dbj in delta:
            nbi, nbj = dbi + bi, dbj + bj
            if nbi < 0 or nbj < 0 or nbi >= 5 or nbj >= 5: continue
            if not visited[nbi][nbj]:
                visited[nbi][nbj] = True
                cnt -= 1
                dfs(nai, naj, nbi, nbj)
                visited[nbi][nbj] = False
                cnt += 1
            elif (nai, naj) == (nbi, nbj):
                dfs(nai, naj, nbi, nbj)
        visited[nai][naj] = False
        cnt += 1

# [Main]
if __name__ == "__main__":
    # [1] 데이터 입력
    K = int(input())
    positions = [tuple(map(int, input().split())) for _ in range(K)]

    # [2] 이동 가능한 칸의 갯수 및 visited 설정
    cnt = 23
    visited = [[False] * 5 for _ in range(5)]
    visited[0][0] = True
    visited[4][4] = True
    for i, j in positions:
        cnt -= 1
        visited[i-1][j-1] = True

    # [3] 시뮬레이션
    answer = 0
    delta = ((-1, 0), (1, 0), (0, -1), (0, 1))
    dfs(0, 0, 4, 4)

    # [4] 출력
    print(answer)