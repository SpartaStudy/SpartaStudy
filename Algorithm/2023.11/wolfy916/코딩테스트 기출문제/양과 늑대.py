'''
양과 늑대 - 프로그래머스 Lv. 3(2022 KAKAO BLIND)
분류 : 이진 트리, dfs
'''

def solution(info, edges):
    
    # [A] dfs + 백트랙킹
    def dfs(stat, cur, nxts):
        nonlocal answer
        answer = max(answer, stat[0])  # 최대값 갱신
        nxts.extend(adjL[cur])         # 다음 행선지 추가
        for i in range(len(nxts)):
            nxt = nxts[i]
            if visited[nxt]: continue
            stat[info[nxt]] += 1
            if stat[0] <= stat[1]:
                stat[info[nxt]] -= 1
                continue
            visited[nxt] = True
            dfs(stat, nxt, nxts[:i] + nxts[i+1:])
            stat[info[nxt]] -= 1
            visited[nxt] = False
    
    # [1] 단방향 간선 표기
    n = len(info)
    adjL = [[] for _ in range(n)]
    for a, b in edges:
        adjL[a].append(b)
    
    # [2] 중복 탐색 방지 및 dfs 실행
    visited = [False] * n
    visited[0] = True
    answer = 1
    dfs([1, 0], 0, [])

    return answer

infos = [[0,0,1,1,1,0,1,0,1,0,1,1], [0,1,0,1,1,0,1,0,0,1,0]]
edgess = [[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]], [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6],[3,7],[4,8],[6,9],[9,10]]]
for i in range(2):
    print(f'case {i+1}')
    print(solution(infos[i], edgess[i]))