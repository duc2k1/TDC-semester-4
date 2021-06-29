<?php
require_once './config/database.php';
require_once './config/config.php';
spl_autoload_register(function ($class_name) {
    require './app/models/' . $class_name . '.php';
});
$input = json_decode(file_get_contents('php://input'), true);
$userName = $input['userName'];
$commentDetail = $input['commentDetail'];
$productId = $input['productId'];
$starNumber = $input['starNumber'];
$commentModel = new CommentModel();
$commentModel->addComment($userName,$commentDetail,$productId,$starNumber);