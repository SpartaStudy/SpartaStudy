/**
 * Programmers_아날로그 시계
 *  - 문제 분류: 그리디..., 다른 분 풀이 참조
 */
const solution = (h1, m1, s1, h2, m2, s2) => {
  /* 변수 관리 */
  const [MINUTE, SECOND] = [60, 60];
  const [UNIT_ANGLE_H, UNIT_ANGLE_M, UNIT_ANGLE_S] = [30, 6, 6];
  let answer = ((h1 === 0 || h1 === 12) && m1 === 0 && s1 === 0) ? 1 : 0;

  /* 메인 로직 */
  answer += getCountFromMidNight(h2, m2, s2) - getCountFromMidNight(h1, m1, s1);

  /* 정답 반환 */
  return answer;

  // 해당 시간까지 겹친 횟수
  function getCountFromMidNight(h, m, s) {
    const angleS = UNIT_ANGLE_S * s;
    const angleM = (UNIT_ANGLE_M * m + (UNIT_ANGLE_M / SECOND) * s) % 360;
    const angleH = (UNIT_ANGLE_H * h + (UNIT_ANGLE_H / MINUTE / SECOND) * (MINUTE * m + s)) % 360;

    let count = 0;
    if (angleS >= angleH) count++;
    if (angleS >= angleM) count++;

    count += (h * MINUTE + m) * 2; // 분당 2번씩 만남(시침 1번, 분침 1번)
    count -= h; // 59~0분 넘어갈 때에는 만나지 않음(h의 크기만큼)
    if (h >= 12) count -= 2; // 11시59분59초~12시에는 초침이 시침/분침과 만나지 않음

    return count;
  }
}

// console.log(solution(0,5,30,0,7,0));
console.log(solution(	11, 59, 30, 12, 0, 0));