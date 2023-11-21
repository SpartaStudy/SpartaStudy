'''
중복 제거 - 백준 13701 골드 4
분류 : 비트마스킹

A_i에 입력 가능한 숫자의 크기는 2 ** 25 이므로 32bit인 정수형으로 나타 낼 수 있다. 입력의 개수는 500만 이하라고 하였으므로 32bit를 기준으로 약 20MB의 메모리가 필요하다는 것을 알 수 있다. 하지만 해당 문제의 메모리 제한은 8MB이므로 20MB의 메모리를 사용하게 되면 메모리 초과가 발생할 것이다.

이러한 문제를 해결하기 위해서 비트 마스킹(Bit Masking)이라는 기법을 이용해야 한다.

비트 마스킹이란 하나의 숫자를 하나의 비트에 대응시켜 표현하는 것을 말한다.

예를 들어 정수 10을 16bit 자료형으로 나타내면 아래와 같다.
0b0000000000001010

하나의 정수를 나타내는데 하나의 16bit 자료형을 사용해야 한다.

하지만 16bit 자료형의 10번째 비트만을 1로 바꾸어 저장하게 되면 아래와 같이 하나의 비트만을 이용해 10이라는 정수를 표현 할 수 있게 된다.
0b0000001000000000
'''
import sys
import os

def read_word():
    reader = os.fdopen(sys.stdin.fileno(), 'rb', buffering=1000000)
    ch = reader.read(1)
    while True:
        num = 0
        while not (ch == b'\n' or ch == b'' or (ch >= b'0' and ch <= b'9')):
            ch = reader.read(1)
        if ch == b'\n' or ch == b'':
            break
        while ch >= b'0' and ch <= b'9':
            num = num * 10 + int(ch)
            ch = reader.read(1)
        yield num

def set_bit(n):
    idx = n // 8
    nr = n % 8

    ret = (bits[idx] & (1 << nr)) == 0
    bits[idx] |= (1 << nr)

    return ret

if __name__ == '__main__':
    bits = bytearray(4194304)

    try:
        sys.stdin = open("13701_input.txt")
    except FileNotFoundError:
        pass

    for num in read_word():
        if set_bit(num) == True:
            print(num, end=" ")
    print()
    