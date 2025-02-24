/**
 *  member.js
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
			this.parentElement.parentElement.remove();
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
			const html = `<tr>
							<td>${member.memberId}</td><td>${member.passwd}</td>
							<td>${member.memberName}</td><td>${member.responsibility}</td>
							<td><button onclick  = "deleteRow('${member.memberId}')" class = "btn btn-danger">삭제</td>
						  </tr>`;
			target.insertAdjacentHTML('beforeend', html);
		});
		return memberAry;
	})