'''
보석 상자 - 백준 2792, 실버1
분류 : 이진 탐색
'''
import sys, math

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 이진 탐색 중간값 유효성 검사 함수
def check(N, mid, jewels):
    cnt = 0
    for jewel in jewels:
        cnt += math.ceil(jewel / mid)
        if cnt > N: return False
    return True

# [C] 이진탐색 함수
def binary_search(N, M, jewels):
    answer = 0
    left, right = 1, 10**9
    while left <= right:
        mid = (left + right) // 2
        if check(N, M, mid, jewels):
            right = mid - 1
            answer = mid
        else:
            left = mid + 1
    return answer

# [main]
if __name__ == '__main__':
    N, M = map(int, input().split())
    jewels = [int(input()) for _ in range(M)]

    print(binary_search(N, M, jewels))