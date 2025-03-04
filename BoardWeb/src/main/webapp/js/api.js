/**
 *  api.js
 */

let centerAll = [];


// 이벤트(select) 등록
document.getElementById('centerList').addEventListener('change', function(e){
	console.log(e.target.value);
	let sidoName = e.target.value; // '서울특별시' ... etc
	let filterSido = [];
	
	filterSido = centerAll.filter(item => {
		
		if(sidoName == item.sido){
			return true;
		} else {
			return false;
		}
	})
	console.log(filterSido);
	makeCenterList(filterSido);
})

function makeCenterList(centerAry = []){
	let fields = ['id', 'centerName', 'phoneNumber', 'sido'];
	// 기존목록 삭제
	document.getElementById('list').innerHTML='';
	// 센터정보
	centerAry.forEach(center => {
		let tr = document.createElement('tr');
		tr.addEventListener('click', function(){
			
			console.log(center.lat, center.lng, center.centerName);
			window.open('map.do?lat=' + center.lat + '&lng=' + center.lng + '&centerName=' + center.centerName);
		});
		
		for (let i = 0; i < fields.length; i++){
			let td = document.createElement('td');
			td.innerHTML = center[fields[i]];
			tr.appendChild(td);
		}
		document.getElementById('list').appendChild(tr);
	});
}

// ajax
fetch('https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&serviceKey=eypP4%2FfpdVXkcjrv%2FAF1gMIvZPGkel1ppFBZwt6Lx84GIWKSVKV1zzUGf8M4qOmP1f%2Bxn8J%2F7WQDHQcJd4InIA%3D%3D')
	.then(result => result.json())
	.then(result => {
		console.log(result.data);
		centerAll = result.data;
		makeSidoList();
	})
	.catch(err => console.log(err));
	

// 시도 정보 중복제거 후 화면 출력	
function makeSidoList(){
	let sidoList = []; // ['서울특별시', '인천광역시', '..']
	
	for (let i = 0; i < centerAll.length; i++){
		if(sidoList.indexOf(centerAll[i].sido) == -1) {
			sidoList.push(centerAll[i].sido);
		}
	}
	console.log(sidoList.sort());
	sidoList.forEach(sido => {
		let opt = document.createElement('option');
		opt.innerHTML = sido; // <option> 태그 만듬
		document.getElementById('centerList').appendChild(opt);
	})
	
}

