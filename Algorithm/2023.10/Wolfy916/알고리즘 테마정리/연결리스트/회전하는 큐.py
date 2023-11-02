'''
회전하는 큐 - 백준(실버3)
분류 : 자료구조(연결리스트)
'''
import sys

# [A] 입력 함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 선택 원소 제거 및 연결
def pick(num):
    pre, nxt = queue[num]
    queue[pre][1] = nxt
    queue[nxt][0] = pre
    del queue[num]

# [C] 최소 연산 카운트
def count(cur, num):
    cnt1 = cnt2 = 1
    tmp1 = tmp2 = cur
    while queue[tmp1][0] != num:
        cnt1 += 1
        tmp1 = queue[tmp1][0]
    while queue[tmp2][1] != num:
        cnt2 += 1
        tmp2 = queue[tmp2][1]
    return min(cnt1, cnt2)

# [main]
if __name__ == '__main__':
    N, M = map(int, input().split())
    orders = list(map(int, input().split()))
    
    # 연결리스트 구현
    queue = {i: [i-1, i+1] for i in range(1, N+1)}
    queue[1][0] = N
    queue[N][1] = 1
    
    answer = 0
    p = 1  # 현재 위치 포인터
    for order in orders:
        if p != order:
            answer += count(p, order)
        p = queue[order][1]
        pick(order)

    print(answer)