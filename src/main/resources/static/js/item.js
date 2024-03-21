const serialInput = document.getElementById("serial_input");
const addItemButton = document.getElementById('add_item_btn');

let min = 0;
console.log("min1: " + min)


if (addItemButton) {
    addItemButton.addEventListener("click", (event) => {

        if (min >= 33){
            alert("추가 금지")
            return false;
        }

        let serial = serialInput.value;

        console.log(serial);

        fetch(`/api/item/${serial}`)
            .then(response => {
                if (!response.ok){
                    throw new Error('error 발생');
                }
                console.log(response);
                return response.json();
            })
            .then(data => {
                console.log(data);
                loadPriceCard(data);
                min = min + 1;
                countingItems(min);

            })
            .catch(error => {
                console.log(error);
            })
    });
}

function loadPriceCard(data){

    const itemDiv = document.querySelector('.item-info');

    let str = '';

    str += `<div class="item-box">
                <div class="serial">${data.serial}</div>
                <div class="name">${data.name}</div>
                <div class="color">${data.color}</div>
                <div class="size">${data.size}</div>
                <div class="price">${data.price}원</div>
            </div>`

    // itemDiv.innerHTML = str
    itemDiv.insertAdjacentHTML("beforeend",str)

}

countingItems(min);

function countingItems(min){

    console.log("hello2 ");

    let str = '';

    console.log("min2: " + min)

    let minDiv = document.querySelector('.theNumberOfMin');

    // str = '<span>${min}</span>'

    minDiv.innerHTML = min

}

