'''
제목 : 셔틀버스 - 프로그래머스
분류 : 구현(형번환), 그리디(조건분기)
1. answer 조건
1-1. 탑승 인원이 부족하다면 answer = 버스도착시각
1-2. 탑승 인원이 충분하다면 answer = 마지막 탑승한 인원의 탑승시각 - 1
2. 매 버스 도착시각마다 조건에 맞는 answer를 갱신하며 시뮬레이션 동작
'''

# "HH:MM" => M 변환 함수
def trans1(t):
    h, m = t.split(':')
    if h[0] == '0': h = h[1]
    if m[0] == '0': m = m[1]
    return int(h) * 60 + int(m)

# M => "HH:MM" 변환 함수
def trans2(t):
    h, m = divmod(t, 60)
    h = '0' + str(h) if h < 10 else str(h)
    m = '0' + str(m) if m < 10 else str(m)
    return h + ':' + m

def solution(n, t, m, timetable):
    # timetable을 분단위 숫자형 자료로 변화 후 내림차순 정렬
    table = sorted(list(map(trans1, timetable)), reverse=True)
    time = 540 - t
    answer = 0
    for _ in range(n):
        time += t  # 버스 도착 시각ㅁ
        board = last = 0  # 탑승 인원과 마지막 탑승한 인원의 대기시각
        while table and table[-1] <= time and board < m:
            board += 1
            last = table.pop()
        answer = time if board < m else last - 1
    return trans2(answer)