### Q. IP 주소는 계층적으로 구분될 수 있습니다. 다음 IP가 속한 네트워크의 경우 몇개의 호스트를 가질 수 있는지 작성하고 그 이유를 설명하시오.
- 192.168.0.1/24

총 2^8 = 256개의 호스트를 가질 수 있다. (253개 인가? 0 -> 네트워크 주소(해당 서브넷 식별),1 ,255 ->브로드캐스트 주소 제외?)

IP주소는 subnet 파트와 host 파트로 나뉘며 `/24` 가 의미하는 것은 (255.255.255.0) bit 연산자를 이용해서 어디까지가 네트워크 아이디인지 구분하기 위함이다.스
따라서 앞에 24비트가 네트워크를 의미하며, 뒤의 8비트는 호스트의 정보를 표시하는 것을 의미한다.

### Q. 서브넷이 무엇인지 설명하고, 서브넷과 라우팅의 연관성을 설명하시오.
서브넷 (prefix, networkID) 
서브넷은 같은 네트워크를 가진 인터페이스들(호스트들)의 집합으로 볼 수 있다

라우터는 라우팅을 하기위해 여러개의 네트워크 인터페이스를 가지게 되며 각각의 인터페이스에 서브넷이 다르며 여러개의 서브넷이 섞인, 전달을 도와주는 중간자의 역할을 한다.

### Q. NAT를 사용하는 이유에 대해 간략하게 설명하시오.
다수의 호스트가 같은 서브넷 내에서 하나의 공용 네트워크를 사용시 호스트끼리 구분하기 위해 사용되며, 내부에서 사용하는 사설IP를 외부에서도 사용가능한 공용IP로 변환해준다.
보안적인 측면...

NAT (Network Address Translation) 작동원리
내부적으로 사용가능한 IP를 만들고 NAT장치를 통과시 외부와 통신되는 공용 IP로 변경 각각의 응답패킷들은 NAT장치를 통과하며 내부 IP주소로 전송하게 된다.
(내부IP(사설IP) : 공용IP + port 이루어진 translation table 만들어두고 변환해줌.)

### Q. 클라이언트가 IP 주소를 할당받기까지 위한 프로토콜과 그 과정을 설명하시오.
클라이언트가 IP주소를 할당 받아 네트워크 접속을 할 수 있게 하기 위해 DHCP (Dynamic Host Configuration Protocol)를 사용한다.

과정
1. DHCP Discover : 클라이언트가 주소요청 (주변 네트워크(서브넷 네트워크?) 전체에 전달됨 67포트인 DHCP서버에서만 응답을 하게됨) 
2. DHCP Offer : DHCP서버가 주소와 사용시간을 반환 (네트워크 전체에 전달 transactionID로 구분)
3. DHCP Request : 클라이언트가 배정된 서버를 사용하겠다는 확인 메세지를 보냄
4. DHCP ACK : 서버가 리퀘스트 패킷 수령후, IP 호스트 임대풀에서 남은거 할당하여 ACK로 피드백

3,4번과정이 필요한 이유는 여러개의 DHCP 서버가 존재할 경우 여러개의 오퍼가 들어오는데 그중 어떤 오퍼를 골랐는지 알 수 있어야함. + 3번,4번도 브로드캐스트 로 날리는 이유는 다른 DHCP서버도 알 수 있어야 해서 

IP주소를 전달받을때 IP주소 뿐만 아니라 (IP addr/Subnet mask, Gateway IP addr, DNS server IP addr) 총 세개의 주소를 받게됨

---

### Q. 네트워크 라우팅을 위해 사용되는 Link State 알고리즘과 Distance Vector 알고리즘에 대해 설명하시오.
#### Link State (LS)
- 각 노드들이 자기와 연결된 링크들의 정보를 **전체 네트워크**에 브로드캐스트하고 **다익스트라**를 적용하게됨
- 각각의 라우터들로 최단경로로 보내기 위한 가장가까운 라우터로 포워딩하게됨
- 현실적으로 전체 네트워크상에서 브로드캐스팅 -> 불가능
- 주로 같은 주체 내부에 있는 네트워크 안에서 사용
- 상용프로토콜 : `OSPF` (Open Shortest Path First)
#### Distance Vector (DV)
- 각 노드들이 자기와 연결된 **이웃끼리만(decentralized)** 링크들의 정보를 공유할 때 **다이나믹 프로그래밍**을 적용하게됨
- Bellman-Ford equation (dp) 사용
- x 노드에서 y노드로 보내기 위한 최단경로
	- $d_x(y)=min(c(x,v)+d_v(y))$
	- $c(x,v)$ : 이웃 v까지의 코스트
	- $d_v(y)$ : 이웃v에서 목적지 y까지 코스트 (재귀적으로 반복)
	- 위와 같은 공식을 재귀적으로 계산하게됨
- count-to-infinity problem 존재
	- poison reverse 사용 : 서로 의존적일 경우 infinity 값으로 보냄 (x가 y를 거쳐간 dv path를 y에게 INF로 보냄)
- 상용 프로토콜 : `RIP` (Routing Information Protocol)
### Q. 실제 전세계의 네트워크 환경에서는 위 두 알고리즘만으로 라우팅이 이루어지기는 어렵습니다. 그 이유와, 이를 보완하기 위해 사용되는 라우팅 방식을 설명하시오.
- 실제 네트워크는 복잡하여 전세계망을 대상으로 사용이 불가능 따라서 계층화 시킨 라우팅 방식인 `Hierachical routing`을 사용하게됨
- AS (Autonomous System) : 네트워크의 관리와 라우팅을 수행할 수 있는 독립된 네트워크
- Intra-AS routing algorithm (내부적으로)
	- `LS` or `DV` 적용
	- 가장 빠른 경로 채택
- Inter-AS rounting Algorithm (외부 AS간)
	- Path vector (PV) 알고리즘인 BGP (Border Gateway Protocol) 적용
	- 가장 저렴한 경로 채택
### Q. 계층 형태의 네트워크 구조에서 AS(Autonomous System)간의 관계에는 어떤 것들이 있는지 설명하고, BGP(Border Gateway Protocol)에서의 우선순위를 나열하시오..
`AS`간의 관계는 `Provider`, `Peer`, `Customer` 관계가 있는데
- `Provider` -> 돈내야됨
- `Peer` -> 돈안내도됨
- `Customer` -> 돈받아야됨
따라서 Customer > Peer > Provider 우선순위를 갖게되고 가장빠르기보단, 가장 저렴한 경로를 택하게된다.

### Q. 같은 서브넷 내부에서 이루어지는 'intra-AS routing'에서 broadcasting을 하는 방법들에 대해 설명하시오.
- broadcasting
	- 같은 서브넷 내부 존재하는 모든 노드에 패킷 전송
	- `source duplication`
	- `in-network duplication`
- multicasting
	- `local multicast group`에 속하는 노드에만 패킷 전송
	- `shared tree`
	- `source-based`
