'''
Carry 피하기 - 코드트리 실버3
분류 : 백트랙킹
'''
import sys

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] carry 여부 체크 함수
def check(num1, num2):
    div = 100000000
    while div > 0:
        value1 = num1 // div % 10
        value2 = num2 // div % 10
        if value1 + value2 > 9: return False
        div //= 10
    return True

# [C] 백트랙킹 함수
def dfs(depth, start, value):
    global answer
    if N - start + depth <= answer: return  # 왜 이생각을 못했지
    answer = max(answer, depth)
    for i in range(start, N):
        if check(value, nums[i]):
            dfs(depth + 1, i + 1, value + nums[i])

# [Main]
if __name__ == "__main__":
    N = int(input())
    nums = [int(input()) for _ in range(N)]
    answer = 0
    dfs(0, 0, 0)
    print(answer)