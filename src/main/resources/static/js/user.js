
const saveBtn = document.querySelector('.saveBtn');

if (saveBtn){
    saveBtn.addEventListener('click', () => {

        const $nickName = document.getElementById('nickName').value
        const $password = document.getElementById('password').value

        fetch(`/api/user/${$userId}`, {
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

const $profileImg = document.querySelector('.socialImg')

if ($profileImg){
    $profileImg.addEventListener('click', () => {
        console.log('clicking')

        document.querySelector('.input-file').click()
    })
}

async function existsId(id){
    const response = await axios.get(`/api/exist/id/${id}`)
    return response.data
}

async function existsNicName(nicName){
    const response = await axios.get(`/api/exist/nicName/${nicName}`)
    return response.data
}

async function existsEmail(email){
    const response = await axios.get(`/api/exist/email/${email}`)
    return response.data
}