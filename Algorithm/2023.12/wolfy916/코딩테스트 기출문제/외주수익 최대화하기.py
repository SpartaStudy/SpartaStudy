'''
외주수익 최대화하기 - 코드트리 실버3
분류 : dfs
'''

def dfs(cur, value):
    global maxV
    if cur > N: return
    elif cur == N:
        maxV = max(maxV, value)
        return
    else:
        dfs(cur + 1, value)
        dfs(cur + tasks[cur][0], value + tasks[cur][1])


N = int(input())
tasks = [tuple(map(int, input().split())) for _ in range(N)]
maxV = 0
dfs(0, 0)
print(maxV)