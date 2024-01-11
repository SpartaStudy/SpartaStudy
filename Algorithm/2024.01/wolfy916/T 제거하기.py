'''
T 제거하기 - 코드트리 골드5
분류 : 스택
'''
import sys

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [Main]
if __name__ == "__main__":
    S = input()
    T = input()

    N, M = len(S), len(T)
    stack = []
    p1, p2 = -1, M - 1
    for i in range(N):
        stack.append(S[i])
        p1 += 1
        flag = False
        while p1 >= 0 and p2 >= 0:
            if stack[p1] != T[p2]: break
            p1 -= 1
            p2 -= 1
            if p2 < 0: flag = True
        
        if flag:
            for _ in range(M):
                stack.pop()

        p1 = len(stack) - 1
        p2 = M - 1

    print("".join(stack))