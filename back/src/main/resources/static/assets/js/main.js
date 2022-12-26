console.log("메인 js 를 잘 가져왔습니다!!!")
let date = new Date();
      let mapContainer, mapOption, map;
      let markerArr = [];

      let sidoText_global;
      let gugunText_global;
      let dongText_global;
      let delYear;
      let delMonth;
      
      
      let houseData = [];
      
      window.onload = function () {
        let yearEl = document.querySelector("#year");
        let yearOpt = `<option value="">매매년도선택</option>`;
        let year = date.getFullYear();
        for (let i = year; i > year - 20; i--) {
          yearOpt += `<option value="${i}">${i}년</option>`;
        }
        yearEl.innerHTML = yearOpt;

        mapContainer = document.getElementById("map"); // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.50184008653391, 127.03721870704817), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

        // 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
        map = new kakao.maps.Map(mapContainer, mapOption);

        // 브라우저가 열리면 시도정보 얻기.
        sendRequest("sido", "*00000000");
       
        let data = localStorage.getItem('flag');
        if(data==1){
            document.querySelector("#login-on").style.display = "none";
            document.querySelector("#login-off").style.display = "";
        }
        else if(data==0){
           
            document.querySelector("#login-on").style.display = "";
            document.querySelector("#login-off").style.display = "none";
        }
      };

      document.querySelector("#year").addEventListener("change", function () {
        let month = date.getMonth() + 1;
        let monthEl = document.querySelector("#month");
        let monthOpt = `<option value="">매매월선택</option>`;
        let yearSel = document.querySelector("#year");
        let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
        for (let i = 1; i < m; i++) {
          monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
        }
        monthEl.innerHTML = monthOpt;
      });

      // 시도가 바뀌면 구군정보 얻기.
      document.querySelector("#sido").addEventListener("change", function () {
        if (this[this.selectedIndex].value) {
          let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
          sendRequest("gugun", regcode);
        } else {
          initOption("gugun");
          initOption("dong");
        }
      });

      // 구군이 바뀌면 동정보 얻기.
      document.querySelector("#gugun").addEventListener("change", function () {
        if (this[this.selectedIndex].value) {
          let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
          sendRequest("dong", regcode);
        } else {
          initOption("dong");
        }
      });

      function sendRequest(selid, regcode) {
        const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
        let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
        fetch(`${url}?${params}`)
          .then((response) => response.json())
          .then((data) => addOption(selid, data));
      }

      function addOption(selid, data) {
//		console.log("[addOption] data: " + data);
        let opt = ``;
        initOption(selid);
        switch (selid) {
          case "sido":
            opt += `<option value="">시도선택</option>`;
            data.regcodes.forEach(function (regcode) {
              opt += `
                  <option value="${regcode.code}">${regcode.name}</option>
                  `;
            });
            break;
          case "gugun":
            opt += `<option value="">구군선택</option>`;
            for (let i = 0; i < data.regcodes.length; i++) {
              if (i != data.regcodes.length - 1) {
                if (
                  data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
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
              if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
              else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
              opt += `
                  <option value="${regcode.code}">${name}</option>
                  `;
            });
            break;
          case "dong":
            opt += `<option value="">동선택</option>`;
            let idx = 2;
            data.regcodes.forEach(function (regcode) {
              if (regcode.name.split(" ").length != 3) idx = 3;
              opt += `
                  <option value="${regcode.code}">${regcode.name.split(" ")[idx]}</option>
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
      document.querySelector("#list-btn").addEventListener("click", function () {
    	  let sidoSel = document.querySelector("#sido");
    	  let sidoText =sidoSel[sidoSel.selectedIndex].text;
    	  sidoText_global = sidoText;
    	  console.log(sidoText);
          let gugunSel = document.querySelector("#gugun");
          let gugunText =gugunSel[gugunSel.selectedIndex].text;
          gugunText_global = gugunText;
          console.log(gugunText);
          let dongSel = document.querySelector("#dong");
          let dongText =dongSel[dongSel.selectedIndex].text;
          dongText_global = dongText;
          
          console.log(dongText);
          let regCode = gugunSel[gugunSel.selectedIndex].value.substr(0, 5); // 선택한거 앞에서 5개
          let yearSel = document.querySelector("#year");
          let year = yearSel[yearSel.selectedIndex].value;
          delYear = yearSel[yearSel.selectedIndex].text;
          
          
          
          
          console.log("delYear(클라이언트): " + delYear);
          
          let monthSel = document.querySelector("#month");
          let month = monthSel[monthSel.selectedIndex].value;
          delMonth = monthSel[monthSel.selectedIndex].text;
          let dealYM = year + month;
          

                    
          let selectedData = JSON.stringify({
        	  sido: sidoText_global,
        	  gugun: gugunText_global,
        	  dong: dongText_global,
        	  delYear: delYear,
        	  delMonth: delMonth,
          })
          let param = {
        		method: 'POST',
          		headers: {
          			'Content-Type' : 'application/json',
          		},
          		body: selectedData,
          }

          	fetch("/houseApi/house", param)
          	.then((response) => response.json())
          	.then((data) => display(data));
        });
      
      function display(data) {
    	  // 화면에 테이블을 뿌려주는 로직
    	 
    	  console.log(Object.entries(data));
    	  let test = Object.entries(data);
    	  
    	  houseData = [];
    	  let idx = 1;
    	 
    	  let tag = `<table class = "table table-hover">`;
    	  let table = document.querySelector("#aptlist");
    	  table.innerHTML ='';
    
    	  
    	  	data.forEach(function(house){
    	  		console.log("forEach 내부: "+house);
    	  		console.log(house["apartmentName"]);
    	  		houseData.push(house);
    	  		
	  		table.innerHTML += `
	  			<tr>
	  				<td>${idx++}</td>
	  				<td>${house.apartmentName}</td>
	  				<td>${house.dong}</td>
	  				<td>${house.lng}</td>
	  				<td>${house.lat}</td>
	  			</tr>`;
  	}); 
 
  	
    	  // 지도 정보 생성 로직
    	  let parser = new DOMParser();
          const xml = parser.parseFromString(data, "application/xml");
          console.log(xml);
    	  
    	  
      }
      
      document.querySelector("#aptlist").addEventListener("click", (e) => {

    	  const idx = e.target.closest('tr').rowIndex - 1;
    	
    	  console.log("클릭");
    	  console.log(houseData[idx]["dong"]);
    	  document.querySelector("#housename").innerHTML = houseData[idx]["apartmentName"];
    	  document.querySelector("#houseprice").innerHTML = "거래 금액 " + houseData[idx]["dealAmount"] + "만원";
    	  document.querySelector("#housearea").innerHTML = "평수 : " + houseData[idx]["area"];
    	  var coords = new kakao.maps.LatLng(houseData[idx]["lat"], houseData[idx]["lng"]);
    	  console.log(markerArr);
    	  if (markerArr.length != 0) 
          {
            console.log("마커");
            console.log(markerArr);
            for (var i = 0; i < markerArr.length; i++) 
            {
              markerArr[i]["marker"].setMap(null);
              markerArr[i]["infowindow"].close(map, markerArr[i]["infowindow"]);
            }
            markerArr = [];
            console.log("마커");
            console.log(markerArr);
          }
    	  
    	  map.panTo(coords);
    	  map.setLevel(2);
    	
          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker(
          {
            map: map,
            position: coords,
          });
          var infowindow = new kakao.maps.InfoWindow(
          {
            content: `<div style="width:150px;text-align:center;padding:6px 0;">${houseData[idx]["dealAmount"]+"만원"}</div>`,
          });
          infowindow.open(map, marker);
          marker.setMap(map);
          markerArr.push({ marker, infowindow });
          console.log(markerArr);
          console.log("추가");
          map.setCenter(coords);
    	});
      

      function makeList(data) {
          //document.querySelector("table").setAttribute("style", "display: ;");
          let tbody = document.querySelector("#aptlist");
          let parser = new DOMParser();
          const xml = parser.parseFromString(data, "application/xml");
          information = [];
          initTable();
          let apts = xml.querySelectorAll("item");
          let region = null;
          apts.forEach((apt) => {
            var s = document.getElementById("dong");
            var value = s.options[s.selectedIndex].text;
            console.log(value);
            //console.log(apt);
            if (value == apt.querySelector("법정동").textContent.trim() && s.selectedIndex > 0) {
              let tr = document.createElement("tr");
              let price = apt.querySelector("거래금액").textContent.trim();
              tr.addEventListener("click", () => {
                console.log("선택");
                var geocoder = new kakao.maps.services.Geocoder();

                geocoder.addressSearch(
                  information[tr.rowIndex - 1]["address"],
                  function (result, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === kakao.maps.services.Status.OK) {
                  	  console.log(result[0].y);
                    	console.log(result[0].x);
                      var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                      map.setCenter(new kakao.maps.LatLng(result[0].y, result[0].x));
                      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                    }
                  }
                );

                var aptInfo = document.querySelector("#aptinfo");
                console.log(aptInfo);
                aptInfo.querySelector(".card-title").innerText = apt
                  .querySelector("아파트")
                  .textContent.trim();
                aptInfo.querySelector(
                  ".card-text"
                ).innerHTML = `<ul class="list-group list-group-flush">
                <li class="list-group-item">주소 : ${information[tr.rowIndex - 1]["address"]}</li>
                <li class="list-group-item">건축년도 : ${
                  apt.querySelector("건축년도").textContent
                }</li>
                <li class="list-group-item">층 : ${apt.querySelector("층").textContent}</li>
                <li class="list-group-item">전용면적 : ${apt
                  .querySelector("전용면적")
                  .textContent.trim()}</li>
                </ul>
                `;
                console.log(aptInfo.querySelector(".card-text").innerHTML);
              });
              if (region == null && apt.querySelector("중개사소재지").textContent.trim() != "")
                region = apt.querySelector("중개사소재지").textContent.trim();

              let address =
                apt.querySelector("도로명").textContent +
                " " +
                apt.querySelector("도로명건물본번호코드").textContent;
              console.log("address: " + address);
              information.push({ price, address });
              let nameTd = document.createElement("td");
              nameTd.appendChild(document.createTextNode(apt.querySelector("아파트").textContent));
              tr.appendChild(nameTd);

              let floorTd = document.createElement("td");
              floorTd.appendChild(document.createTextNode(apt.querySelector("층").textContent));
              tr.appendChild(floorTd);

              let areaTd = document.createElement("td");
              areaTd.appendChild(document.createTextNode(apt.querySelector("전용면적").textContent));
              tr.appendChild(areaTd);

              let dongTd = document.createElement("td");
              dongTd.appendChild(document.createTextNode(apt.querySelector("법정동").textContent));
              tr.appendChild(dongTd);

              let priceTd = document.createElement("td");
              priceTd.appendChild(
                document.createTextNode(apt.querySelector("거래금액").textContent + "만원")
              );
              priceTd.classList.add("text-end");
              tr.appendChild(priceTd);

              tbody.appendChild(tr);
            } else if (s.selectedIndex == 0) {
              let tr = document.createElement("tr");
              tr.addEventListener("click", () => {
                console.log("선택");
                var geocoder = new kakao.maps.services.Geocoder();

                geocoder.addressSearch(
                  information[tr.rowIndex - 1]["address"],
                  function (result, status) {
                    // 정상적으로 검색이 완료됐으면
                    console.log("검색완료");
                    if (status === kakao.maps.services.Status.OK) {
                  	console.log(result[0].y);
                  	console.log(result[0].x);
                      var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                      map.setCenter(new kakao.maps.LatLng(result[0].y, result[0].x));
                      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                    }
                  }
                );
              });
              let price = apt.querySelector("거래금액").textContent.trim();
              if (region == null && apt.querySelector("중개사소재지").textContent.trim() != "")
                region = apt.querySelector("중개사소재지").textContent.trim();

              let address =
                apt.querySelector("도로명").textContent +
                " " +
                apt.querySelector("도로명건물본번호코드").textContent;
              // console.log(address);
              information.push({ price, address });
              let nameTd = document.createElement("td");
              nameTd.appendChild(document.createTextNode(apt.querySelector("아파트").textContent));
              tr.appendChild(nameTd);

              let floorTd = document.createElement("td");
              floorTd.appendChild(document.createTextNode(apt.querySelector("층").textContent));
              tr.appendChild(floorTd);

              let areaTd = document.createElement("td");
              areaTd.appendChild(document.createTextNode(apt.querySelector("전용면적").textContent));
              tr.appendChild(areaTd);

              let dongTd = document.createElement("td");
              dongTd.appendChild(document.createTextNode(apt.querySelector("법정동").textContent));
              tr.appendChild(dongTd);

              let priceTd = document.createElement("td");
              priceTd.appendChild(
                document.createTextNode(apt.querySelector("거래금액").textContent + "만원")
              );
              priceTd.classList.add("text-end");
              tr.appendChild(priceTd);

              tbody.appendChild(tr);
            }
            // if (value == apt.querySelector("법정동").textContent)
  			// console.log("true");
          });
          for (let i = 0; i < information.length; i++) {
          	if(region!=null)
            information[i]["address"] = region + " " + information[i]["address"];
          }
          initMap();
        }

      function initTable() 
      {
        let tbody = document.querySelector("#aptlist");
        let len = tbody.rows.length;
        for (let i = len - 1; i >= 0; i--) {
          tbody.deleteRow(i);
        }
      }

      function initMap() 
      {
        //document.querySelector("#map").style.visibility = "visible";
        if (markerArr.length != 0) 
        {
          console.log("마커");
          console.log(markerArr);
          for (var i = 0; i < markerArr.length; i++) 
          {
            markerArr[i]["marker"].setMap(null);
            markerArr[i]["infowindow"].close(map, markerArr[i]["infowindow"]);
          }
          markerArr = [];
          console.log("마커");
          console.log(markerArr);
        }
        if (information.length > 0)
        {
          var geocoder = new kakao.maps.services.Geocoder();
          var x = 0.0;
          var y = 0.0;
          for (let i = 0; i < information.length; i++) 
          {
            geocoder.addressSearch(information[i]["address"], function (result, status)
            {
              // 정상적으로 검색이 완료됐으면
              if (status === kakao.maps.services.Status.OK)
              {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker(
                {
                  map: map,
                  position: coords,
                });
                var infowindow = new kakao.maps.InfoWindow(
                {
                  content: `<div style="width:150px;text-align:center;padding:6px 0;">${information[i]["price"]}</div>`,
                });
                infowindow.open(map, marker);
                marker.setMap(map);
                markerArr.push({ marker, infowindow });
                map.setCenter(new kakao.maps.LatLng(result[0].y, result[0].x));

                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              }
            });
          }
        }
      }