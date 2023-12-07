'''
공 이동 시뮬레이션 - 프로그래머스 Lv. 3(월간 코드 챌린지)
분류 : ?
'''

def solution(n, m, x, y, queries):
    delta = ((0, 1), (0, -1), (1, 0), (-1, 0))
    visited = dict()
    answer = 0
    q = [(x, y, len(queries) - 1)]
    visited[(x, y)] = len(queries) - 1
    while q:
        vx, vy, qi = q.pop(0)
        if qi == -1:
            if visited[(vx, vy)] == -1:
                answer += 1
                continue
        for dx, dy in delta[queries[qi][0]]:
            if queries[qi][0] < 1:
                for ny in range(vy + 1, vy + dy + 1):
                    if ny >= m: break
                    if visited.get((vx, ny)):
                        if visited[(vx, ny)] <= qi: continue
                        visited[(vx, ny)] = qi
                        q.append((vx, ny, qi - 1))
                    else:
                        visited[(vx, ny)] = qi
                        q.append((vx, ny, qi - 1))
            elif queries[qi][0] < 2:
                for ny in range(vy - 1, vy + dy - 1, -1):
                    if ny < 0: break
                    if visited.get((vx, ny)):
                        if visited[(vx, ny)] <= qi: continue
                        visited[(vx, ny)] = qi
                        q.append((vx, ny, qi - 1))
                    else:
                        visited[(vx, ny)] = qi
                        q.append((vx, ny, qi - 1))
            elif queries[qi][0] < 3:
                for nx in range(vx + 1, vx + dx + 1):
                    if nx >= n: break
                    if visited.get((nx, vy)):
                        if visited[(nx, vy)] <= qi: continue
                        visited[(nx, vy)] = qi
                        q.append((nx, vy, qi - 1))
                    else:
                        visited[(nx, vy)] = qi
                        q.append((nx, vy, qi - 1))
            else:
                for nx in range(vx - 1, vx + dx - 1, -1):
                    if nx < 0: break
                    if visited.get((nx, vy)):
                        if visited[(nx, vy)] <= qi: continue
                        visited[(nx, vy)] = qi
                        q.append((nx, vy, qi - 1))
                    else:
                        visited[(nx, vy)] = qi
                        q.append((nx, vy, qi - 1))

    return answer

n = [2, 2]
m = [2, 5]
x = [0, 0]
y = [0, 1]
queriess = [
    [[2,1],[0,1],[1,1],[0,1],[2,1]],
    [[3,1],[2,2],[1,1],[2,3],[0,1],[2,1]],
]

if __name__ == '__main__':
    for i in range(2):
        print(solution(n[i], m[i], x[i], y[i], queriess[i]))