# 트리(Tree)

> 계층적 관계를 표현하는 자료구조



## 트리의 특징

- 계층적 데이터 저장
- 효율적인 검색 속도
  - 효율적인 삽입, 삭제 및 검색을 위한 자료구조
- 힙(Heap)
  - 힘도 트리로 구성된 자료구조
- 데이터 베이스 인덱싱
  - 데이터베이스 인덱싱을 구현하는데 트리를 사용
  - ex) B-Tree, B+Tree, AVL Tree
- Trie
  - 사전(문자열)을 저장하는 데 사용 되는 트리



## 트리 용어

![img](https://blog.kakaocdn.net/dn/cPiOFh/btqxeKMdB9v/qOFAWTqPnyK8YfnkYOEft0/img.png)

- 노드(Node): 트리의 기본 요소

- 가지(Branch, Edge): 노드와 노드를 연결하는 간선 

- 루트 노드(Root Node): 트리의 맨 위에 있는 노드 (1번 노드)
- 차수(Degree): 각 노드에서 뻗어나온 가지의 수
- 단말 노드, 리프 노드(Leaf Node): 자식이 하나도 없는 노드, 차수가 0인 노드
- 비단말 노드: 자식이 하나라도 있는 노드
- 자식 노드(Children): 어떤 노드에 연결된 다음 레벨의 노드
- 부모 노드(Parent): 어떤 노드에 연결된 이전 레벨의 노드
- 형제 노드(Sibling): 동일한 부모를 갖는 노드
- 레벨(Level): 루트 노드의 레벨을 1로 가정하고 그 이후는 L+1
- 깊이(depth): 어떤 트리에서 노드를 가질 수 있는 최대의 레벨



## 트리의 종류

- 이진 트리(Binary Tree)
  - 각 노드가 최대 2개의 자식을 갖는 트리
  - 탐색은 중위 순회, 전위 순회, 후위 순회가 있다
- 완전 이진 트리(Complete Binary Tree)
  - 마지막 레벨을 제외하고 모든 레벨이 완전히 채워져 있는 트리
- 균형 이진 트리(Full Binary Tree)
  - 모든 노드가 0개 또는 2개의 자식 노드를 자는 트리
- 포화 이진 트리 (Perfect Binary Tree)
  - 균형 이진 트리이면서 완전 이진 트리
  - 모든 리프 노드는 같은 높이에 있어야 한다.
- 균형 트리(B-Tree)
  - 레드블랙 트리, AVL트리
  - 시간 복잡도 O(logN)에 삽입, 찾기 가능한 균형 잡힌 트리
- 편향 트리(Skew tree)
  - 모든 노드들이 자식을 하나만 가진 트리



## 트리의 순회

![img](https://blog.kakaocdn.net/dn/xZuQT/btqxf5ITHaB/qRKMOItbrMqeZOEeIDkHuk/img.png)

- 전위 순회 (Preorder)
  - A -> B -> C
  - 중간 -> 왼쪽 -> 오른쪽
- 중위 순회 (Inorder)
  - B -> A -> C
  - 왼쪽 -> 중간 -> 오른쪽
- 후위 순회(Postorder)
  - B -> C-> A
  - 왼쪽 -> 오른쪽 -> 중