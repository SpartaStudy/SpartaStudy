'''
카드 짝 맞추기 - 프로그래머스 Lv. 3(2021 KAKAO BLIND)
분류 : DFS, BFS, 구현
'''

from collections import deque as dq

# [A] 카드 좌표 리스트를 리턴하는 함수
def search_card(board, N, M):
    cards = []
    for i in range(N):
        for j in range(M):
            if board[i][j] == 0: continue
            cards.append((i, j))
    return cards

# [B] S 지점에서 E 지점으로 이동하는 최소 조작 횟수를 리턴하는 함수
def bfs(board, N, M, si, sj, ei, ej, log):
    delta = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    q = dq([(si, sj)])
    visited = [[100] * M for _ in range(N)]
    visited[si][sj] = 0
    found_cards = []
    found_cards_cnt = []
    while q:
        vi, vj = q.popleft()
        # 카드 짝이 필요한 경우
        # -> 해당 짝을 찾을때까지 반복
        if len(log) % 2:
            if (vi, vj) == (ei, ej):
                return visited[vi][vj] + 1
        # 새로운 카드가 필요한 경우
        # -> si, sj에서 최소 조작으로 갈 수 있는 카드를 모두 탐색
        else:
            if board[vi][vj] and (vi, vj) not in log:
                for f_card in found_cards:
                    i, j = f_card
                    if board[vi][vj] == board[i][j]:
                        break
                else:
                    found_cards.append((vi, vj))
                    found_cards_cnt.append(visited[vi][vj] + 1)
        for di, dj in delta:
            # 방향키
            ni, nj = vi + di, vj + dj
            if ni < 0 or nj < 0 or ni >= N or nj >= M: continue
            if visited[vi][vj] + 1 < visited[ni][nj]:
                visited[ni][nj] = visited[vi][vj] + 1
                q.append((ni, nj))
            # ctrl + 방향키
            ti, tj = ni, nj
            while True:
                if ni < 0 or nj < 0 or ni >= N or nj >= M:
                    ni -= di; nj -= dj
                    break
                if board[ni][nj] and (ni, nj) not in log: break
                ni += di; nj += dj
            if (ti, tj) == (ni, nj): continue
            if visited[vi][vj] + 1 < visited[ni][nj]:
                visited[ni][nj] = visited[vi][vj] + 1
                q.append((ni, nj))
    return found_cards, found_cards_cnt

# [C] dfs 탐색 함수
def dfs(board, vi, vj, N, M, cards, log, cnt):
    global answer
    # 조작횟수가 갱신된 answer이상이면 탐색할 필요 없음
    if cnt >= answer: return
    # answer값 최소값으로 갱신
    if len(cards) == len(log):
        answer = min(answer, cnt)
        return
    # 카드 짝이 필요할 때
    if len(log) % 2:
        for ei, ej in cards:
            if (vi, vj) == (ei, ej): continue
            if board[vi][vj] == board[ei][ej]:
                break
        add = bfs(board, N, M, vi, vj, ei, ej, log)
        dfs(
            board, ei, ej, N, M,
            cards, log + [(ei, ej)],
            cnt + add
        )
    # 새로운 카드가 필요할 때
    else:
        f_cards, f_cards_cnt = bfs(board, N, M, vi, vj, 0, 0, log)
        for card, add in zip(f_cards, f_cards_cnt):
            si, sj = card
            dfs(
                board, si, sj, N, M,
                cards, log + [(si, sj)],
                cnt + add
            )

# [D] 메인 함수
def solution(board, r, c):
    global answer
    answer = 100
    N, M = len(board), len(board[0])
    cards = search_card(board, N, M)
    dfs(board, r, c, N, M, cards, [], 0)
    return answer

board = [[1,0,0,3],[2,0,0,0],[0,0,0,2],[3,0,1,0]]
r = 1
c = 0
print(solution(board, r, c))