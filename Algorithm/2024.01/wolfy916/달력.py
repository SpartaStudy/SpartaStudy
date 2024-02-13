'''
달력 - 백준 골드 5
분류 : 그리디
'''
import sys

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [Main]
if __name__ == "__main__":
    # [1] 데이터 입력
    N = int(input())
    schedule = [tuple(map(int, input().split())) for _ in range(N)]

    # [2] 달력 생성 및 일정 표시
    calendar = [0] * 366
    for start, end in schedule:
        for day in range(start, end + 1):
            calendar[day] += 1

    # [3] 넓이 계산
    answer = 0
    w = h = 0
    for day in range(1, 366):
        if calendar[day] > 0:
            w += 1
            h = max(h, calendar[day])
        else:
            answer += w * h
            w = h = 0

    if w > 0:
        answer += w * h

    print(answer)