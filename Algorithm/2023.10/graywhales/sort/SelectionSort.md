## 선택정렬

### Abstract
정렬되지 않은 배열 중에서 가장 작은 값을 순차적으로 찾아나가며 바꿔나가는 정렬. 

### Process
1.   주어진 배열 중에 최댓값을 찾는다.
2.  그 값을 맨 뒤에 위치한 값과 교체한다.
3.  맨 뒤 위치를 뺀 나머지 배열을 같은 방법으로 교체한다.

### Psuedo code
```
selection_sort(A[1..N]) { # A[1..N]을 오름차순 정렬한다.
    for last <- N downto 2 {
        A[1..last]중 가장 큰 수 A[i]를 찾는다
        if (last != i) then A[last] <-> A[i]  # last와 i가 서로 다르면 A[last]와 A[i]를 교환
    }
}
```

### Java code
```java
void selectionSort(int[] arr) {
    int max, tmp;
    for (int i = arr.length-1; i >0; i--) {
        max = i;
        // 1. 남은 배열에서 가장 큰 값의 index를 max에 저장
        for (int j = i - 1; j >= 0; j--) {
            if (arr[j] > arr[max]) {
                max = j;
            }
        }
        // 2. 맨 뒤에 위치한 값과 swap해주기
        tmp = arr[max];
        arr[max] = arr[i];
        arr[i] = tmp;
  }
}
```

### 시간복잡도
O(n^2) 느린편

### 공간복잡도
주어진 배열 안에서 정렬되기에 O(n)

### 특징
-   비교횟수 n(n-1)/2 , swap횟수 최대n
    
-   Bubble Sort와 마찬가지로 정렬하고자 하는 배열 안에서 교환하는 방식이므로, 다른 메모리 공간을 필요로 하지 않는다. => 제자리 정렬(in-place sorting)
- 불안정 정렬이다. (중복된 값이 입력된 순서와 동일하게 정렬되지 않음, 앞에서부터 자리를 교환하기 때문에 2 2' 1 을 정렬하고 나면 1 2' 2 로 정렬이됨 1 2 2'이 아닌) 
