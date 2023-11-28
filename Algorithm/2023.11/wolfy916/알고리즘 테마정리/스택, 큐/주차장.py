

def empty_spot():
    for i in range(1, N+1):
        if parking[i]: continue
        return i
    return 0

def out(car):
    for i in range(1, N+1):
        if parking[i] != car: continue
        parking[i] = 0
        return

N, M = map(int, input().split())
charges = [0] + [int(input()) for _ in range(N)]
weights = [0] + [int(input()) for _ in range(M)]
parking = [0] * (N+1)
space = N
waiting = []
answer = 0
for _ in range(2 * M):
    car = int(input())
    if car > 0:
        if space:
            park_num = empty_spot()
            parking[park_num] = car
            space -= 1
            answer += charges[park_num] * weights[car]
        else:
            waiting.append(car)
    else:
        car *= -1
        space += 1
        out(car)
        while waiting and space:
            car = waiting.pop(0)
            park_num = empty_spot()
            parking[park_num] = car
            space -= 1
            answer += charges[park_num] * weights[car]
print(answer)