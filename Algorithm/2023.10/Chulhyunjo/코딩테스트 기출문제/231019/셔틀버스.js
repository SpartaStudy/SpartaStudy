// 문자열 -> 숫자 (시간 -> 분)
function formatDate(time){
  time = time.split(':')
  let second = parseInt(time[0])*60 + parseInt(time[1])

  return second
}

// 숫자 -> 문자열 (분 -> 시간)
function formatDate2(time){
  let hour = Math.floor(time / 60)
  let minute = time % 60
  hour = hour < 10 ? '0' + hour : hour.toString()
  minute = minute < 10 ? '0'+minute : minute.toString()
  return hour + ':' + minute
}

function solution(n, t, m, timetable) {
  for(let i=0; i < timetable.length; i++){
      timetable[i] = formatDate(timetable[i])
  }
  timetable = timetable.filter((t)=> t!==1439)
  timetable.sort((a, b) => a - b)
  
  let firstTime = 540
  let cnt = 0
  let canUse = -1
  let timetable_cnt = timetable.length
  let used_cnt = 0
  
  while (cnt < n){
      let now_cnt = 0
  
      while (now_cnt < m && timetable_cnt){
          if(timetable[0] <= firstTime){
              canUse = timetable.shift()
              timetable_cnt--
          }
          now_cnt++
      }
      used_cnt += now_cnt
      cnt++
      firstTime += t
  }
  console.log(timetable_cnt, used_cnt, canUse)
  if (canUse === -1 || timetable_cnt === 0 && used_cnt < m * n){
      return formatDate2(540 + t * (n-1))
  }
  
  return formatDate2(canUse - 1)
}