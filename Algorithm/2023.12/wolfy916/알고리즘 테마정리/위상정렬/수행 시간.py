'''
수행 시간 - 백준 골드4
분류 : 위상정렬, DP
'''

import sys

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 전송시간 계산함수
def cal(i, j):
    return pow(i - j, 2)

# [Main]
if __name__ == "__main__":
    # [1] 데이터 입력
    N = int(input())
    
    # [2] (key, value) = (계급, [(번호, 동작시간)...]) 형태의 dict 생성
    computers = dict()
    for i in range(1, N + 1):
        c, t = map(int, input().split())
        if computers.get(c):
            computers[c].append([i, t, 0])
        else:
            computers[c] = [[i, t, 0]]

    # [3] dp..?
    level = 2
    max_level = max(computers.keys())
    while level <= max_level:
        lenV = len(computers[level])
        for idx in range(lenV):
            j, t2, maxt2 = computers[level][idx]
            for i, t1, maxt1 in computers[level - 1]:
                computers[level][idx][2] = max(computers[level][idx][2], t1 + maxt1 + cal(i, j))
        level += 1

    # [4] 정답 계산
    answer = 0
    for num, t, maxt in computers[max_level]:
        answer = max(answer, t + maxt)

    print(answer)