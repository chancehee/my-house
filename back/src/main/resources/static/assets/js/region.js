let date = new Date();
let mapContainer, mapOption, map;
let markerArr = [];
window.onload = function () {
  console.log("리로드");
  sendRequest("sidoRegist", "*00000000");
  let data = localStorage.getItem("flag");
  if (data == 1) {
    document.querySelector("#login-on").style.display = "none";
    document.querySelector("#login-off").style.display = "";
  } else if (data == 0) {
    document.querySelector("#login-on").style.display = "";
    document.querySelector("#login-off").style.display = "none";
  }
};

// 시도가 바뀌면 구군정보 얻기.
document.querySelector("#sidoRegist").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
    sendRequest("gugunRegist", regcode);
  } else {
    initOption("gugunRegist");
    initOption("dongRegist");
  }
});

// 구군이 바뀌면 동정보 얻기.
document.querySelector("#gugunRegist").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
    sendRequest("dongRegist", regcode);
  } else {
    initOption("dongRegist");
  }
});

function sendRequest(selid, regcode) {
  console.log(selid);
  const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
  let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
  fetch(`${url}?${params}`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      addOption(selid, data);
    });
}

function addOption(selid, data) {
  let opt = ``;
  initOption(selid);
  console.log(selid);
  switch (selid) {
    case "sidoRegist":
      opt += `<option value="">시도선택</option>`;
      data.regcodes.forEach(function (regcode) {
        opt += `
                    <option value="${regcode.code + regcode.name}">${
          regcode.name
        }</option>
                    `;
      });
      break;
    case "gugunRegist":
      opt += `<option value="">구군선택</option>`;
      for (let i = 0; i < data.regcodes.length; i++) {
        if (i != data.regcodes.length - 1) {
          if (
            data.regcodes[i].name.split(" ")[1] ==
              data.regcodes[i + 1].name.split(" ")[1] &&
            data.regcodes[i].name.split(" ").length !=
              data.regcodes[i + 1].name.split(" ").length
          ) {
            data.regcodes.splice(i, 1);
            i--;
          }
        }
      }
      let name = "";
      data.regcodes.forEach(function (regcode) {
        if (regcode.name.split(" ").length == 2)
          name = regcode.name.split(" ")[1];
        else
          name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
        opt += `
                    <option value="${regcode.code + name}">${name}</option>
                    `;
      });
      break;
    case "dongRegist":
      opt += `<option value="">동선택</option>`;
      let idx = 2;
      data.regcodes.forEach(function (regcode) {
        if (regcode.name.split(" ").length != 3) idx = 3;
        opt += `
                    <option value="${
                      regcode.code + regcode.name.split(" ")[idx]
                    }">${regcode.name.split(" ")[idx]}</option>
                    `;
      });
  }
  document.querySelector(`#${selid}`).innerHTML = opt;
}

function initOption(selid) {
  let options = document.querySelector(`#${selid}`);
  options.length = 0;
  // let len = options.length;
  // for (let i = len - 1; i >= 0; i--) {
  // options.remove(i);
  // }
}

// /////////////////////// 아파트 매매 정보 /////////////////////////
