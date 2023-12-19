'''
징검다리 - 백준 11561, 실버3
분류 : 이진 탐색
'''
import sys

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 1부터 n까지의 합
def Gauss_sum(n):
    return (1 + n) * n // 2

# [C] 이진 탐색 중간값 유효성 검사 함수
def check(N, mid):
    return True if N >= Gauss_sum(mid) else False

# [D] 이진탐색 함수
def binary_search(N):
    answer = 0
    left, right = 1, 10**16
    while left <= right:
        mid = (left + right) // 2
        if check(N, mid):
            left = mid + 1
            answer = mid
        else:
            right = mid - 1
    return answer

# [main]
if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        N = int(input())
        print(binary_search(N))