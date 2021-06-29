<?php
session_start();

require_once './config/database.php';
require_once './config/config.php';
spl_autoload_register(function ($class_name) {
  require './app/models/' . $class_name . '.php';
});

$pathURI = explode('-', $_SERVER['REQUEST_URI']);
$id = $pathURI[count($pathURI) - 1];

//$id = $_GET['id'];
$productModel = new ProductModel();

$item = $productModel->getProductById($id);
?>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
    integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
  <link rel="stylesheet" href="./public/css/style.css">
  <style>
  .rating {
    font-size: 50px;
  }

  .cursor {
    cursor: pointer;
  }
  </style>
</head>

<body>
  <div class="container">
    <div class="row">
      <div class="col-md-4">
        <?php
        $mainPhoto = explode(',', $item['product_photo']);
        ?>

        <img src="/<?php echo BASE_URL; ?>/public/images/<?php echo $mainPhoto[0]; ?>" class="img-fluid" alt="...">

        <?php
        foreach ($mainPhoto as $photo) {
        ?>

        <img src="/<?php echo BASE_URL; ?>/public/images/<?php echo $photo; ?>" class="img-fluid" alt="..."
          style="width: 50px;">

        <?php
        }
        ?>
      </div>
      <div class="col-md-8">
        <div style="display:none" id="product_id"><?php echo $item['id'] ?></div>
        <h1><?php echo $item['product_name'] ?></h1>
        <div class="averageStar d-flex text-warning">

        </div>
        <p>Price : <?php echo $item['product_price'] ?></p>
        <p>
          <?php echo $item['product_description'] ?>
        </p>
        <p><?php echo $item['product_view'] ?></p>
        <button class="btn d-flex btn-primary">
          <i class="far fa-thumbs-up fa-3x mr-5 text-warning" id="btnLike" style="cursor:pointer"></i>
          <div id="total_like" style="line-height: 50px;"></div>
        </button>
        <hr>
      </div>
    </div>
    <h4>Comment and rating</h4>
    <div class="input_user_name mb-2">
      <input placeholder="Name" type="text" id="user_name">
    </div>
    <div class="input_comment_detail">
      <textarea placeholder="Comment" id="comment_detail" style="width: 100%;"></textarea>
    </div>
    <div class="rate d-flex text-warning">
      <div class="rating cursor">☆</div>
      <div class="rating cursor">☆</div>
      <div class="rating cursor">☆</div>
      <div class="rating cursor">☆</div>
      <div class="rating cursor">☆</div>
    </div>
    <button class="btn btn-primary" id="addComment">Gửi</button>
    <div class="all_comment">
    </div>
  </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous">
  </script>
  <script src="https://kit.fontawesome.com/38b1eecda0.js" crossorigin="anonymous"></script>
  <script src="../public/js/ajax_product.js">
  </script>
</body>

</html>