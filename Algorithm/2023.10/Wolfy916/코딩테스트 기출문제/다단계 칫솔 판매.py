'''
다단계 칫솔 판매 - 프로그래머스
분류 : 연결리스트, 트리
'''

def solution(enroll, referral, seller, amount):
    # [1] 연결리스트 생성
    tree = dict()
    tree["-"] = [0, 'e']
    for i in range(len(enroll)):
        # tree[이름] = [총 이익금, 추천인 이름]
        tree[enroll[i]] = [0, referral[i]]
    
    # [2] 이익금 계산
    for i in range(len(seller)):
        user = seller[i]
        benefit = amount[i] * 100       # 판매대금
        fee = benefit // 10             # 10% 수수료
        tree[user][0] += benefit - fee  # 본인 수익금
        while fee:
            benefit = fee
            fee = benefit // 10
            par = tree[user][1]
            if par == 'e': break
            tree[par][0] += benefit - fee
            user = par
    
    # [3] 이익금 출력
    answer = []
    for user in enroll:
        answer.append(tree[user][0])
    return answer