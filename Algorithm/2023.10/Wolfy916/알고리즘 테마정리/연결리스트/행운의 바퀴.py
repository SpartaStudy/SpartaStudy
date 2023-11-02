'''
행운의 바퀴 - 백준(실버4)
분류 : 자료구조(연결리스트), 그리디..?
'''
import sys

# [A] 입력 함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [main]
if __name__ == '__main__':
    N, K = map(int, input().split())
    order = [list(input().split()) for _ in range(K)]
    
    circle = ['?'] * N
    p = 0
    for a, b in order:
        for _ in range(int(a)):
            p += 1
            p %= N
        if circle[p] == '?':
            if b in circle:
                print('!')
                exit()
            circle[p] = b
        elif circle[p] != b:
            print('!')
            exit()
    
    for i in range(p, -1, -1):
        print(circle[i], end='')
    for i in range(N - 1, p, -1):
        print(circle[i], end='')