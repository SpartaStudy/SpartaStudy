'''
산 모양 타일링 - 프로그래머스 Lv. 3(2024 KAKAO WINTER INTERNSHIP)
분류 : DP
'''
def solution(n, tops):
    dp = [[0] * 2 for _ in range(n)]
    
    if tops[0] < 1:
        dp[0] = [2, 1]
    else:
        dp[0] = [3, 1]
        
    for i in range(1, n):
        if tops[i] < 1:
            dp[i][0] = (dp[i - 1][0] * 2 + dp[i - 1][1]) % 10007
            dp[i][1] = (dp[i - 1][0]  + dp[i - 1][1]) % 10007
        else:
            dp[i][0] = (dp[i - 1][0] * 3 + dp[i - 1][1] * 2) % 10007
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 10007
    
    return sum(dp[n - 1]) % 10007