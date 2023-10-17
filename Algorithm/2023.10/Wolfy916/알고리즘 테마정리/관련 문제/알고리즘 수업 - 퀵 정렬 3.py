'''
제목 : 알고리즘 수업 - 퀵 정렬 3[백준 24092]
분류 : 구현, 정렬(퀵 정렬)
1. 문제 내용의 수도 코드를 사용
'''
import sys
sys.setrecursionlimit(100000)

# [A] 입력 함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 리스트 체크 함수
def check():
    global same
    if same == N:
        print(1)
        exit()

# [C] 배열 원소 위치 교환 함수
def change(i, j):
    global same
    tmp1 = 1 if arr[i] == comp[i] else 0
    tmp2 = 1 if arr[j] == comp[j] else 0
    same -= tmp1 + tmp2
    arr[i], arr[j] = arr[j], arr[i]
    if arr[i] == comp[i]: same += 1
    if arr[j] == comp[j]: same += 1
    check()

# [D] 파티셔닝
def partition(l, r):
    global same
    x = arr[r]
    i = l - 1
    for j in range(l, r):
        if arr[j] <= x:
            i += 1
            change(i, j)
    if i + 1 != r:
        change(i+1, r)
    return i + 1

# [E] 퀵 정렬
def quick_sort(l, r):
    if l < r:
        p = partition(l, r)
        quick_sort(l, p - 1)
        quick_sort(p + 1, r)

# [main]
if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, input().split()))
    comp = list(map(int, input().split()))

    same = 0
    for i in range(N):
        if arr[i] != comp[i]: continue
        same += 1

    check()
    quick_sort(0, N - 1)
    print(0)