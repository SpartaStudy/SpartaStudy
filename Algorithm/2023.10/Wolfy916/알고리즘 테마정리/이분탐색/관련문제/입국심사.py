'''
제목: 입국심사 - 프로그래머스(레벨3)
분류: 이분탐색
1. 이분탐색을 통해 mid분내에 모든 심사가 완료되는지 체크
2. 최적해 탐색
'''

def solution(n, times):
    def check(m):
        nonlocal n
        cnt = 0
        for time in times:
            cnt += m // time
            if cnt >= n: return True
        return False
    
    answer = 0
    left, right = 1, times[-1] * n
    while left <= right:
        mid = (left + right) // 2
        if check(mid):
            answer = mid
            right = mid - 1
        else:
            left = mid + 1

    return answer