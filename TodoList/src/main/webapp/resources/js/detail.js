// 쿼리스트링 값 얻어오기

// location.search : 쿼리스트링만 얻어오기 ( ?index=1 )
// URLSearchParams : 쿼리스트링을 객체 형태로 다룰 수 있는 객체
const params = new URLSearchParams(location.search);
// 쿼리스트링 중 Key가 index인 값을 얻어오기
const listNo  = params.get("listNo");


const goToList    = document.querySelector("#goToList");
const completeBtn = document.querySelector("#completeBtn");
const updateBtn   = document.querySelector("#updateBtn");
const deleteBtn   = document.querySelector("#deleteBtn");

// 목록으로 버튼이 클릭된 경우
goToList.addEventListener("click", () => {
  // "/" (메인페이지) 요청 (GET방식)
  location.href = "/";
});


// 완료여부 변경 버튼 클릭시
completeBtn.addEventListener("click", () => {

  const complete = document.querySelector("#complete");

  // 현재 보고있는 todo의 완료여부를
  // (true) O <-> X (false) 변경요청
  location.href = "/todo/complete?listNo=" + listNo + "&complete=" + complete.value;
});


// 삭제 버튼 클릭시
deleteBtn.addEventListener("click", () => {

  // 1. 정말 삭제할 것인지 confirm()을 이용래서 확인
  if( !confirm("정말 삭제 하시겠습니까?") ){
    // 취소 클릭시
    return;
  }

  // 2. confirm() 확인 클릭시
  //   /todo/delete?index=인덱스번호 GET 방식으로 요청 보내기
  const title = document.querySelector("#title");
  location.href = "/todo/delete?listNo=" + listNo + "&title=" + title.value;

});


// 수정버튼 클릭시 수정할 수 있는 화면을 요청
updateBtn.addEventListener("click", () => {
  // GET 방식 요청
  location.href = "/todo/update?listNo=" + listNo;
});