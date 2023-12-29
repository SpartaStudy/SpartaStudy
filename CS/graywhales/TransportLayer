## Q. TCP와 UDP Segment Header 필드별로 간단한 설명.
![](https://evan-moon.github.io/static/ac69210c44cd473bcb737665d590b124/21b4d/tcp-header.png)
- TCP[^1] 
	- source port/ dest port : 포트번호 (IP는 네트워크 헤더에)
	- SEQuence number : 데이터 순서 (초기값은 랜덤 -> 이전 커넥션과 중복감지) 1바이트당 1씩 증가
	- ACKnowledgement number : 승인 번호 (수신자가 예상하는 다음 시퀀스 번호) 
	- Data offset : 세그먼트중 헤더를 제외하고 시작하는 위치 (헤더 크기가 옵션때문에 가변적이라)
	- Reserved : 아직 목적이 안정해진 필드
	- Bit Flags
		- NS / CWR / ECE : 혼잡 제어 기능 향상
		- URG : 긴급 포인터 필드에 값 존재유무
		- ACK : 승인번호 존재 유무
		- PSH : 1일 경우 이 세그먼트 이후 더 연결된 세그먼트가 없음
		- RST : 연결을 리셋 요청
		- SYN : 동기화를 위한 세그먼트임을 나타냄
		- FIN : 연결 종료요청 세그먼트임을 나타냄
	- Window Size : 필드에 한번에 전송할 수 있는 데이터 양
	- Checksum : 오류검사/오류제어 를 위한 필드
	- Urgent Pointer : 우선처리 데이터를 가르키는 포인터
	- Options : TCP 기능을 확장시 사용
- UDP
	- source port/ dest port
	- length
	- checksum : 수신측에서 오류검사후 폐기용으로 사용 (오류를 제어하진 않음)

[^1]: https://evan-moon.github.io/2019/11/10/header-of-tcp/

## Q. TCP가 Reliable Transmission을 제공하는 방법에 대한 설명.
\+ Q. TCP가 Congestion Control과 Flow Control를 제공하는 방법.

- 오류제어 , 흐름 제어, 혼잡 제어를 통해 신뢰성있는 전달을 제공
- 오류제어
	- checksum,ack,time-out,seq,buffering,retransmission
- 흐름제어
	- send window (송신측 버퍼크기), recieve window(수신측 남은버퍼사이즈)를 이용하여 전송속도를 조절
	- 코너케이스 rwnd=0일경우 송신측은 프로브 보냄
- 혼잡제어
	- condition window(cwnd) : 네트워크가 받아들일 수 있는양
	- additive increase, multiplacative increase로 cwnd 사이즈를 조절함 -> 두개섞은 slow start
	- fast retransmit을 사용
## Q. Fast-retransmit에 대한 설명과 Fast-retransmit이 필요한 이유.
- 송신측에서 동일한 넘버의 ACK를 3번 추가적으로 받게되면 수신측은 해당하는 세그먼트가 유실되었다는 것을 알아차리고(유실, 네트워크가 혼잡해짐) (RTO 타이머가 만료되지 않더라고) cwnd 사이즈를 줄이고 해당 세그먼트를 재전송하게된다.
- 여러개의 패킷을 동시에 보내고, 오류가 났을경우 전체 데이터를 재전송하는 것이아닌 오류가 난 패킷만 재전송 하여 중복된 전송을 줄여 빠른 전송이 가능케 하고, 혼잡도를 감지하고 cwnd를 줄여 네트워크의 혼잡도 또한 제어하게 된다.
## Q. TCP Connection과 TCP Closing 과정을 단계별로 설명(Segment 필드값을 예시로 설명할 것)
- 3-way hand shaking
	- client -> server : SYN
	- server -> client : ACK + SYN
	- client -> server : ACK + (data)
- 4-way hand shaking
	- client -> server : FIN
	- server -> client : ACK (FIN 세그먼트 받았다 이후 남은 데이터전송을 위해 약간의 TIME-OUT 발생)
	- server -> client : FIN (서버내 데이터 전송 모두 끝낸뒤)
	- client -> server : ACK (TIME-WAIT을 둠 - 이후에도 클라이언트는 잉여 패킷을 기다리고 CLOSE)
- closing이 1step 이 더많은 이유
	- FIN에 대한 ACK와 더불어서 바로 FIN을 보낼 수 없기때문에 (서버에서 데이터전송 처리가 다 안끝날 수 있기 때문) step을 분리
