<?php
require "connect.php";

$query = "select cena_za_sztuke from produkty where id_produktu='1';";

$result = pg_exec($con,$query);

$numRows = pg_numrows($result);

if($numRows > 0){
	for($i=0; $i<$numRows; $i++){
	$e=pg_result($result,$i,0);
	echo "$e"; 
	}
}
else{
echo "Brak produktów lub nieoczekiwany błąd";
}
?>
