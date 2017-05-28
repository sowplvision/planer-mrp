<?php
require "connect.php";

$query = "select nazwa from polprodukty;";

$result = pg_exec($con,$query);

$numRows = pg_numrows($result);

if($numRows > 0){
	for($i=0; $i<$numRows; $i++){
	$e=pg_result($result,$i,0);
	echo "$e; "; 
	}
}
else{
echo "Brak półproduktów. Dodaj jakieś przed działaniem na produktach.";
}
?>
