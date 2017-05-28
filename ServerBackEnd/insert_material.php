<?php
require "connect.php";

$nazwa = $_POST["nazwa"];
$opis = $_POST["opis"];
$czas = $_POST["czas"];
$naStanie = $_POST["naStanie"];
$partia = $_POST["partia"];
$cena = $_POST["cena"];

$query = "insert into materialy (nazwa, opis, czas_montazu, ilosc_na_stanie, wielkosc_partii, cena_za_sztuke) values ('$nazwa','$opis','$czas','$naStanie','$partia','$cena');";

$result = pg_exec($con,$query);

if($result){
echo "Pomyślnie dodano nowy materiał";
}
else{
echo "Błąd. Sprawdź czy któreś z pól nie zawiera błędów";
}
?>
