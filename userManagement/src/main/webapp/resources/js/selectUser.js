const updateBtn = document.querySelector("#updateBtn");
const deleteBtn = document.querySelector("#deleteBtn");
const goToLsit = document.querySelector("#goToLsit");

goToLsit.addEventListener("click", ()=>{
  location.href = "/selectAll"; // 목록페이지 요청
});


// 요청사항에 담아서 바로 요청하지 못하는 이유:
// get방식으로 하면 주소에 숫자만 써서 다 지우니까 보안 X
deleteBtn.addEventListener("click", () => {

  if( !confirm("삭제 하시겠습니까?") ){ // 취소 클릭 시
    return;
  }

  // form태그, input태그 생성 후 body 제일 밑에 추가해 suvlet 하기
  const deleteForm = document.createElement("form");

      // 요청주소설정
  deleteForm.action = "/deleteUser";

      // 메서드 설정
  deleteForm.method = "POST";

  // input 요소 설정
  const input = document.createElement("input");

      // input 을 form의 자식으로 추가
  deleteForm.append(input);

      //input type, name, value 생성
  input.type = "hidden";
  input.name = "userNo";
  const userNoTd = document.querySelector("#userNoTd");
  input.value = userNoTd.innerText;

  // body태그 제일 마지막에 form 추가
  document.querySelector("body").append(deleteForm);

  deleteForm.submit();

});


