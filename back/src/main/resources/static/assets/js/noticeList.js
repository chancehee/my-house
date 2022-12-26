console.log("공지사항 리스트 js 파일 내부입니다~");

fetch(`/noticeApi/notice/1`)
  .then((response) => response.json())
  .then((data) => {
    document.querySelector("#notice-table").style.display = "block";
    console.log(data);
    makeList(data);
  })
  .catch((error) => {
    console.log("에러 발생");
    document.querySelector("#notice-table").style.display = "none";
  });

// 공지사항 테이블 화면에 띄우기
function makeList(notices) {
  let tbody = ``;

  notices.forEach(function (notice) {
    tbody += `
			<tr id="notice_${notice.num}" class="view text-center" data-id="${notice.num}" ondblclick="noticeDetail(this);" >
				<td>${notice.num}</td>
				<td>${notice.user_id}</td>
				<td>${notice.title}</td>
				<td>${notice.text}</td>
				<td>${notice.join_date}</td>
				<td><input class="check_class" type="checkbox" name="num" value="${notice.num}"></td>
			</tr>
		`;
  });
  document.querySelector("#tbody").innerHTML = tbody;
}

// 모달창으로 상세정보 띄우기
function noticeDetail(el) {
  var modal = new bootstrap.Modal(document.getElementById("noticeDetail"), {
    backdrop: true,
    keyboard: false,
  });

  // 선택한 글의 번호 (num)
  let id = el.getAttribute("data-id");

  fetch(`/noticeApi/noticeDetail/${id}`)
    .then((response) => response.json())
    .then((data) => {
      document.querySelector("#vnum").innerHTML = data.num;
      document.querySelector("#vid").innerHTML = data.user_id;
      document.querySelector("#vtitle").innerHTML = data.title;
      document.querySelector("#vtext").innerHTML = data.text;
      document.querySelector("#vjoindate").innerHTML = data.join_date;
      modal.show();
    });
}

//  체크박스 삭제 로직
function really() {
  if (confirm("정말 삭제하시겠습니까?")) {
    console.log("삭제 버튼을 클릭했습니다!!");
    let checkedAll = document.querySelectorAll(".check_class");

    console.log(checkedAll);

    // document.querySelector(".check_class").chekced;
    for (let i = 0; i < checkedAll.length; i++) {
      if (checkedAll[i].checked == true) {
        console.log(checkedAll[i]);
        console.log(checkedAll[i].value); // notice.num 으로 지정해준 값을 가져온다. (name="num", value="1")
        let num = checkedAll[i].value;
        console.log(typeof num);
        // 가져온 값을 삭제한다.

        let config = {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        };

        let pgNo = "1";
        fetch(`noticeApi/notice/${num}/${pgNo}`, config)
          .then((response) => response.json())
          .then((data) => console.log(data));
      }
    }
    location.reload();
  } else {
    console.log("아니요 클릭함!!");
  }
}

function deleteOne() {
  if (confirm("정말 삭제하시겠습니까?")) {
    console.log("공지사항 1개 삭제 버튼 클릭함!");

    let config = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    };

    let num = document.querySelector("#vnum").textContent;
    let pgNo = "1";
    fetch(`noticeApi/notice/${num}/${pgNo}`, config)
      .then((response) => response.json())
      .then((data) => console.log(data))
      .then((location.href = `/noticeList`));
  }
}
