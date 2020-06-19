<?php
$name=$_GET['login'];
$pwd=$_GET['pwd'];
if ($name=="john" && $pwd=="123")
echo"John Doe";
elseif ($name=="jane" && $pwd=="456")
echo"Jane Doe";

else
echo"invalid";
?>
