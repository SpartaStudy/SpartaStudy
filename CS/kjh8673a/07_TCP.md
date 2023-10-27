**TCP**

<br>

## TCP

TCP는 send buffer와 receive buffer를 갖고 있다.

send buffer에는 애플리케이션 계층에서 내려온 메시지가 담긴다. 전송한 세그먼트가 유실될 경우 send buffer에 저장된 내용을 바탕으로 재전송한다. 상대방이 받은 것이 확인될 경우 메시지는 삭제된다.

응답받는 쪽에서는 receiver buffer를 이용해 들어온 세그먼트들을 저장하고있다가 순서를 맞춰서 애플리케이션 계층으로 올려보낸다.

- TCP의 주요 기능: reliable data transfer, flow control, connection management

### flow control

rceiver가 얼마나 받아들일 수 있는 능력이 있는지에 따라 속도를 설정해야한다.

리시버가 자신의 버퍼 상태를 피드백으로 알려주면된다. 얼마만큼 더 받을 수 있는지 남은 공간의 크기를 알려준다. TCP헤더에 리시버 버퍼라는 필드에 담아서 전송한다.

상대방의 버퍼에 남은 공간이 없더라도 주기적으로 세그먼트를 보내서 상태를 체크해야한다.

- Nagle's algorithm

  1. 처음에는 1바이트의 세그먼트를 보낸다.

  2. ACK가 올 때까지 다음에 보낼 세그먼트에 데이터를 쌓으면서 기다린다.

  3. ACK가 왔거나, 세그먼트에 데이터가 다 찼다면 보낸다.

### connection management

A -> B, B -> A 의 두번의 요청으로 연결을 확정하기는 부족하다. 재전송이 늦어질 경우 상대방과의 연결이 끊겨있을 수 있기 때문이다.

따라서, 3-way handshake방식으로 연결해야한다.

- 연결 시작

  1. A -> B: SYN (SYN비트 1, 시퀀스넘버 x) 전송

  2. B -> A: SYNACK (SYN비트 1, 시퀀스넘버 y, ACK비트 1, ACK넘버 x+1) 응답

  3. A -> B: SYNACK에 대한 ACK (ACK비트 1, ACK넘버 y + 1) 응답

- 연결 종료

  1. A -> B: FIN (FIN비트 1, 시퀀스넘버 x) 전송

  2. B -> A: ACK (ACK비트 1, ACK넘버 x+1) 응답

  3. B -> A: FIN (FIN비트 1, 시퀀스넘버 y) 전송

  4. A -> B: ACK (ACK비트 1, ACK넘버 y+1) 응답
