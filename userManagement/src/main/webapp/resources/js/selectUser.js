const updateBtn = document.querySelector("#updateBtn");
const deleteBtn = document.querySelector("#deleteBtn");
const goToList = document.querySelector("#goToList");


goToList.addEventListener("click", () => {
location.href = "/selectAll"; //목록으로

});


deleteBtn.addEventListener("click", () => {

  //confirm을 이용해서 삭제할지 확인

  if( !confirm("삭제 할끄야?") ) { //취소 클릭시
    return;
  }

  //form태그, input태그 생성 후 body 제일 밑에 추가해 sumit하기
  const deleteForm = document.createElement("form");

  //요청 주소 설정
  deleteForm.action = "/deleteUser";

  //데이터 전달 방식 설정
  
  deleteForm.method = "POST"

  const input = document.createElement ("input")

  //input을 fom에 자식 추가
  deleteForm.append(input);

  //input type,name,value 설정
  input.type = "hidden";
  input.name = "userNo";

  const u = document.querySelector("#userNoTd")
  input.value = u.innerText;


  // body 태그 제일 마지막에 form 추가
  document.querySelector("body").append(deleteForm);

  //deleteFrom 제출하기
  deleteForm.submit();

});