'''
블록 이동하기 - 프로그래머스(2020 KAKAO BLIND)
분류 : BFS, 브루트-포스
'''

from collections import deque as dq

def solution(board):

    # [A] 인덱스 유효성 & 벽인지 체크하는 함수
    def valid_check(vi, vj, N):
        if vi < 0 or vj < 0 or vi >= N or vj >= N: return True
        if board[vi][vj] == 1: return True
        return False

    N = len(board)
    delta1 = [(-1, 0), (1, 0), (0, -1), (0, 1)]  # 상하좌우 이동
    delta2 = [((-1, 0), (1, 0)), ((0, -1), (0, 1))]  # 회전 이동
    visited = set()
    visited.add((0, 0, 0, 1))
    q = dq([(0, 0, 0, 0, 1)])
    while q:
        cnt, li, lj, ri, rj = q.popleft()
        if (li, lj) == (N-1, N-1) or (ri, rj) == (N-1, N-1):
            answer = cnt
            break
        # 상하좌우 이동
        for di, dj in delta1:
            nli, nlj = li + di, lj + dj
            if valid_check(nli, nlj, N): continue
            nri, nrj = ri + di, rj + dj
            if valid_check(nri, nrj, N): continue
            if (nli, nlj, nri, nrj) in visited: continue
            visited.add((nli, nlj, nri, nrj))
            q.append((cnt + 1, nli, nlj, nri, nrj))
        # 회전 이동
        k = abs(li - ri)  # 0: 가로 배치, 1: 세로 배치
        for di, dj in delta2[k]:
            nli, nlj = li + di, lj + dj
            if valid_check(nli, nlj, N): continue
            nri, nrj = ri + di, rj + dj
            if valid_check(nri, nrj, N): continue
            if (nri, nrj, ri, rj) not in visited:
                visited.add((nri, nrj, ri, rj))
                q.append((cnt + 1, nri, nrj, ri, rj))
            if (li, lj, nli, nlj) not in visited:
                visited.add((li, lj, nli, nlj))
                q.append((cnt + 1, li, lj, nli, nlj))
    
    return answer

board = [[0, 0, 0, 1, 1],[0, 0, 0, 1, 0],[0, 1, 0, 1, 1],[1, 1, 0, 0, 1],[0, 0, 0, 0, 0]]
print(solution(board))