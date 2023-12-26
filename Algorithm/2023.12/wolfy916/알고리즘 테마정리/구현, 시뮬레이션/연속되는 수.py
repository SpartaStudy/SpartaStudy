'''
연속되는 수 - 코드트리 실버4
분류 : 시뮬레이션
'''

N = int(input())
arr = [int(input()) for _ in range(N)]

nums = set(arr)
maxV = 1
for num in nums:
    prev = -1
    tmp = 1
    for x in arr:
        if x == num: continue
        if prev == x:
            tmp += 1
        else:
            maxV = max(maxV, tmp)
            tmp = 1
            prev = x
    else:
        maxV = max(maxV, tmp)

print(maxV)