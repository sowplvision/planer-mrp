<?php
require "connect.php";

$userLogin = $_POST["login"];
$userPassword = $_POST["haslo"];

$query = "select * from uzytkownicy where login='$userLogin' and haslo='$userPassword';";

$result = pg_exec($con,$query);

$numRows = pg_numrows($result);

if($numRows > 0){
echo "Logowanie powiodło sie.";
}
else{
echo "Błąd. Sprawdź dane logowania.";
}
?>
