'''
선입 선출 스케줄링 - 프로그래머스 Lv. 3
분류 : 이분 탐색
'''

def solution(n, cores):
    if n <= len(cores): return n
    n -= len(cores)
    left, right = 1, max(cores) * n
    while left < right:
        mid = (left + right) // 2
        sumV = sum(map(lambda x: mid // x, cores))
        if sumV < n:
            left = mid + 1
        else:
            right = mid
    
    for core in cores:
        n -= (right - 1) // core
        
    for i in range(len(cores)):
        if right % cores[i] == 0:
            n -= 1
            if n < 1:
                return i + 1

n = 6
cores = [1, 2, 3]

if __name__ == '__main__':
    print(solution(n, cores))