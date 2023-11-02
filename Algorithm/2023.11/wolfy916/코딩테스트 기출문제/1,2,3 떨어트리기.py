'''
1,2,3 떨어트리기 - 프로그래머스 Lv.4(2023 KAKAO BLINT)
분류 : 트리
'''

def solution(edges, target):
    # [A] 각 회차마다 도착할 리프노드를 탐색하는 함수
    def search(v):
        if len(adjL[v]) == 0: return v
        arrival = search(adjL[v][path[v]])
        path[v] = (path[v] + 1) % len(adjL[v])  # 경로 조정
        return arrival

    # [B] 매번 숫자를 떨어트리며 체킹하는 백트랙킹 함수
    def drop(selected, order):
        nonlocal answer

        # 계산값 모두 일치
        if sum_lst[1:] == target:
            answer = selected
            return True

        # 리프노드 메모이제이션
        if len(orders) > order:
            leaf = orders[order]
        else:
            leaf = search(1)
            orders.append(leaf)

        # 백트랙킹 탐색
        for num in range(1, 4):
            if sum_lst[leaf] + num > target[leaf - 1]: continue
            sum_lst[leaf] += num
            if drop(selected + [num], order + 1): return True
            sum_lst[leaf] -= num

        return False

    # [1] 인접리스트 생성
    adjL = [[] for _ in range(len(target) + 1)]
    edges.sort(key=lambda x: [x[1], x[0]])
    for a, b in edges:
        adjL[a].append(b)  # 단방향 간선 표기

    # [2] 초기 연결된 길 설정
    path = [0] * (len(target) + 1)
    sum_lst = [0] * (len(target) + 1)

    # [3] 숫자 떨어트리며 탐색
    answer = [-1]
    orders = []
    drop([], 0)

    return answer


edges = [[2, 4], [1, 2], [6, 8], [1, 3], [5, 7], [2, 5], [3, 6], [6, 10], [6, 9]]
target = [0, 0, 0, 3, 0, 0, 5, 1, 2, 3]
print(solution(edges, target))