'''
무~ - 코드트리 골드5
분류 : 분할 정복
'''

# [A] 분할 정복
def search(t, N):
    prev = S[t - 1] if t > 0 else 0
    # [A-1] 3개의 구간으로 분리
    if N <= prev:
        return search(t - 1, N)
    elif N <= prev + t + 3:
        return 'm' if N < prev + 2 else 'o'
    else:
        return search(t - 1, N - prev - t - 3)

# [Main]
if __name__ == "__main__":
    # [1] 데이터 입력
    N = int(input())

    # [2] 각 단계별 길이를 저장
    S = [3]
    while S[-1] < N:
        S.append(S[-1] * 2 + len(S) + 3)

    # [3] 각 단계를 분할하여 정답을 탐색
    print(search(len(S) - 1, N))