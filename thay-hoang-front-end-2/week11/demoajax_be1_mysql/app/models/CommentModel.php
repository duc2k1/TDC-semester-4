<?php
class CommentModel extends Db
{
    // Thêm comment
    public function addComment($userName,$commentDetail,$productId,$starNumber)
    {
        $createAt=date("Y-m-d");
        echo $starNumber;
        $sql = parent::$connection->prepare("INSERT INTO `comments` (`comment_detail`, `start_number`, `product_id`, `created_at`, `user_name`) VALUES ('$commentDetail', $starNumber, $productId,'$createAt','$userName');");
        return $sql->execute();
    }   
    public function loadComment($productId){
        $sql = parent::$connection->prepare("SELECT * FROM `comments` WHERE product_id=?");
        $sql->bind_param('i', $productId);
        return parent::select($sql);
    }
}
    