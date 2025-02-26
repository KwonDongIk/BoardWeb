/**
 * 
 * reply.js
 * 
 */

console.log(svc.showName());
let page = 1; // 페이징
	

// 댓글
function makeReply(reply){
	
	const html = `<li data-id="${reply.replyNo}">
					<span class = "col-sm-2">${reply.replyNo}</span>
					<span class = "col-sm-5">${reply.reply}</span>
					<span class = "col-sm-2">${reply.replyer}</span>
					<span class = "col-sm-2"><button onclick = "deleteRow('${reply.replyNo}')">삭제</button></span>
				  </li>`;
				  
	return html;
	
	
}

// 글 삭제
function deleteRow(rno){
	
	if(!confirm("삭제하시겠어요?")){
		alert('취소합니다.');
		return;
	}
	svc.removeReply(rno
		,function(result){
			if (result.retCode == "OK"){
				//document.querySelector('li[data-id="'+rno+'"]').remove();
				showPageList();
				
				svc.makePaging(bno, function(result){
					const totalCnt = result.totalCnt;
					const realEnd = Math.ceil(totalCnt / 5);
					
					if(page > realEnd && realEnd > 0){
						page = realEnd;
						showPageList();
					}
					
					showPagingList();
				})
			}
		}
		,function(err) {console.log(err);})
	
	
	
}

function showPageList(){
	svc.replyList({bno, page} // 글번호 
							, // 성공 함수 	
							function(result){
								// 기존 목록 지우기
								document.querySelectorAll('li[data-id]').forEach(function(elem){
									elem.remove();
								});
								let resultAry = result;
								resultAry.forEach(function(reply){
									const target = document.querySelector('.reply>.content>ul');
								 	target.insertAdjacentHTML('beforeend', makeReply(reply));
								});
								}
								,
								function(err){
										
										console.log(err);
										
									}	
							);
}


// 목록
showPageList();
showPagingList();

// 페이징 생성
function showPagingList(){
	
	svc.makePaging(bno, 
			function(result){
				console.log(result); // {totalCnt : ???}
				const totalCnt = result.totalCnt;
				// startPage, endPage, currentPage
				// prev, next 계산
				let currentPage = page;
				let endPage = Math.ceil(currentPage / 10) * 10;
				let startPage = endPage - 9;
				let realEnd = Math.ceil(totalCnt / 5)
				endPage = endPage > realEnd ? realEnd : endPage;
				
				let prev = startPage != 1 ? true : false;
				
				let next = endPage != realEnd ? true : false;
				
				// 링크 생성
				let target = document.querySelector('div.footer > nav > ul');
				target.innerHTML = '';
				let html = '';
				// 이전페이지 존재
				if (prev) {
					
					html = `<li class="page-item">
						        	<a class="page-link" href="#" data-page="${startPage-1}">Previous</a>
						        </li>`
					
				} else {
					
					html = `<li class="page-item disabled">
						      		<a class="page-link">Previous</a>
						        </li>`
					
				}
				target.insertAdjacentHTML('beforeend', html);	
				
				for (let p = startPage; p <= endPage; p++){
					html = `<li class="page-item" aria-current="page"><a class="page-link" href="#" data-page="${p}">${p}</a></li>`
					
					if (currentPage == p){
						html = `<li class="page-item active" aria-current="page"><span class="page-link">${p}</span></li>`
					}
					target.insertAdjacentHTML('beforeend', html);
				}
				
				// 이후페이지 존재
				if (next) {
					
					html = `<li class="page-item">
						    	<a class="page-link" href="#" data-page="${endPage+1}">Next</a>
						    </li>`
					
				} else {
					
					html = `<li class="page-item disabled">
						    	<a class="page-link">Next</a>
						    </li>`
					
				}
				target.insertAdjacentHTML('beforeend', html);
							
				addLinkEvent();
				},
				
			
				function(){
				
			}
	);
} // showPageList func end
		


// 댓글 등록 이벤트 id="addReply"
document.querySelector('#addReply').addEventListener('click', function(){
	
	// 글번호 bno 작성자 logid 댓글 ?
	
	const reply = document.querySelector('#reply').value;
	const replyer = logid;
	
	if(!reply || !replyer){
		alert("필수 값이 없어요.");
		return;
	}
	
	const parm = {bno, reply, replyer}
	
	svc.addReply(parm //
		, function(result){
			
			if (result.retCode == "OK"){
				
				/*const html = makeReply(result.retVal);
				const target = document.querySelector('.reply>.content>ul');
				target.insertAdjacentHTML('beforeend', html);*/
				page = 1;
				showPageList();
				showPagingList();
				
				document.querySelector('#reply').value = ''; // 댓글창 초기화
				
				
			} else {
				alert("처리 예외가 발생했어요.");
				
				
				
			}
			
			
		}//
		, function(err){});	

})

function addLinkEvent(){
	// 페이징 목록의 링크() 이벤트
	document.querySelectorAll('div.footer>nav a').forEach(function(item){
		
		item.addEventListener('click', function(e) {
			
			e.preventDefault(); // 기본기능 차단
			console.log(e.target.getAttribute('data-page'));
			page = e.target.getAttribute('data-page'); // 링크 클릭하면 페이지 정보
			// 목록보기
			showPageList();
			// 페이지 생성
			showPagingList();
			
		});
		
	})
}

