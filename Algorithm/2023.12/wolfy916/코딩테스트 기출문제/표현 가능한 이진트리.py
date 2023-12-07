'''
표현 가능한 이진트리 - 프로그래머스 Lv. 3 (2023 KAKAO BLIND)
분류 : 트리
'''

def solution(numbers):
    
    # [A] 트리 순회 함수
    def check(l, m, r):
        nonlocal result
        visited[m] = True

        # [A-1] 좌측 서브트리
        left = (l + m) // 2
        if 0 <= left and not visited[left]:
            if bin_tree[m] == '0' and bin_tree[left] == '1':
                result = 0
                return
            check(l, left, m)

        # [A-2] 우측 서브트리
        right = (m + r) // 2
        if right < len(bin_tree) and not visited[right]:
            if bin_tree[m] == '0' and bin_tree[right] == '1':
                result = 0
                return
            check(m, right, r)
        

    answer = []
    for num in numbers:
        # [1] 포화이진트리의 노드수에 맞게 이진수 생성
        # ex) 8 = 0b1000 => 0001000
        bin_num = bin(num)[2:]
        k = 0
        cnt = 2 ** k
        while len(bin_num) > cnt:
            k += 1
            cnt += 2 ** k
        bin_tree = '0' * (cnt - len(bin_num)) + bin_num
        
        # [2] 루트노드부터 순회하며 만들수 있는 숫자인지 확인
        result = 1
        visited = [False] * len(bin_tree)
        check(0, len(bin_tree) // 2, len(bin_tree))
        answer.append(result)
        
    return answer