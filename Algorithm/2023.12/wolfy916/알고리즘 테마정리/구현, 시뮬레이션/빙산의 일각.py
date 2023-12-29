'''
빙산의 일각 - 코드트리 실버 1
분류: 정렬, 시뮬레이션
'''
import sys

# [A] 입력함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [Main]
if __name__ == "__main__":
    # [1] 데이터 입력
    N = int(input())
    heights = [0] + [int(input()) for _ in range(N)] + [0]

    # [2] (key, value) = (높이, 높이에 해당하는 인덱스들) 형태의 dict 생성
    ref = dict()
    for i in range(1, N + 1):
        if ref.get(heights[i]):
            ref[heights[i]].append(i)
        else:
            ref[heights[i]] = [i]

    # [3] 가장 높은 높이들부터 방문하며 빙산 카운트
    keys = sorted(list(ref.keys()), reverse=True)
    checked = [False] * (N + 2)
    answer = cnt = 0
    for key in keys:
        for value in ref[key]:
            checked[value] = True
            if checked[value - 1] and checked[value + 1]:
                cnt -= 1
            elif not checked[value - 1] and not checked[value + 1]:
                cnt += 1
        answer = max(answer, cnt)
    
    print(answer)