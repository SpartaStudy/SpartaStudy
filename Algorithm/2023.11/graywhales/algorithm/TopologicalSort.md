# 위상정렬
## Abstract
DAG(Directed Acyclic Graph) 사이클이 없는 방향 그래프의 모든 Vertex들을 방향을 거스르지 않도록 순서대로 나열하는 정렬을 의미한다. (한가지로만 정렬되는 것이 아니고 순서만 지킨다면 여러 가지 답이 존재 할 수 있음)

## Process
- Indegree (진입차수) : 특정한 노드로 들어오는 간선의 개수
- Outdegree (진출차수) : 특정한 노드에서 나가는 간선의 개수
1. 진입차수가 0인 노드를 큐에 넣는다.
2. 큐에서 원소를 꺼낸다.
3. 해당 노드에서 나가는 간선을 그래프에서 제거한다. (indegree 차수를 내림)
4. 새롭게 진입차수가 0이 된 노드를 큐에 삽입한다.
5. 큐가 빌때까지 2,3,4번을 반복한다.

## Psuedo Code
```
S ← Set of all nodes with no incoming edge
while S is not empty do
    remove a node n from S
    add n to L
    for each node m with an edge e from n to m do
        remove edge e from the graph
        if m has no other incoming edges then
            insert m into S

if graph has edges then
    return error   (graph has at least one cycle)
else
    return L   (a topologically sorted order)
```
## Java code
```java
static int[] indegree= new int[n];

public static void topologicalSort(){
	Queue<Integer> q =new ArrayDeque<>();
	//1. 진입차수가 0인 노드를 큐에 넣는다.
	for(int i=0;i<n;i++){
		if(indegree[i]==0){
			q.offer(i);
		}
	}
	while(!q.isEmpty()){
		//2. 큐에서 원소를 꺼낸다.
		int now=q.poll();
		System.out.print(now+" ");
		List<Intger> list = graph.get(now);
		
		for(int i=0;i<list.size();i++){
			int next=list.get(i);
			//3. 해당 노드에서 나가는 간선을 그래프에서 제거한다. (indegree 차수를 내림)
			if(--indegree[next]==0){
				//4. 새롭게 진입차수가 0이 된 노드를 큐에 삽입한다.
				q.offer(next);
			}
		}
	}
}
```

## 시간복잡도
O(V+E) 정점의 갯수 + 간선의 갯수 만큼 걸린다.

## 특징
최단거리를 빠르게 계산할 때 사용이 되어짐.
