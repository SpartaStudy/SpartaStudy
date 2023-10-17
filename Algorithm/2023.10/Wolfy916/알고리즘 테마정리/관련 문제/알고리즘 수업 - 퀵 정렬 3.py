'''
알고리즘 수업 - 퀵 정렬 3[백준 24092]
분류 : 구현, 정렬
'''
import sys

# [A] 입력 함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 두 리스트를 체크하는 함수
def check(arr1, arr2):
    if arr1 == arr2:
        print(1)
        exit()

# [C] 파티셔닝
def partition(arr, l, r):
    x = arr[r]
    i = l - 1
    for j in (l, r):
        if arr[j] <= x:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
            check(arr, comp)
    if i + 1 != r:
        arr[i+1], arr[r] = arr[r], arr[i+1]
        check(arr, comp)
    return i + 1

# [D] 퀵 정렬
def quick_sort(arr):
    l, r = 0, len(arr) - 1
    if l < r:
        p = partition(arr, l, r)
        quick_sort(arr[l:p])
        quick_sort(arr[p+1:r+1])

# [main]
if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, input().split()))
    comp = list(map(int, input().split()))
    quick_sort(arr)
    print(0)