<?php
require "connect.php";

$nazwa = $_POST["nazwa"];
$opis = $_POST["opis"];
$czas = $_POST["czas"];
$naStanie = $_POST["naStanie"];
$partia = $_POST["partia"];

$query = "insert into polprodukty (nazwa, opis, czas_montazu, ilosc_na_stanie, wielkosc_partii) values ('$nazwa','$opis','$czas','$naStanie','$partia');";

$result = pg_exec($con,$query);

if($result){
echo "Pomyślnie dodano nowy półprodukt";
}
else{
echo "Błąd. Sprawdź czy któreś z pól nie zawiera błędów";
}
?>
