**TCP 소켓**

<br>

## SMTP

simple mail transfer protocol의 약자로 인터넷을 통해 이메일을 보내고 받는 데 사용되는 통신 프로토콜이다.

Email System은 크게 3가지로 구성된다.

1. user agents

2. mail servers

3. SMTP

### User agents

user. 즉, 메일을 작성하거나 읽는 것을 수행한다.

### Mail servers

이메일 메세지를 갖고 있는 서버이다. 이메일을 가져온다는 것은 서버에 저장되어 있는 메세지를 가져오는 것이다.

### SMTP

메일 서버들 간에 메세지를 주고 받을 때 사용하는 프로토콜이다. 메일을 보내는 쪽 서버가 client, 받는 쪽 서버가 server가 된다.

SMTP는 TCP를 이용한다.

### Mail을 보내는 과정

- Alice가 Bob에게 mail을 보내는 과정

1. Alice가 메일을 보냄.

2. mail server에서 Alice의 메일을 받음.

3. Bob's mail server로 메일을 전송

4. Bob이 메일을 열면 Bob의 user agent로 메일 전송.

### Mail access protocol

- POP: 받는 쪽 서버에 ID, PWD를 걸어서 이를 입력해야 이메일을 받을 수 있다. 계정을 입력하면 내 컴퓨터에 이메일을 다운로드받는다.

- IMAP: 서버에서 메일을 읽는 프로토콜이다. 서버에 저장된 내용을 보여주고 변경사항을 동기화한다. 여러 디바이스에서 접속해도 동일한 정보를 확인할 수 있다.

### 소켓

프로세스들 간에 커뮤니케이션을 위한 인터페이스를 OS에서 제공해주는데 이를 소켓 인터페이스라고 한다.
