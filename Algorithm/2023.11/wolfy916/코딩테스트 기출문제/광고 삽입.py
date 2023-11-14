'''
광고 삽입 - 프로그래머스 Lv. 3(2021 KAKAO BLIND)
분류 : 누적합, 구간합
'''

def solution(play_time, adv_time, logs):
    # [1] 동영상 총 재생시간과 광고시간을 초단위로 변환
    change = lambda x: int(x[0:2]) * 3600 + int(x[3:5]) * 60 + int(x[6:8])
    total = change(play_time)
    adv = change(adv_time)
    
    # [2] 동영상 시작시각과 종료시각 기록
    table = [0] * (total + 1)
    for log in logs:  # log = "HH:MM:SS-HH:MM:SS"
        start, end = change(log[:8]), change(log[9:])
        table[start] += 1
        table[end] -= 1
    
    # [3] 누적합 -> table[sec] = 해당 시각에 재생중인 동영상의 갯수
    for sec in range(1, total + 1):
        table[sec] += table[sec - 1]
    
    # [4] 구간합을 위한 누적합
    for sec in range(1, total + 1):
        table[sec] += table[sec - 1]
    
    # [5] 공익광고 길이만큼 윈도우 슬라이딩
    #     동영상 누적 가중치가 가장 높은 구간 탐색
    table = [0] + table
    maxV = answer = 0
    for sec in range(adv, total + 2):
        tmp = table[sec] - table[sec - adv]
        if maxV < tmp:
            maxV = tmp
            answer = sec - adv  # 공익광고 시작시각 기록
    
    # [6] 출력
    h, t = divmod(answer, 3600)
    m, s = divmod(t, 60)
    return f"{h:02d}:{m:02d}:{s:02d}"