'''
코딩 테스트 공부 - 프로그래머스(2022 KAKAO TECH INTERNSHIP)
분류 : DP
[1] - DP 해시, 우선 순위 큐 풀이
[2] - DP 배열 풀이
'''

# [1]
from heapq import heappush, heappop

def solution(alp, cop, problems):

    def insert(value, key):
        if key not in dp or value < dp[key]:
            dp[key] = value
            heappush(q, (value, key))

    problems += [[0, 0, 1, 0, 1], [0, 0, 0, 1, 1]]
    dp = {(alp, cop): 0}
    alp_max = max(map(lambda x: x[0], problems))
    cop_max = max(map(lambda x: x[1], problems))
    q = [(0, (alp, cop))]
    while q[0][1][0] < alp_max or q[0][1][1] < cop_max:
        c, (al, co) = heappop(q)
        for al_rq, co_rq, al_rd, co_rd, cost in problems:
            if al >= al_rq and co >= co_rq:
                insert(c + cost, (min(al + al_rd, 150), min(co + co_rd, 150)))

    return q[0][0]

# [2]
def solution(alp, cop, problems):
    max_alp = max(x[0] for x in problems)
    max_cop = max(x[1] for x in problems)

    alp = min(alp, max_alp)
    cop = min(cop, max_cop)
    INF = float('inf')
    dp = [[INF] * (max_cop + 1) for _ in range(max_alp + 1)]
    dp[alp][cop] = 0
    
    for i in range(alp, max_alp + 1):
        for j in range(cop, max_cop + 1):
            if i + 1 <= max_alp:
                dp[i + 1][j] = min(dp[i + 1][j], dp[i][j] + 1)
            if j + 1 <= max_cop:
                dp[i][j + 1] = min(dp[i][j + 1], dp[i][j] + 1)
            for al_rq, co_rq, al_rd, co_rd, cost in problems:
                if i >= al_rq and j >= co_rq:
                    nxt_al, nxt_co = min(max_alp, i + al_rd), min(max_cop, j + co_rd)
                    dp[nxt_al][nxt_co] = min(dp[nxt_al][nxt_co], dp[i][j] + cost)

    return dp[-1][-1]

alps = [10, 0, 10]
cops = [10, 0, 1]
problemss = [
    [[10,15,2,1,2],[20,20,3,3,4]],
    [[0,0,2,1,2],[4,5,3,1,2],[4,11,4,0,2],[10,4,0,4,2]],
     [[1, 1, 1, 1, 1], [5, 5, 1, 1, 3]],
]

if __name__ == '__main__':
    for i in range(3):
        print(solution(alps[i], cops[i], problemss[i]))