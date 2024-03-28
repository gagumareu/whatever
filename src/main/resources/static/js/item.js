
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
                <div class="box-margin">
                    <div class="item-name"><span class="name">${data.name}</span></div>
                    <div class="item-color"><span class="color">${data.color}</span></div>
                    <div class="item-size"><span class="size">${data.size}</span></div>
                    <div class="emptyBox"></div>
                    <div class="item-JanNPrice">
                        <div class="jan-code"><span class="serial">${data.serial}</span> </div>
                        <div class="jan-empty1"></div>
                        <div class="jan-price"><span class="price">${new Intl.NumberFormat().format($price)}</span></div>
                        <div class="jan-won"><span>원</span></div>
                    </div>
                </div>
            </div>`

    // itemDiv.innerHTML = str
    $temDiv.insertAdjacentHTML("beforeend",str)

}


function countingItems(min){

    let minDiv = document.querySelector('.theNumberOfMin');

    minDiv.innerHTML = min

}


const $itemWrapper = document.querySelector('.item-box-wrapper');

$itemWrapper.addEventListener("click", function (e){

    e.preventDefault()

    const target = e.target.closest('.item-box');

    target.remove()

    $min = $min -1

    countingItems($min)

},false);





