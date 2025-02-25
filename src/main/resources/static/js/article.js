// "delete-btn" ID를 가진 요소를 가져옴 (삭제 버튼)
const deleteButton = document.getElementById('delete-btn');

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
