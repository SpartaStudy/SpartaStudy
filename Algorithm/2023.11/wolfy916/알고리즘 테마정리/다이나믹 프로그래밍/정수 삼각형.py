'''
정수 삼각형 - 프로그래머스 Lv. 3
분류 : DP
'''

# 재귀적 구조
def solution(triangle):
    if len(triangle) < 2:
        return triangle[0][0]
    for i in range(len(triangle[-1]) - 1):
        triangle[-2][i] += max(
            triangle[-1][i],
            triangle[-1][i + 1]
        )
    triangle.pop()  # 맨 아랫층 제거
    return solution(triangle)

triangle = [[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]

if __name__ == '__main__':
    print(solution(triangle))