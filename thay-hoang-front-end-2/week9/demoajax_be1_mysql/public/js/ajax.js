//load more

const loadMore = document.querySelector("#loadMore")
let allItemProduct = null
const productByCategory = document.querySelector("#productByCategory");
function loadMoreItem() {
  let loadMoreCount = 3;
  let temp = [...allItemProduct]
  if (loadMoreCount >= temp.length)
    loadMore.style.display = "none";
  else
    loadMore.style.display = "block";
  productByCategory.innerHTML = ""
  for (let i = 0; i < loadMoreCount; i++) {
    productByCategory.innerHTML += `<div class="col-md-4 item-product">${temp[i].innerHTML}</div>`;
  }

  loadMore.addEventListener("click", () => {
    loadMoreCount += 3
    if (loadMoreCount >= temp.length)
      loadMore.style.display = "none";
    else
      loadMore.style.display = "block";
    productByCategory.innerHTML = ""
    for (let i = 0; i < loadMoreCount; i++) {
      productByCategory.innerHTML += `<div class="col-md-4 item-product">${temp[i].innerHTML}</div>`;
    }

  })
}

//search
const searchBox = document.querySelector("#searchBox");
searchBox.addEventListener("keyup", () => {
  searchByKeyword(searchBox.value);
});
async function searchByKeyword(key) {
  // Buoc 1:
  const url = "getProductByKey.php";
  const data = {
    key,
  };
  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "Accept": "application/json;charset=UTF-8",
    },
    body: JSON.stringify(data),
  });

  // Buoc 2:
  const result = await response.json();
  //hiển thị giao diện
  const divResult = document.querySelector("#search-result")
  console.log(result)
  divResult.innerHTML = ""
  result.forEach(item => {
    let keyReg = new RegExp(key, "gi")
    productName = item.product_name.replace(keyReg, `<b>${key}</b>`)
    divResult.innerHTML += `
      <div class="list-group-item list-group-item-action border-1">
        <a style="color:black;text-decoration:none;" href="product.php/a-${item.id}">
          ${productName}
        </a>
      </div>
    `
  });
}
//trash
async function getProduct(productId) {
  // Buoc 1:
  const url = "productdetail.php";
  const data = {
    id: productId,
  };
  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "Accept": "application/json;charset=UTF-8",
    },
    body: JSON.stringify(data),
  });

  // Buoc 2:
  const result = await response.json();

  // Hien thi giao dien
  const modalProductName = document.querySelector("#product-name");
  const modalProductDescription = document.querySelector(
    "#product-description"
  );
  const modalProductImage = document.querySelector("#product-image");
  modalProductName.innerHTML = `${result.product_name}`;
  modalProductDescription.innerHTML = `${result.product_description}`;
  modalProductImage.src = `./public/images/${result.product_photo}`;
  // Hien thi giao dien
  var myModal = new bootstrap.Modal(document.getElementById("productModal"));
  myModal.show();
}
//lấy sản phẩm theo danh mục
async function getProductsByCategory(categoryId, checked) {
  // Buoc 1:
  const url = "getProductsByCategory.php";
  const data = {
    id: categoryId,
  };
  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "Accept": "application/json;charset=UTF-8",
    },
    body: JSON.stringify(data),
  });
  // Buoc 2:
  const result = await response.json();
  //hiển thị giao diện
  //thay doi background item
  const productByCategory = document.querySelector("#productByCategory");
  const tagP = document.querySelectorAll("p");
  let tam = "";
  if (checked) {
    tagP[categoryId - 1].classList.add("actived");
    for (let i = 0; i < result.length; i++) {
      tam += `
      <div class="col-md-4 item-product">
      <div class="card">
        <a href="#">
          <img src="./public/images/${result[i].product_photo
        }" class="card-img-top" alt="...">
        </a>
        <div class="card-body">
          <h5 class="card-title" onclick="getProduct(${i + 1})">
          ${result[i].product_name}
          </h5>
          <p class="card-text">${result[i].product_price}</p>
        </div>
      </div>
    </div>
    `;
    }
    arrHTML[categoryId - 1] = tam;
    productByCategory.innerHTML = arrHTML.join("");
    console.log(arrHTML);
  } else {
    tagP[categoryId - 1].classList.remove("actived");
    arrHTML[categoryId - 1] = null;
    productByCategory.innerHTML = arrHTML.join("");
    console.log(arrHTML.join(""));
  }
  allItemProduct = document.querySelectorAll(".item-product")
  loadMoreItem()
}
const arrCheckBox = document.querySelectorAll(".checkboxCategory");
let arrHTML = [];
for (let i = 0; i < arrCheckBox.length; i++)
  arrCheckBox[i].addEventListener("change", () => {
    getProductsByCategory(i + 1, arrCheckBox[i].checked);
    showAllProduct();
  });
async function getAllProduct() {
  // Buoc 1:
  const url = "getAllProduct.php";
  const response = await fetch(url);

  // Buoc 2:
  const result = await response.json();
  console.log(result);
  //hiển thị giao diện

  let tam = "";

  for (let i = 0; i < result.length; i++) {
    tam += `
       <div class="col-md-4 item-product">
       <div class="card">
         <a href="#">
           <img src="./public/images/${result[i].product_photo
      }" class="card-img-top" alt="...">
         </a>
         <div class="card-body">
           <h5 class="card-title" onclick="getProduct(${i + 1})">
           ${result[i].product_name}
           </h5>
           <p class="card-text">${result[i].product_price}</p>
         </div>
       </div>
     </div>
     `;
  }
  productByCategory.innerHTML = tam;
  allItemProduct = document.querySelectorAll(".item-product")
  loadMoreItem()
}
function showAllProduct() {
  let check = false;
  for (let i = 0; i < arrCheckBox.length; i++)
    if (arrCheckBox[i].checked) {
      check = true;
      break;
    }
  console.log(check);
  if (check == false) getAllProduct();

}
showAllProduct();
