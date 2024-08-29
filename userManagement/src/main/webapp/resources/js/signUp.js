const userId = document.querySelector("#userId");
const check  = document.querySelector("#check");

// #userId에 입력(input)이 되었을 때
userId.addEventListener("input", e => {

  // 비동기로 아이디 중복 값을 얻어오는 ajax코드 작성 예정
  
  // - ajax : 서버와 비동기 통신을 하기 위한 JS 기술
  // - fetch() API : JS에서 제공하는 ajax를 쉽게쓰는 코드

  fetch("/signUp/idCheck?userId=" + e.target.value)
  .then(resp => resp.text())  // 응답 값을 text로 변환
  .then(result => {
    // result : 첫번째 then()에서 resp.text()를 통해 text로 변환된 값
    console.log(result);
  });

  // 

});