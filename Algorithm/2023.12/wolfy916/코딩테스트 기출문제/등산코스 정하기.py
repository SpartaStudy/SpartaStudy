'''
등산코스 정하기 - 프로그래머스 Lv. 3(2022 KAKAO TECH INTERNSHIP)
분류 : heapq + bfs = dijkstra
'''

from heapq import heappush, heappop

def solution(n, paths, gates, summits):
    # [1] 양방향 간선 기록
    adjL = [[] * (n + 1) for _ in range(n + 1)]
    for a, b, c in paths:
        adjL[a].append((b, c))
        adjL[b].append((a, c))
    
    # [2] 출입구를 시작으로 heapq 초기화
    hq = []
    visited = [float('inf')] * (n + 1)
    for gate in gates:
        hq.append((0, gate))
        visited[gate] = 0
    
    # [4] heapq + bfs
    answer = [n + 1, float('inf')]
    summit_dict = {s: True for s in summits}
    while hq:
        intensity, v = heappop(hq)
        if intensity > answer[1]: break
        if summit_dict.get(v):
            if answer[1] >= intensity:
                answer[0] = min(answer[0], v)
                answer[1] = intensity
            continue
        for w, c in adjL[v]:
            update = max(intensity, c)
            if visited[w] <= update: continue
            visited[w] = update
            heappush(hq, (update, w))
    
    return answer