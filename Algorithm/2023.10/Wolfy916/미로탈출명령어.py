"""
미로 탈출 명령어 - 프로그래머스
알고리즘 분류: dfs + 그리디?
1. 2차원 배열을 사전순(d, l, r, u)으로 이동하며 탐색
2. impossible 판별 조건 설정
"""

import sys
sys.setrecursionlimit(2506)

def solution(n, m, x, y, r, c, k):
    
    def dfs(vi, vj, step, path):
        dis = abs(vi - r) + abs(vj - c)  # 남은 거리
        rem_step = k - step              # 남은 이동횟수

        # impossible 판별1
        # 남은 거리와 남은 이동횟수의 홀짝이 일치해야함
        # 홀짝이 일치할 경우 두 값의 차이값이 2의 배수로 고정됨
        # 2의 배수는 임의의 칸으로 1번 이동했다가 다시 돌아오는 것으로 이동횟수 소모 가능
        if dis % 2 != rem_step % 2: return 'impossible'

        # impossible 판별2
        # 남은 거리가 남은 이동횟수보다 크다면 못감
        if dis > rem_step: return 'impossible'

        if step == k:
            if (vi, vj) == (r, c):
                return ''.join(path)
        else:
            for i in range(4):
                ni, nj = vi + delta[i][0], vj + delta[i][1]
                if ni < 0 or nj < 0 or ni >= n or nj >= m: continue
                ret = dfs(ni, nj, step + 1, path + [delta_str[i]])
                if ret != 'impossible': return ret
        
        return 'impossible'
    
    delta = [(1, 0), (0, -1), (0, 1), (-1, 0)]  # 사전순
    delta_str = ['d', 'l', 'r', 'u']            #
    x -= 1; y -= 1; r -= 1; c -= 1;
    
    return dfs(x, y, 0, [])