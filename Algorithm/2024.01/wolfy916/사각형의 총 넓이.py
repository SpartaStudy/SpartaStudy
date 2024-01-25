'''
사각형의 총 넓이 - 코드트리 골드3
분류 : 스위핑
'''
import sys

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 스위핑 리스트 생성 함수
def gen_swip_arr(N):
    arr = []
    for _ in range(N):
        x1, y1, x2, y2 = map(int, input().split())
        y1 += 10000
        y2 += 10000
        arr.append((x1, y2, y1, 1))
        arr.append((x2, y2, y1, -1))
    arr.sort()
    return arr

# [C] 카운트 함수
def swip_cnt(arr):
    cnt = 0
    for i in range(len(arr)):
        if arr[i] < 1: continue
        cnt += 1
    return cnt

if __name__ == "__main__":
    # [1] 데이터 입력
    N = int(input())

    # [2] 스위핑 리스트 생성
    swip_arr = gen_swip_arr(N)

    # [3] y좌표 기준으로 스위핑 탐색
    answer = 0
    y_arr = [0] * 20001
    for i in range(len(swip_arr) - 1):
        x, y1, y2, d = swip_arr[i]
        for y in range(y1, y2):
            y_arr[y] += d
        answer += (swip_arr[i + 1][0] - x) * swip_cnt(y_arr)
    
    print(answer)
