'''
나무박멸 - 코드트리 골드4
분류 : 구현, 시뮬레이션
'''
import sys

# [A] 입력 함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 나무 성장 및 번식
def growing_tree():
    breeding = []
    for i in range(N):
        for j in range(N):
            # 벽과 제초제 제외
            if area[i][j] < 1 or medicine[i][j] > 0: continue
            empty_space_cnt = 0  # 빈 공간 카운트
            tree_cnt = 0         # 인접 나무 수 카운트
            coords = []          # 인접 좌표 저장
            for di, dj in cross:
                ni, nj = i + di, j + dj
                if ni < 0 or nj < 0 or ni >= N or nj >= N: continue
                if medicine[ni][nj] > 0: continue
                # 인접 빈 공간의 경우
                if area[ni][nj] == 0:
                    empty_space_cnt += 1
                    coords.append((ni, nj))
                # 인접 나무의 경우
                elif area[ni][nj] > 0:
                    tree_cnt += 1
            area[i][j] += tree_cnt  # 나무 성장
            if empty_space_cnt < 1: continue
            for ni, nj in coords:
                breeding.append((ni, nj, area[i][j] // empty_space_cnt))  # 나무 번식 데이터
    # 나무 번식
    for ni, nj, b in breeding:
        area[ni][nj] += b

# [C] 제초제 유지 기간 감소
def reduce_medicine():
    for i in range(N):
        for j in range(N):
            if medicine[i][j] < 1: continue
            medicine[i][j] -= 1

# [D] 가장 많이 나무를 박멸할 수 있는 제초제 살포 좌표 탐색
def search_medicine_point():
    maxV = 0
    pi = pj = 0
    for i in range(N):
        for j in range(N):
            # 벽과 제초제 제외
            if area[i][j] < 1 or medicine[i][j] > 0: continue
            # 제초제 살포시 박멸할 수 있는 나무 수 카운트
            remove_tree_cnt = area[i][j]
            for di, dj in diagonal:
                ni, nj = i, j
                for _ in range(K):
                    if ni + di < 0 or ni + di >= N or nj + dj < 0 or nj + dj >= N: break
                    ni += di; nj += dj
                    if area[ni][nj] > 0:
                        remove_tree_cnt += area[ni][nj]
                    else:
                        break
            # 제초제 살포 좌표 갱신
            if maxV < remove_tree_cnt:
                maxV = remove_tree_cnt
                pi = i; pj = j
    return pi, pj, maxV

# [E] 제초제 살포
def spray_medicine(pi, pj):
    area[pi][pj] = 0
    medicine[pi][pj] = C
    for di, dj in diagonal:
        ni, nj = pi, pj
        for _ in range(K):
            if ni + di < 0 or ni + di >= N or nj + dj < 0 or nj + dj >= N: break
            ni += di; nj += dj
            medicine[ni][nj] = C
            if area[ni][nj] < 1: break
            area[ni][nj] = 0

# [main]
if __name__ == '__main__':
    # [1] 데이터 입력 및 초기화
    N, M, K, C = map(int, input().split())
    area = [list(map(int, input().split())) for _ in range(N)]
    medicine = [[0] * N for _ in range(N)]  # 제초제 유지 기간 기록
    cross = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    diagonal = [(-1, -1), (-1, 1), (1, -1), (1, 1)]

    # [2] 시뮬레이션 반복
    answer = 0
    for _ in range(M):
        growing_tree()
        reduce_medicine()
        pi, pj, remove_tree_cnt = search_medicine_point()
        spray_medicine(pi, pj)
        answer += remove_tree_cnt
    
    print(answer)