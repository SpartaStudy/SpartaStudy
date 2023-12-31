**IP protocol학습**

<br>

## IP protocol학습

NAT(Network Address Translation)란, IP 패킷에 있는 출발지 및 목적지의 IP 주소와 TCP/UDP 포트 숫자 등을 바꿔 재기록하면서 네트워크 트래픽을 주고 받게 하는 기술이다.

NAT를 사용하면 IP 주소 절약, 보안과 같은 장점이 있다. 하나의 공인 IP 주소를 사용하여 여러 대의 호스트가 인터넷에 접속할 수 있다. 라우터 외부로 트래픽이 나갈 때 사설 IP가 공인 IP 주소로 바뀌므로 IP를 숨길 수 있다.

NAT를 사용하게 되면 사용자가 패킷을 만들어서 보내면 라우터가 패킷 헤더에 있는 Destination IP, Port Number를 고친다. 라우터는 Network레이터에서 작동하는데, 들어오는 패킷의 헤더를 보고 다음 라우터로 전달해주는 역할이다. 패킷 헤더의 특정 내용을 고치는 역할까지 라우터가 하게 된다면 라우터가 해야할 주된 임무인 전송을 벗어난 행위이다.

### DHCP

DHCP(Dynamic Host Configuration Protocol)란 IP주소와 ICP/IP 프로토콜의 기본 설정을 클라이언트에게 자동적으로 제공해주는 프로토콜이다.

PC의 수가 많거나 PC 자체 변동사항이 많은 경우 IP 설정이 자동으로 되기 때문에 효율적으로 사용 가능하고, IP를 자동으로 할당해주기 때문에 IP 충돌을 막을 수 있다.

하지만, DHCP 서버에 의존하기 때문에 서버가 다운되면 IP 할당이 제대로 이루어지지 않는다.
