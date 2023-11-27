/**
 * Programmers_코딩테스트 공부
 *  - 문제 분류: 
 */
const solution = (alp, cop, problems) => {
  /* 변수 관리 */
  const N = problems.length;
  let [targetAlp, targetCop] = [0, 0];
  const dp = Array.from({ length: 151 }, () => Array.from({ length: 151 }, () => 301));
  let answer = 301;

  /* 메인 로직 */
  // Problems를 순회하면서 목표 targetAlp, targetCop를 구함
  for (let i=0; i<N; i++) {
    targetAlp = Math.max(targetAlp, problems[i][0]);
    targetCop = Math.max(targetCop, problems[i][1]);
  }

  problems.sort((a, b) => {
    const getRatioA = (a[2] + a[3]) / a[4];
    const getRatioB = (b[2] + b[3]) / b[4];
    if (getRatioA === getRatioB) return a[4] - b[4];
    
    return getRatioB - getRatioA;
  });

  // problems 길이 : 100
  // problems 값 : [필요 alp, 필요 cop, 상승 alp, 상승 cop, 소요 cost]
  // 모든 문제를 풀기 위해 필요한 최대 시간 : 300(최대 필요 alp, cop이 모두 150이기 때문)
  // 소요 시간당 상승하는 alp, cop를 구해 오름차순으로 정렬
  dfs(0, alp, cop);

  /* 정답 반환 */
  return answer;

  // dfs
  function dfs(depth, currAlp, currCop) {
    if (dp[currAlp][currCop] <= depth || answer <= depth) return;
    dp[currAlp][currCop] = depth; // 방문처리

    if (currAlp >= targetAlp && currCop >= targetCop) {
      answer = depth;
      return;
    }

    for (let i=0; i<N; i++) {
      const [needAlp, needCop, getAlp, getCop, cost] = problems[i];
      // 풀지 못하는 문제는 continue
      if (needAlp > currAlp || needCop > currCop) continue;
      // currAlp, currCop의 상한은 targetAlp, targetCop에 맞춤
      dfs(depth + cost, currAlp + getAlp > targetAlp ? targetAlp : currAlp + getAlp, currCop + getCop > targetCop ? targetCop : currCop + getCop);
    }

    if (currAlp < targetAlp) {
      dfs(depth + 1, currAlp + 1, currCop);
    }

    if (currCop < targetCop) {
      dfs(depth + 1, currAlp, currCop + 1);
    }
  }
}

console.log(solution(10,	10,	[[10,15,2,1,2], [20,20,3,3,4]]));
console.log(solution(0,	0,	[[0,0,2,1,2],[4,5,3,1,2],[4,11,4,0,2],[10,4,0,4,2]]));