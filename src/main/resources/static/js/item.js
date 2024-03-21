
const $serialInput = document.getElementById("serial_input");
const $addItemButton = document.getElementById('add_item_btn');

let $min = 0;
console.log("min1: " + $min)

countingItems($min);


if ($addItemButton) {
    $addItemButton.addEventListener("click", (event) => {

        if ($min >= 33){
            alert("추가 금지")
            return false;
        }

        let serial = $serialInput.value;

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
                $min = $min + 1;
                countingItems($min);

            })
            .catch(error => {
                console.log(error);
            })
    });
}

function loadPriceCard(data){

    const $temDiv = document.querySelector('.item-box-wrapper');

    let str = '';

    let $price = data.price;

    str += `<div class="item-box">
                <span class="serial">${data.serial}</span>
                <span class="name">${data.name}</span>
                <span class="color">${data.color}</span>
                <span class="size">${data.size}</span>
                <span class="price">${new Intl.NumberFormat().format($price)}원</span>
            </div>`

    // itemDiv.innerHTML = str
    $temDiv.insertAdjacentHTML("beforeend",str)

}


function countingItems(min){

    let minDiv = document.querySelector('.theNumberOfMin');

    minDiv.innerHTML = min

}


const $itemWrapper = document.querySelector('.item-box-wrapper');
const $itemBox = document.querySelector('.item-box');

$itemWrapper.addEventListener("click", function (e){

    e.preventDefault()

    console.log("clicking")

    const target = e.target.closest('.item-box');

    target.remove();

},false);





