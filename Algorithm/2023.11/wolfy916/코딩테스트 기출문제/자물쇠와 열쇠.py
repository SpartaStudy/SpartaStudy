'''
자물쇠와 열쇠 - 프로그래머스 Lv. 3(2020 KAKAO BLIND)
분류 : 구현
1. 2차원 배열과 좌표를 90도로 회전시키는 rotate 함수 구현
2. 서로 다른 좌표값을 일치시켜 값을 비교하는 is_valid 함수 구현
'''

def solution(key, lock):

    # [A] key를 시계방향으로 90도 회전시키고,
    #     key의 돌기 좌표들도 회전시키는 함수
    def rotate(plate, coords):
        # [A-1] key 배열 회전
        rotate_plate = [[0] * M for _ in range(M)]
        for j in range(M):
            for i in range(M):
                rotate_plate[j][i] = plate[M - i - 1][j]
        
        # [A-2] key의 돌기 좌표 회전
        rotate_coords = []
        for x, y in coords:
            rotate_coords.append((y, M - x - 1))

        return rotate_plate, rotate_coords
    
    # [B] key의 돌기 좌표와 lock의 홈 좌표를 일치시켜 유효한지 확인하는 함수
    def is_valid(ki, kj, li, lj, lock_blank_cnt):
        di, dj = li - ki, lj - kj  # 좌표 일치를 위한 i, j 변화량
        for i in range(M):
            for j in range(M):
                ni, nj = i + di, j + dj
                # 인덱스 유효성 검사
                if ni < 0 or nj < 0 or ni >= N or nj >= N: continue
                # key의 홈은 제외
                if key[i][j] == 0: continue
                # key의 돌기와 lock의 돌기가 만나면 False
                if lock[ni][nj] == 1: return False
                lock_blank_cnt -= 1  # lock의 홈을 채운만큼 마이너스 카운트
        return True if lock_blank_cnt == 0 else False

    N, M = len(lock), len(key)
    # 자물쇠가 꽉 막혀있다면 True처리
    lock_blank_cnt = N ** 2 - sum(map(sum, lock))
    if lock_blank_cnt == 0: return True

    # [1] key의 돌기 부분 좌표 수집
    key_coords = []
    for i in range(M):
        for j in range(M):
            if key[i][j] == 0: continue
            key_coords.append((i, j))

    # [2] 배열을 회전시키며 체크
    for _ in range(4):
        key, key_coords = rotate(key, key_coords)
        for i in range(N):
            for j in range(N):
                if lock[i][j] > 0: continue
                for k in range(len(key_coords)):
                    ki, kj = key_coords[k]
                    if is_valid(ki, kj, i, j, lock_blank_cnt):
                        return True

    return False

key = [[0, 0, 0], [1, 0, 0], [0, 1, 1]]
lock = [[1, 1, 1], [1, 1, 0], [1, 0, 1]]
print(solution(key, lock))