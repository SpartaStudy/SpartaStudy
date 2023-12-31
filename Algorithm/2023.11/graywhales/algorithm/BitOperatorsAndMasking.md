## 2진수의 음수표현
### 1의보수
11111111-x 
`~` 연산과 동일

```
00000000 -> +0
00000001 -> +1
00000010 -> +2
00000011 -> +3
...
01111111 -> +127

10000000 -> -0
10000001 -> -1
10000010 -> -2
10000011 -> -3
...
11111111 -> -127
```
### 2의 보수
대부분의 경우 사용되는 방식
10000000-x
1의보수와 동일하게 하고 1을 더해줌 
```
00000001 -> +1
11111111 -> -1

00000010 -> +2
11111110 -> -2

01111111 -> +127
10000001 -> -127
```



## bitwise operator
![](https://www.researchgate.net/publication/276202732/figure/fig3/AS:667718365749268@1536207872972/Bitwise-Operators-i-Bitwise-operators-cannot-be-applied-to-float-or-double-They-can.png)


1. AND `&`
	1. 두 비트가 모두 1일때만 1 
```java 
int a = 6; //0110
int b = 5; //0101
int result = a & b; //0100 -> 4
```
2. OR `|`
	1. 두 비트중 하나 이상이 1일때 1, 둘다 0일때만 0
```java
int a = 5; //0101
int b = 3; //0011
int result = a | b; //0111 ->6
```
3. XOR `^`
	1. 두 비트가 서로 다를때만 1 같으면 0
```java
int a = 5;    // 0101
int b = 3;    // 0011
int result = a ^ b;  // 0110
```
4. NOT `~`
	1. 각 비트를 반전시킴
```java
int a = 5;    // 0101
int result = ~a;   // 1010 -> -6

// +6 -> 0110 // -6 -> 1010 (2의보수)
```
5. SHIFT `<<` , `>>` , `>>>`
- a `<<` b : a를 왼쪽으로 b비트 이동후 오른쪽에 0으로 채움

```java
int a = 5; //0000 0101
int result = a << 1; // 0000 1010

//2^n 의 숫자를 만들때 쉽게 1<<n 으로 사용됨 or 비트마스킹 할때도 자주 사용됨
int n = 10;
int b = 1<<n; // 2^10 1024
```

-  a`>>`b : 오른쪽으로 비트 이동후 왼쪽에 부호비트(양수 0, 음수 1)로 채워짐
```java
int a = -8;   // 1111 1000
int result = a >> 1;  // 1111 1100 -> -4

int b = 8;    // 0000 1000
int resultB = b >> 1; // 0000 0100 -> +4

```
-  a`>>>`b : 오른쪽으로 비트 이동후 왼쪽에 0으로 채워짐 (무조건 양수만 나옴)
- b가 음수일 경우
- b = -1 >> 32-1 = 31값으로 대체됨
```java
int a = -8;   // 1111 1000
int result = a >>> 1;  // 0111 1100 

int b = 8;    // 0000 1000
int resultB = b >> 1; // 0000 0100 -> +4
```


## bit masking
### abstract
비트마스크란 비트 연산자들을 사용하여 이진수의 비트들을 자료구조로 사용하는 기법을 말한다.
빠른 비트연산자를 O(1) 사용하여 구현하므로 수행시간이 빠르며, 2^N개의 경우를 n개의 비트만으로 관리할 수 있으므로 메모리 또한 적게사용하는 장점이 있다. 

### 집합
집합에서의 사용 : 0~7을 관리하는 set을 구현한다고 했을때
`<<` 왼쪽 쉬프트와 `|` `^` `&`  
총 8비트를 사용하여 
```
0000 0000 -> 아무 원소도 없다
0000 0001 -> 1<<0 0번째 원소 존재
0000 0010 -> 1<<1 1번째 원소 존재
...

1000 0000 -> 1<<7 7번째 원소 존재

1001 0100 -> ((1<<2 | 1<<4) | 1<<7) 2,4,7 원소 존재

```
다음과 같이 집합을 나타낼 수 있으며
6번째 원소가 존재하는지 확인하기 위해 다음과 같이 사용한다.
```java
int set = (1<<6) | (1<<3);

boolean flag ;

if(set & (1<<6)!=0) flag = true; 
```
