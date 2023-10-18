'''
제목 : 알고리즘 수업 - 선택 정렬 3[백준 23883]
분류 : 구현, 정렬(선택 정렬)
1. 문제 내용의 수도 코드 사용
2. dictionary 자료 구조로 인덱스를 찾는 로직을 최적화
'''
import sys

# [A] 입력 함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 선택 정렬
def select_sort():
    global cnt
    idx = N
    for key in sorted(idx_dict.keys(), reverse=True):
        idx -= 1
        if idx_dict[key] < idx:
            cnt += 1
            if cnt == K:
                print(*sorted([arr[idx_dict[key]], arr[idx]]))
                exit()
            arr[idx], arr[idx_dict[key]] = arr[idx_dict[key]], arr[idx]
            idx_dict[arr[idx_dict[key]]], idx_dict[arr[idx]] =  idx_dict[arr[idx]], idx_dict[arr[idx_dict[key]]]

# [main]
if __name__ == '__main__':
    N, K = map(int, input().split())
    arr = list(map(int, input().split()))
    idx_dict = {arr[i]: i for i in range(N)}
    cnt = 0
    select_sort()
    print(-1)