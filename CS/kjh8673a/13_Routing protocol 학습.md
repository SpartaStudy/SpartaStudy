**Routing protocol 학습**

<br>

## Routing protocol 학습

네크워크가 주어졌을 때 최단 경로를 찾아야 한다. -> 라우팅 알고리즘 사용

link state 알고리즘, distance vector 알고리즘을 사용한다.

link state는 모든 노드들이 전체 네트워크 상황을 알고 있다. 모든 노드는 자기 자신과 연결된 노드들의 정보를 갖고 있기 때문.

distance vector는 내가 알고있는 정보를 이웃과만 공유한다.

### distance vector

벨만-포드 방정식을 사용하는 알고리즘이다. 

x에서 y까지 최소 거리를 구할 때 바로 연결된 노드 중 하나를 선택하고 그로부터 y 까지 거리를 더하는 방식이다. 재귀를 이용해 값을 구한다.

x에서 바로 연결된 다음 노드까지의 거리를 갖고 있는 정보를 distance vector라고 한다.

즉, 모든 노드는 자신만의 distance vector를 가지고 x부터 y까지의 값을 구한다.