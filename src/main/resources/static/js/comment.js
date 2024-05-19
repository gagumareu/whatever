
async function getList({$articleId, page, size, goLast}){

    console.log(goLast)
    console.log("getList")

    const result = await axios.get(`/api/comment/list/${$articleId}`, {params: {page, size}})

    if(goLast){

        console.log("if goLast");

        const $total = result.data.total
        const $lastPage = parseInt(Math.ceil($total/size))

        return getList({$articleId: $articleId, page: $lastPage, size: size})

        console.log($lastPage)
        console.log(size)

    }

    console.log(result.data)

    return result.data
}

async function addComment(commentObj){
    const response = await axios.post('/api/comment', commentObj)
    return response.data
}

async function getComment(id){
    const response = await axios.get(`/api/comment/${id}`)
    return response.data
}

async function updateComment(commentObj){
    const response = await axios.put(`/api/comment/${commentObj.id}`, commentObj)
    return response.data
}

async function removeComment(id){
    const response = await axios.delete(`/api/comment/${id}`)
    return response.data
}