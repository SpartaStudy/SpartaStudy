'''
아름다운 수열 - 코드트리 실버4
분류 : 정렬, 구현
'''

import sys

def input():
    return sys.stdin.readline().rstrip('\n')

if __name__ == "__main__":
    N = int(input())
    arr_A = [int(input()) for _ in range(N)]
    M = int(input())
    arr_B = [int(input()) for _ in range(M)]

    arr_B.sort()
    answer = []
    for i in range(N - M + 1):
        tmp_A = arr_A[i:i+M]
        tmp_A.sort()
        delta = arr_B[0] - tmp_A[0]
        is_matched = True

        for j in range(M):
            if arr_B[j] - tmp_A[j] == delta: continue
            is_matched = False
            break

        if is_matched:
            answer.append(i + 1)
    
    print(len(answer))
    for idx in answer:
        print(idx)