**Routing protocol 학습**

<br>

## Routing protocol 학습

네트워크를 라우터에서 구현한 프로토콜로는 RIP, OSPF, BGP가 있다.

distance vector는 RIP, link state는 OSPF로 구현되어있다.

전세계적으로 통신을 할 경우 라우터가 무수히 많기 때문에 distance vector나 link state알고리즘을 사용하여 거리를 구할 수 없다.

대규모의 경우에는 계층화를 사용한다.

일정 네트워크 단위만큼 알고리즘을 통해 경로를 구하고, 다른 단위와 연결할 때 또 다른 방법을 사용한다. BGP라는 알고리즘을 사용한다.

AS(Autonomous System).

BGP는 이웃한 AS로부터 받은 정보를 전달하는 역할을 한다. provider, customer 네트워크로 나뉜다. 어떤 네트워크가 없을 때 영향을 크게 받는 쪽이 customer가 된다. SKT같은 통신사와 고객의 사이를 생각하면 된다. 이들 사이에 이동을 할 때 마다 customer가 provider에게 비용을 지불해야 한다.

peer라는 관계도 있는데, 두 네트워크의 계급이 같을 경우에 속한다. SKT와 KT의 사이가 peer관계.
