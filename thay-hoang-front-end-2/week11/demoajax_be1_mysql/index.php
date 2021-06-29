<?php
require_once './config/database.php';
spl_autoload_register(function ($class_name) {
    require './app/models/' . $class_name . '.php';
});

$productModel = new ProductModel();

$totalRow = $productModel->getTotalRow();
$perPage = 3;
$page = 1;
if(isset($_GET['page'])) {
    $page = $_GET['page'];
}
//$page = isset($_GET['page']) ? $_GET['page'] : 1;

$productList = $productModel->getProductsByPage($perPage, $page);

$categoryModel = new CategoryModel();
$categoryList = $categoryModel->getCategories();

$pageLinks = Pagination::createPageLinks($totalRow, $perPage, $page);
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
  <link rel="stylesheet" href="./public/css/style.css">
</head>

<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <?php
                foreach ($categoryList as $item) {
                ?>
          <li class="nav-item">
            <a class="nav-link"
              href="category.php?id=<?php echo $item['id']; ?>"><?php echo $item['category_name']; ?></a>
          </li>
          <?php
                }
                ?>
        </ul>
        <form class="d-flex form-search">
          <input id="searchBox" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <div class="list-group" id="search-result">

          </div>
        </form>
      </div>
    </div>
  </nav>
  <div class="container">
    <div class="row">
      <!-- danh mục -->
      <div class="col-md-3">
        <ul>
          <?php
                foreach ($categoryList as $item) {
                ?>
          <li>
            <label>
              <p class="item"><?php echo $item['category_name']; ?></p>
              <input type="checkbox" class="checkboxCategory" style="display:none">
            </label>

          </li>
          <?php
                }
                ?>
        </ul>
      </div>
      <!-- sản phẩm -->
      <div class="col-md-9">
        <div class="row" id="productByCategory">
        </div>
        <div class="d-flex justify-content-center">
          <button class="btn btn-primary" id="btnLoadMore">
            Load more...
          </button>
          <!-- <button class="btn btn-primary" type="button" disabled id="btnLoading">
            <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
            Loading...
          </button> -->
        </div>

      </div>
    </div>

    <!-- Thong tin san pham -->
    <!-- Modal -->
    <div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="product-name">Modal title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body" id="product-description">
            ...
          </div>
          <img src="" id="product-image">
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous">
  </script>
  <script src="./public/js/ajax.js"></script>
</body>

</html>