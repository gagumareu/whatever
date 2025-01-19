
// 게시물 작성 API
const saveArticleBtn = document.getElementById('create-btn');

if (saveArticleBtn){
    saveArticleBtn.addEventListener('click', () => {

        const titleValue = document.querySelector('.article-title-value').value
        const categoryValue = document.querySelector('.article-category-value').value
        const writerValue = document.querySelector('.article-writer-value').value
        const contentValue = document.getElementById('summernote').value

        if (!titleValue){
            alert("제목을 입력해 주세요");
            return;
        }else if (!categoryValue){
            alert("카테고리를 선택해 주세요")
            return;
        }else if (!writerValue){
            alert("작성자를 입력해 주세요")
            return;
        }else if(!contentValue){
            alert("내용을 입력해 주세요")
            return;
        }

        let str = '';
        const form = document.querySelector('form');
        const uploadli = document.querySelector('.uploadResultDiv').getElementsByTagName('li');
        const fileHidden = document.querySelector('.fileHidden');

        for (let i = 0; i < uploadli.length; i++){

            const fileName = uploadli[i].getAttribute('data-s3url')
            console.log(fileName)

            str += `<input type="hidden" name="fileNames" value="${fileName}">`

            console.log(uploadli[i]);
        }
        fileHidden.innerHTML = str;
        form.submit();
    });
}


// 게시물 삭제 API
const deleteArticleBtn = document.getElementById('article-delete-btn');

if (deleteArticleBtn){
    deleteArticleBtn.addEventListener('click', () => {

        if (confirm("삭제하시겠습니까?")){
            let id = document.getElementById('article-id').value;
            console.log(id);
            fetch(`/api/article/${id}`, {
                method: "DELETE"
            }).then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/articles');
            });
        }
    });
}


// 게시글 업데이트 API
const updateArticleBtn = document.getElementById('update-btn');

if (updateArticleBtn){
    updateArticleBtn.addEventListener('click', () => {

        let param = new URLSearchParams(location.search);
        let id = param.get('id');
        let fileList = []

        const titleValue = document.querySelector('.article-title-value').value
        const categoryValue = document.querySelector('.article-category-value').value
        const contentValue = document.getElementById('summernote').value

        if (!titleValue){
            alert("제목을 입력해 주세요");
            return;
        }else if (!categoryValue){
            alert("카테고리를 선택해 주세요")
            return;
        }else if(!contentValue){
            alert("내용을 입력해 주세요")
            return;
        }

        const uploadLi = document.querySelector('.uploadResultUL').getElementsByTagName('li');

        for (let i = 0; i < uploadLi.length; i++){

            let s3Url = uploadLi[i].getAttribute('data-s3url');

            console.log(s3Url)

            fileList.push(s3Url)
        }

        console.log("category: " + categoryValue)
        console.log("title: " + titleValue)

        // fetch(`/api/article/${id}`, {
        //     method: "PUT",
        //     headers: {
        //         "Content-Type": "application/json",
        //     },
        //     body: JSON.stringify({
        //         // content: document.getElementById('summernote').value,
        //         // writer: document.querySelector('.article-writer-value').value,
        //         // category: document.querySelector('.article-category-value').value,
        //         content: contentValue,
        //         category: categoryValue,
        //         fileNames: fileList
        //     })
        // })
        // .then(() => {
        //     alert("수정 완료");
        //     location.replace(`/article/${id}`)
        // });
    });
}

function formDate(date){

    const data = new Date(date);

    return data.getFullYear() + "-" + (data.getMonth() +1 ) + "-" + data.getDate();

}