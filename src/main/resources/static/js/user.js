
const saveBtn = document.querySelector('.saveBtn');

if (saveBtn){
    saveBtn.addEventListener('click', () => {

        const $nickName = document.getElementById('nickName').value
        const $password = document.getElementById('password').value

        fetch(`/api/user/${$email}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                nickName: $nickName,
                password: $password
            })
        })
            .then(() =>{
                alert("수정 완료");
                location.replace(`/articles`)
            });
    });
}