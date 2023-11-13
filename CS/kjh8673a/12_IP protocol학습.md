**IP protocol학습**

<br>

## IP protocol학습

MTU란 한 번에 전송될 수 있는 최대 크기의 패킷을 말한다. 처리할 수 있는 MTU보다 큰 사이즈의 패킷이 들어오게 되면 분리하여 전송한다.

fragment로 분리될 때 offset, flag와 같은 정보가 같이 들어간다. 도착 IP에서 다시 하나로 reassembly 되는데 이용된다.


ICMP(Internet Control Message Protocol) 라우터, 라우트 호스트끼리 컨트롤 메시지를 교환하기 위한 프로토콜이다.


IPv4와 IPv6의 차이는 address 공간의 확장이다. 128비트로 확장이 됐다.


IPv4로 동작하는 라우터는 IPv6를 이해할 수 없기 때문에 IPv6패킷을 보내더라도 IPv4패킷으로 감쌀 수 있는 터널링(Tunneling)이 필요하다.

라우터들을 지나쳐 목적지로 갈 때 최소 비용으로 가는 것이 좋다. Link-State Routing Algorithm을 통해 해결한다.

목적지까지 최단거리를 계산하기 전에 모든 라우터들은 네트워크 상황을 알고있다는 것이 특징이다. 다익스트라 알고리즘이 사용된다.