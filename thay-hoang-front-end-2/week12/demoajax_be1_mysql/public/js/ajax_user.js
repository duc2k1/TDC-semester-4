const btnSignIn = document.querySelector("#btnSignIn")
const myModal = new bootstrap.Modal(document.getElementById("signInModal"));
const btnSignInWeb = document.querySelector("#btnSignInWeb")
const signInUserName = document.querySelector("#sign_in_user_name")
btnSignIn.addEventListener("click", () => {
  //show modal
  myModal.show();
})
btnSignInWeb.addEventListener("click", () => {
  //tạo seesion
  sessionUserSignIn(signInUserName.value)
})
async function sessionUserSignIn(userName) {
  // Buoc 1:
  console.log("user sign in")
  const url = "./userSignIn.php";
  const data = {
    userName: userName,
  };
  const response = await fetch(url, {
    method: "POST",
    body: JSON.stringify(data),
  });
}
