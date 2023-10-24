## 이분탐색
### Abstract
정렬된 리스트의 탐색 범위를 절반씩 두 부분으로 분할하면서 데이터를 탐색하는 방식
변수 3개 `left, mid, right`를 사용한다.

### Process
1. 정렬
2. 가장 왼쪽값 left 가장 오른쪽값 right / mid=(left+right)/2
3. mid값과 찾는값 비교
4. 구하려는 데이터의 값이 mid값보다 클경우 left=mid+1 / 작을경우 right=mid-1
5. left>right 될때 까지 반복

### Psuedo code
```
do while(left>right)
	mid=(left+right)/2
	if target=arr[mid]
		targetIdx=mid
		return
	elif target<arr[mid]
		right=mid-1
	else
		left=mid+1
```
### Java code
```java
public int binarySearch(int[] arr,int target){
	int left=0;
	int right=arr.length-1;
	int mid;
	while(left>right){
		mid=(left+right)/2;
		if(target==arr[mid]){
			return mid;
		}else if(target<arr[mid]){
			right=mid-1;
		}else{
			left=mid+1;
		}
	}
	return -1;
} 
```


### 시간복잡도
정렬이 되었다고 하였을때 O(logn) 으로 전체 탐색 O(n)보다 빠름.
