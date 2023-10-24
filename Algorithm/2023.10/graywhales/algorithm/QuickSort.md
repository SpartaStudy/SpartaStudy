## 퀵정렬
### Abstract
분할정복 방식으로 정렬하는 방법 pivot(기준)을 정하고 그보다 큰것은 뒤로 작은것은 앞으로 보내는 정렬방식. 그 후 pivot을 기준으로 갈라진 두 배열을 또다시 재귀적으로 반복하여 정렬.

### Process (오른쪽피벗)
1.  배열 가운데서 하나의 pivot을 정한다(가장 오른쪽 원소).
2.  앞에서는 피벗보다 큰값을 뒤에서부터 피벗보다 작은값을 탐색한다.
3.  그리고 둘을 교환한다.
4.  앞쪽 탐색위치와 뒷쪽 탐색위치가 교차하지 않을때 까지 2,3번을 반복
5.  교차하게되면 최종적으로 찾게된 오른쪽탐색위치와 피벗의 자리를 교체한다. 
6.  피벗을 기준으로 분할된 두 개의 배열에 대해 재귀적으로 과정을 반복한다.

### Psuedo code
```
quick_sort(A[l..r]) { # A[l..r]을 오름차순 정렬한다.
    if (l < r) then {
        p <- partition(A, l, r);  # 분할
        quick_sort(A, l, p - 1);  # 왼쪽 부분 배열 정렬
        quick_sort(A, p + 1, r);  # 오른쪽 부분 배열 정렬
    }
}

partition(A[], l, r) {
    x <- A[r];    # 기준원소
    i <- l - 1;   # i는 x보다 작거나 작은 원소들의 끝지점
    for j <- l to r - 1  # j는 아직 정해지지 않은 원소들의 시작 지점
        if (A[j] ≤ x) then A[++i] <-> A[j]; # i값 증가 후 A[i] <-> A[j] 교환
    if (i + 1 != r) then A[i + 1] <-> A[r]; # i + 1과 r이 서로 다르면 A[i + 1]과 A[r]을 교환
    return i + 1;
}
```

### Java code
```java
void quickSort(int[] arr,int left,int right){
	if(l<r){
	    int pivot = partition(); 
	    quickSort(arr, left, pivot-1);
	    quickSort(arr, pivot+1, right);
	}
}

int partition(int[] arr,int left,int right){
    int pivot = arr[r]; // 가장 오른쪽값을 피벗으로 설정
    int lo = left;
    int hi = right;
    while(lo<hi){
	while(lo<hi&&arr[lo]<pivot) lo++; // 피벗보다 큰값을 왼쪽에서 탐색
	while(lo<hi&&arr[hi]>=pivot) hi++; // 피벗보다 작거나 같은값을 오른쪽에서 탐색
	swap(arr,lo,hi);// 두 값을 교체
    }
    swap(arr,hi,right); // 
    return hi;
}

void swap(int[] arr,int a,int b){
	int tmp=arr[b];
	arr[b]=arr[a];
	arr[a]=tmp;
}
```


### 시간복잡도
일반적으로 O(nlogn)
최악의 경우 O(n^2)
이미 한쪽으로 정렬된 경우 최악의 경우가 된다. 
### 공간복잡도
제자리 정렬(in-place sort) 이기 때문에 O(n)

### 특징
불안정 정렬이다. 인접하지 않은 원소들을 swap하기 때문에 안정정렬이 불가능
또한 병합정렬과 비슷하게 분할정복을 이용하여 정렬을 하는데 같은 크기로 분할하는 병합정렬과 달리, 피봇의 값에 크기에따라 배열이 둘로나뉘어 균등하지 않게 나뉜다.
따라서 최악의경우 (미리 정렬이 된) 재귀의 depth가 logn -> n만큼 커져 O(n^2) 시간복잡도를 가짐.
