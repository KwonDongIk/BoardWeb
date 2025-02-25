/**
 *  member.js
 *  DB 삭제, 화면에서 지우기 ^^*
 */ 

// 삭제 함수
function deleteRow(id){
	console.log(id);
	fetch("removeMember.do?mid="+id)
	.then(function(result){
		return result.json();
	})
	
	.then((result) => {
		console.log(result);
		if(result.retCode == "OK"){
			document.querySelector('#tr_' + id).remove(); // 한건 지우기
		} else if(result.retCode == "NG"){
			alert("삭제 오류가 발생했어요.");
		} else {
			alert("알수 없는 코드에요.");
		}
	})
}


fetch("testData.do")
	.then(function(result){
		return result.json(); // stream을 object로
	})
	
	.then(function(result){
		const memberAry = result;
		memberAry.forEach(function(member){
			const target = document.querySelector('#list');
			const html = `<tr id = tr_${member.memberId}>
							<td>${member.memberId}</td><td>${member.passwd}</td>
							<td>${member.memberName}</td><td>${member.responsibility}</td>
							<td><button onclick  = "deleteRow('${member.memberId}')" class = "btn btn-danger">삭제</td>
						  </tr>`;
			target.insertAdjacentHTML('beforeend', html);
		});
		return memberAry;
	})
	
	
// 추가 이벤트
document.querySelector('#addMember').addEventListener('click', function(e){
	console.log("클릭");
	
	const id = document.querySelector('input[name="mid"]').value;
	const pw = document.querySelector('input[name="mpw"]').value;
	const name = document.querySelector('input[name="mname"]').value;
	
	fetch("addMember.do", {
		
		method : "POST",
		headers: {
		      "Content-Type": "application/x-www-form-urlencoded",
		    },
		    body: `mid=${id}&mpw=${pw}&mname=${name}`
	})
		.then(function(result){
			return result.json(); // stream을 object로
		})
		.then(function(result){
			if(result.retCode == "OK"){
							const target = document.querySelector('#list');
							const html = `<tr id = tr_${id}>
											<td>${id}</td><td>${pw}</td>
											<td>${name}</td><td>User</td>
											<td><button onclick  = "deleteRow('${id}')" class = "btn btn-danger">삭제</td>
										  </tr>`;
							target.insertAdjacentHTML('beforeend', html);
							}
						});
		})
		
		
			
		
