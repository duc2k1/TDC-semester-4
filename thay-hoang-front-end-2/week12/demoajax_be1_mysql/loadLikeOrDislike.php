<?php
session_start();
require_once './config/database.php';
require_once './config/config.php';
spl_autoload_register(function ($class_name) {
    require './app/models/' . $class_name . '.php';
});
$input = json_decode(file_get_contents('php://input'), true);
$productId = $input['productId'];
if (isset($_SESSION['like_by_' . $productId]))
    echo json_encode($_SESSION['like_by_' . $productId]);
else
    echo json_encode(false);