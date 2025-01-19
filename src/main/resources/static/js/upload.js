async function uploadToServer(formObj){

    console.log("upload to Server,,")
    console.log(formObj)

    const response = await axios({
        method: 'POST',
        url: '/uploadAjax',
        data: formObj,
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });

    return response.data
}

async function removeFileToServer(uuid, fileName){

    const response = await axios.delete(`/remove/${uuid}_${fileName}`)

    return response.data
}