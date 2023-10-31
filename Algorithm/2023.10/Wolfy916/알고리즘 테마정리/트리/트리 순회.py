'''
트리 순회 - 백준1991(실버1)
분류 : 이진 트리
1. 3가지 트리 순회
'''
import sys

# [A] 입력 함수 초기화
def input():
    return sys.stdin.readline().rstrip('\n')

# [B] 전위 순회
def pre_order(node):
    if node == '.':
        return
    print(node, end='')
    pre_order(bin_tree[node][0])
    pre_order(bin_tree[node][1])

# [C] 중위 순회
def in_order(node):
    if node == '.':
        return
    in_order(bin_tree[node][0])
    print(node, end='')
    in_order(bin_tree[node][1])

# [D] 후위 순회
def post_order(node):
    if node == '.':
        return
    post_order(bin_tree[node][0])
    post_order(bin_tree[node][1])
    print(node, end='')

# [main]
if __name__ == '__main__':
    N = int(input())
    bin_tree = dict()
    for _ in range(N):
        N, L, R = input().split()
        bin_tree[N] = [L, R]
    pre_order('A')
    print()
    in_order('A')
    print()
    post_order('A')