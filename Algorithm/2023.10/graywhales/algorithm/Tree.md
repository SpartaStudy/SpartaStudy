# 트리

## 특징
- 단 하나의 루트 노드와  0개 이상의  겹치지 않는 하위 나무 구조들의 집합으로 이루어짐
- 트리내에 또 다른 트리가 있는 재귀적 자료 구조
- Loop를 갖지 않고, 연결된 무방향 그래프 구조
- 노드가 n개일 경우 항상 n-1개의 간선을 가짐
- 데이터를 순차적으로 저장하지 않는 비선형 자료구조

## 종류
- 편향 트리
	- 모든 노드들이 자식을 하나만 가지는 트리
- 이진트리
	- 각 노드의 차수(자식 노드)가 2 이하인 트리
계층 적 데이터 저장

-   트리는 데이터를 계층 구조로 저장하는 데 사용됩니다.
-   예를 들어 파일 및 폴더는 계층적 트리 형태로 저장됩니다.

## 용어
- 노드 (Node)
	- 트리를 구성하는 기본 요소
- 간선 (Edge)
	- 노드와 노드 간의 연결선
- 깊이 (Depth)
	- 루트에서 어떤 노드까지의 간선 수
- 높이 (Height)
	- 어떤 노드에서 리프 노드까지 가장 긴 경로의 간선 수
- 레벨 (Level)
	- 루트에서 어떤 노드까지의 간선 수
- Degree
	- 노드의 자식 수

- 루트 노드 (Root Node)
	- 부모가 없는 최상위 노드 (단일)
- 부모 노드 (Parent Node)
- 자식 노드 (Child Node)
- 형제 노드 (Sibling Node)
- 리프 노드 (Leaf Node)
	- 자식 노드가 없는 노드
- 가지 노드 (Branch Node)
	- 자식 노드를 하나 이상 가진 노드


## 이진트리순회

Root를 방문하는 순서에 따라 전위순회, 중위순회, 후위순회로 나뉨
### 전위순회
> Pre Order Traversal
 
 - Root - Left - Right  
### 중위순회
> In Order Traversal

-  Left - Root - Right  
### 후위순회
>Post Order Traversal

-  Left - Right - Root

### Java code
```java
//전위순회
public static void preOrderTraversal(Node root) {  
	if(root==null) return;  
	
	System.out.println(root.value);  
	preOrderTraversal(root.left);  
	preOrderTraversal(root.right);  
}  
//중위순회
public static void inOrderTraversal(Node root) {  
	if(root==null) return;  
	
	inOrderTraversal(root.left);  
	System.out.println(root.value);
	inOrderTraversal(root.right);  
}  
//후위순회
public static void postOrderTraversal(Node root) {  
	if(root==null) return;  
	
	postOrderTraversal(root.left);  
	postOrderTraversal(root.right);  
	System.out.println(root.value);
}
```
