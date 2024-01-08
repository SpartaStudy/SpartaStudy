## Q. TCP와 UDP Segment Header 필드별로 간단한 설명.
![](https://evan-moon.github.io/static/ac69210c44cd473bcb737665d590b124/21b4d/tcp-header.png)
- TCP[^1] 
	- source port/ dest port : 포트번호 (IP는 네트워크 헤더에) demux mux 기능을 담당
	- SEQuence number : 데이터 순서 (초기값은 랜덤 -> 이전 커넥션과 중복감지) 1바이트당 1씩 증가
	- ACKnowledgement number : 승인 번호 (수신자가 예상하는 다음 시퀀스 번호) 
	- Data offset : 세그먼트중 헤더를 제외하고 시작하는 위치 (헤더 크기가 옵션때문에 가변적이라)
	- Reserved : 아직 목적이 안정해진 필드
	- Flag bits (해당 세그먼트의 용도 표시)
		- NS / CWR / ECE : 혼잡 제어 기능 향상
		- URG : 긴급 포인터 필드에 값 존재유무
		- ACK : 승인번호 존재 유무
		- PSH : 1일 경우 이 세그먼트 이후 더 연결된 세그먼트가 없음
		- RST : 연결을 리셋 요청
		- SYN : 동기화를 위한 세그먼트임을 나타냄
		- FIN : 연결 종료요청 세그먼트임을 나타냄
	- Window Size : sender측의 수신버퍼의 빈 공간을 표시 (rwnd)
	- Checksum : 오류검사/오류제어 를 위한 필드
	- Urgent Pointer : 우선처리 데이터를 가르키는 포인터
	- Options : TCP 기능을 확장시 사용
- UDP
	- source port/ dest port
	- length
	- checksum : 수신측에서 오류검사후 폐기용으로 사용 (오류를 제어하진 않음)

[^1]: https://evan-moon.github.io/2019/11/10/header-of-tcp/

## Q. TCP가 Reliable Transmission을 제공하는 방법에 대한 설명.
- RDT (Reliable Data Transfer)
- 오류제어
	- ACKS, NAKS 사용
		- ACKs: receiver가 패킷을 온전히 받았다 sender에게 알려주는 것
		- NAKs: receiver가 패킷에 에러가 있다고 sender에게 알려주는 것
		- 실제로 TCP는 NAKs를 헤더는 없음 but 중복된 ACK Number를 보냄으로써 NAKs를 보낸것이 됨
	- RTT (Round Trip Time/Timeout)을 사용
		- Sender에서 Receiver로부터 ACK를 받지 못했을 때, 일정 시간이 지나면 패킷유실이 발생함을 인지후 패킷을 재전송하여 패킷로스와 ACK로스 상황을 대처함

## Q. TCP가 Congestion Control과 Flow Control를 제공하는 방법.
- 흐름제어
	- receiver측에서 수행하는 제어 기법
	- sender와 receiver의 데이터 처리 속도 차이로 인한 수신 버퍼 overflow에 대처
	- recieve window(수신측 남은버퍼사이즈)를 이용하여 전송속도를 조절
	- 코너케이스 rwnd=0일경우 송신측은 프로브 보냄
- 혼잡제어
	- sender측에서 수행하는 제어 기법
	- sender의 데이터 전송속도와 네트워크의 처리 속도 차이로 발생하는 혼잡에 대처
	- 네트워크 경로 상의 한꺼번에 많은 데이터가 전송시 전송 지연시간 증가 및 버퍼 overflow로 패킷로스가 발생
	- condition window(cwnd) : 네트워크가 받아들일 수 있는양 즉 sender의 윈도우 사이즈를 조절하면서 혼잡을 제어를 하게됨.
	- additive increase, multiplacative increase로 cwnd 사이즈를 증가 -> 두개섞은 slow start
	- fast recovery(cwnd를 절반으로 줄임), fast retransmit(4번 중복시 재전송)을 사용
## Q. Fast-retransmit에 대한 설명과 Fast-retransmit이 필요한 이유.
- 같은 ACK를 4회 이상 수신한다면 RTT를 기다리지 않고 바로 재전송이 이루어지며, 이를 통해 타이머의 타임아웃만큼 긴 지연시간으로 자원 낭비 발생을 막게됨
- 송신측에서 동일한 넘버의 ACK를 3번 추가적으로 받게되면 수신측은 해당하는 세그먼트가 유실되었다는 것을 알아차리고(유실, 네트워크가 혼잡해짐) (RTO 타이머가 만료되지 않더라고) cwnd 사이즈를 줄이고 해당 세그먼트를 재전송하게된다.
- 여러개의 패킷을 동시에 보내고, 오류가 났을경우 전체 데이터를 재전송하는 것이아닌 오류가 난 패킷만 재전송 하여 중복된 전송을 줄여 빠른 전송이 가능케 하고, 혼잡도를 감지하고 cwnd를 줄여(Fast Recovery) 네트워크의 혼잡도 또한 제어하게 된다.
## Q. TCP Connection과 TCP Closing 과정을 단계별로 설명(Segment 필드값을 예시로 설명할 것)
- 3-way hand shaking
	- client -> server : SYN (SEQ =x 난수 설정)
	- server -> client : ACK + SYN (SEQ=y, ACK NUM= x+1)
	- client -> server : ACK + (data) (SEQ=x+1, ACK NUM=y+1)
- 4-way hand shaking
	- client -> server : FIN
	- server -> client : ACK (FIN 세그먼트 받았다 이후 남은 데이터전송을 위해 약간의 TIME-OUT 발생)
	- server -> client : FIN (서버내 데이터 전송 모두 끝낸뒤)
	- client -> server : ACK (TIME-WAIT을(2MSL) 둠 - 이후에도 클라이언트는 잉여 패킷을 기다리고 CLOSE)
		- MSL (Maxmimum Segement Lifetime) 일반적으로 2분
- closing이 1step 이 더많은 이유
	- FIN에 대한 ACK와 더불어서 바로 FIN을 보낼 수 없기때문에 (서버에서 데이터전송 처리가 다 안끝날 수 있기 때문) step을 분리
