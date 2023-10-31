'''
제목: 개똥벌레 - 백준(골드5)
분류: 이분탐색
1. 특정 높이로 날아갈 때 몇 개의 석순과 몇 개의 종유석을 부수는지 확인
2. 최소값 갱신 및 갯수 카운팅
'''
import sys

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 이분탐색
def check(arr, height):
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] <= height:
            left = mid + 1
        else:
            right = mid - 1
    return len(arr) - (right + 1)

# [main]
if __name__ == '__main__':
    N, H = map(int, input().split())
    bot, top = [], []  # 석순, 종유석
    for i in range(N // 2):
        bot.append(int(input()))
        top.append(int(input()))

    bot.sort()  # 오름차순 정렬
    top.sort()  #

    minV, cntV = N, 0
    for i in range(1, H + 1):
        # 특정 높이에서 비행할 때 부술 수 있는 석순과 종유석의 갯수
        bot_cnt = check(bot, i-1)
        top_cnt = check(top, H-i)
        tot = bot_cnt + top_cnt
        if minV == tot:
            cntV += 1
        elif minV > tot:
            minV = tot
            cntV = 1
    
    print(minV, cntV)