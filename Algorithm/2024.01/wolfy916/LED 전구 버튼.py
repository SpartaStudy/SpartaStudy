'''
LED 전구 버튼 - 코드트리 골드4
분류 : 시뮬레이션
'''

# [A] 로직 함수
def solution(N, B, bulbs):
    visited = [bulbs[:]]  # 반복 구간 탐지를 위한 기록용 리스트
    start = end = 0       # 반복 구간의 시작과 끝 인덱스를 기록할 변수
    loop_cnt = B          # 남은 스위치 사용 횟수
    for i in range(1, B + 1):
        # [1] 시뮬레이션
        bulbs = [0] * N
        bulbs[0] = (visited[-1][0] + visited[-1][-1]) % 2
        for j in range(1, N):
            bulbs[j] = (visited[-1][j] + visited[-1][j - 1]) % 2
        # [2] 반복 구간 발견
        if bulbs in visited:
            start, end = visited.index(bulbs), i
            loop_cnt -= i
            break
        # [3] 기록
        visited.append(bulbs[:])
    else:
        # [4-1] 반복 구간이 없을때
        return visited[-1]
    
    # [4-2] 반복 구간이 있을때
    loop_lenV = end - start
    idx = loop_cnt % loop_lenV + start
    return visited[idx]

# [Main]
if __name__ == "__main__":
    N, B = map(int, input().split())
    bulbs = [int(input()) for _ in range(N)]
    print("\n".join(map(str, solution(N, B, bulbs))))