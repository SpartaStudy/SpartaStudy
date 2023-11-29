'''
등굣길 - 프로그래머스 Lv. 3
분류 : DP
'''

def solution(m, n, puddles):
    dp = [[0] * (m + 1)  for _ in range(n + 1)]
    dp[0][1] = 1
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if [j, i] in puddles: continue
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 
    return dp[-1][-1] % 1000000007

m, n, puddles = 4, 3, [[2, 2]]

if __name__ == '__main__':
    print(solution(m, n, puddles))