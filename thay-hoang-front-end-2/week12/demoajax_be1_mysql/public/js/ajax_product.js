const addComment = document.querySelector("#addComment");
const userName = document.querySelector("#user_name");
const commentDetail = document.querySelector("#comment_detail");
const productId = document.querySelector("#product_id");
const rating = document.querySelectorAll(".rating");
const averageStar = document.querySelector(".averageStar");
let starNumber = 0;
let averageStarNumber = 0;
const btnLike = document.querySelector("#btnLike")
const totalLike = document.querySelector("#total_like")
//
//load like
loadLike(productId.innerHTML)
async function loadLike(productId) {
  // Buoc 1:
  let url = "../loadLike.php";
  const data = {
    productId: productId,
  };
  let response = await fetch(url, {
    method: "POST",
    body: JSON.stringify(data),
  });
  // Buoc 2:
  //hiển thị giao diện
  let result = await response.json();
  totalLike.innerHTML = result
  //check user like or unlike
  url = "../loadLikeOrDislike.php";
  response = await fetch(url, {
    method: "POST",
    body: JSON.stringify(data),
  })
  result = await response.json()
  if (result) {
    btnLike.classList.remove("far")
    btnLike.classList.add("fas")
  } else {
    btnLike.classList.remove("fas")
    btnLike.classList.add("far")
  }
}
//button like
btnLike.addEventListener("click", () => {
  if (btnLike.classList.contains('fas')) {
    btnLike.classList.remove("fas")
    btnLike.classList.add("far")
    //remove like
    removeLike(productId.innerHTML)
    //load like 
    totalLike.innerHTML--
  } else {
    btnLike.classList.remove("far")
    btnLike.classList.add("fas")
    //add like
    addLike(productId.innerHTML)
    //load like 
    totalLike.innerHTML++
  }
})
async function removeLike(productId) {
  // Buoc 1:
  console.log("remove like")
  const url = "../removeLike.php";
  const data = {
    productId: productId,
  };
  const response = await fetch(url, {
    method: "POST",
    body: JSON.stringify(data),
  });
}
async function addLike(productId) {
  // Buoc 1:
  console.log("add like")
  const url = "../addLike.php";
  const data = {
    productId: productId,
  };
  const response = await fetch(url, {
    method: "POST",
    body: JSON.stringify(data),
  });
}
//rating
rating.forEach((item, id) => {
  item.addEventListener("click", () => {
    for (let i = 0; i < rating.length; i++) {
      rating[i].innerHTML = "☆";
    }
    for (let i = 0; i <= id; i++) {
      rating[i].innerHTML = "★";
    }
    starNumber = id + 1;
  });
});
//load comment and average star
async function loadCommentByProductId(productId) {
  // Buoc 1:
  const url = "../loadComment.php";
  const data = {
    productId: productId,
  };
  console.log(productId);
  const response = await fetch(url, {
    method: "POST",
    body: JSON.stringify(data),
  });
  // Buoc 2:
  //hiển thị giao diện
  const result = await response.json();
  const allComment = document.querySelector(".all_comment");

  result.forEach((comment) => {
    let s = `
    <div class="cmt">
      <hr>
      <h3 class="cmt_user_name">${comment.user_name}</h3>
      <div class="cmt_comment_detail">${comment.comment_detail}</div>
      <b class="cmt_created_at">${comment.created_at}</b>
      <br>   
      <div class="d-flex text-warning">
  `;
    for (let i = 1; i <= comment.start_number; i++) {
      s += '<div class="rating">★</div>';
    }
    for (let i = 1; i <= 5 - comment.start_number; i++) {
      s += '<div class="rating">☆</div>';
    }
    s += "<hr></div></div>";
    allComment.innerHTML = s + allComment.innerHTML;
    //
    averageStarNumber += comment.start_number;
  });

  averageStarNumber = Math.round(averageStarNumber / result.length);
  //show average star
  for (let i = 1; i <= averageStarNumber; i++)
    averageStar.innerHTML += '<div class="rating">★</div>';
  for (let i = 1; i <= 5 - averageStarNumber; i++)
    averageStar.innerHTML += '<div class="rating">☆</div>';
}
loadCommentByProductId(productId.innerHTML);
//comment
addComment.addEventListener("click", () => {
  if (userName.value !== "" && commentDetail.value !== "")
    addCommentToDatabase(
      userName.value,
      commentDetail.value,
      productId.innerHTML,
      starNumber
    );
  else
    alert("Vui lòng nhập thông tin")
});
async function addCommentToDatabase(
  userName,
  commentDetail,
  productId,
  starNumber
) {
  // Buoc 1:
  const url = "../addComment.php";
  const data = {
    userName: userName,
    commentDetail: commentDetail,
    productId: productId,
    starNumber: starNumber,
  };
  console.log(productId);
  const response = await fetch(url, {
    method: "POST",
    body: JSON.stringify(data),
  });
  // Buoc 2:
  //hiển thị giao diện
  const allComment = document.querySelector(".all_comment");
  const currentTime = new Date();
  let s = `
    <div class="cmt">
    <hr>
    <h3 class="cmt_user_name">${userName}</h3>
    <div class="cmt_comment_detail">${commentDetail}</div>
    <b class="cmt_created_at">${currentTime.getFullYear()}-${currentTime.getMonth() + 1
    }-${currentTime.getDate()}</b>
    <br>
    <div class="d-flex text-warning">
  `;
  console.log("star", starNumber);
  for (let i = 1; i <= starNumber; i++) {
    s += '<div class="rating">★</div>';
  }
  for (let i = 1; i <= 5 - starNumber; i++) {
    s += '<div class="rating">☆</div>';
  }
  s += "<hr></div></div>";
  allComment.innerHTML = s + allComment.innerHTML;
}
