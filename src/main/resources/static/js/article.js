// "delete-btn" ID를 가진 요소를 가져옴 (삭제 버튼)
const deleteButton = document.getElementById('delete-btn');
// "modify-btn" ID를 가진 요소(수정 버튼)를 가져옴
const modifyButton = document.getElementById('modify-btn');
// "create-btn" ID를 가진 요소(생성 버튼)를 가져옴
const createButton = document.getElementById("create-btn");

// 삭제 버튼이 존재하는 경우에만 이벤트 리스너를 추가
if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        // "article-id" ID를 가진 입력 필드에서 게시글 ID 값을 가져옴
        let id = document.getElementById('article-id').value;

        // REST API DELETE 요청을 사용하여 해당 ID의 게시글을 삭제
        fetch(`/api/articles/${id}`, {
            method: 'DELETE' // HTTP DELETE 메서드를 사용하여 요청 전송
        })
        .then(() => {
            // 삭제 완료 후 알림창을 띄움
            alert('삭제가 끝났습니다.');

            // 삭제 후 게시글 목록 페이지("/articles")로 이동
            location.replace('/articles');
        });
    });
}



// 수정 버튼이 존재하는 경우에만 이벤트 리스너를 추가
if (modifyButton) {
    modifyButton.addEventListener('click', () => {
        // 현재 URL의 쿼리 스트링에서 'id' 값을 가져옴
        let params = new URLSearchParams(location.search);
        let id = params.get('id'); // 예: ?id=3 → id 값은 3

        // REST API PUT 요청을 사용하여 해당 ID의 게시글을 수정
        fetch(`/api/articles/${id}`, {
            method: 'PUT', // HTTP PUT 메서드를 사용하여 요청 전송 (수정 요청)
            headers: {
                "Content-Type": "application/json", // 요청 본문의 데이터 타입을 JSON으로 설정
            },
            body: JSON.stringify({ // JSON 형식으로 데이터 변환
                title: document.getElementById('title').value, // 입력된 제목 값
                content: document.getElementById('content').value // 입력된 내용 값
            })
        })
        .then(() => {
            // 수정 완료 후 알림창을 띄움
            alert("수정이 되었습니다.");

            // 수정 후 해당 게시글 페이지(`/articles/{id}`)로 이동
            location.replace(`/articles/${id}`);
        });
    });
}

// 생성 버튼이 존재하는 경우에만 이벤트 리스너를 추가
if(createButton){
    createButton.addEventListener("click",(event) =>{

        // REST API POST 요청을 사용하여 새로운 게시글을 생성
        fetch("/api/articles", {
            method: "POST", // HTTP POST 메서드를 사용하여 요청 전송 (새로운 데이터 추가)
            headers: {
                "Content-Type": "application/json", // 요청 본문의 데이터 타입을 JSON으로 설정
            },
            body: JSON.stringify({ // JSON 형식으로 데이터 변환
                title:document.getElementById("title").value, // 입력된 제목 값
                content: document.getElementById("content").value, // 입력된 내용 값
            }),
        })
        .then(() =>{

            // 생성 완료 후 알림창을 띄움
            alert("등록 되었습니다.");

            // 생성 후 게시글 목록 페이지("/articles")로 이동
            location.replace("/articles");
        });
    });
}