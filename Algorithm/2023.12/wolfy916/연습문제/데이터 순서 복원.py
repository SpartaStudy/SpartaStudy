'''
데이터 순서 복원 - 백준 골드3
분류 : 애드 혹, 위상 정렬
'''
import sys

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 로직함수
def solution():
    # [1] 우선 순위를 매김
    priority = [0] * (N + 1)
    for num in range(1, N + 1):
        a = arr_A.index(num)
        b = arr_B.index(num)
        c = arr_C.index(num)
        minV = min(a, b, c)
        priority[num] += a + b + c - minV
    
    # [2] 우선 순위에 대하여 정렬
    answer = list(range(1, N + 1))
    answer.sort(key=lambda x: priority[x])

    return answer

# [Main]
if __name__ == "__main__":
    # [1] 데이터 입력
    N = int(input())
    arr_A = list(map(int, input().split()))
    arr_B = list(map(int, input().split()))
    arr_C = list(map(int, input().split()))
    
    # [2] 정답 출력
    print(*solution())