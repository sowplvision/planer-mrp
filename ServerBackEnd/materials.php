<?php
require "connect.php";

$query = "select nazwa from materialy;";

$result = pg_exec($con,$query);

$numRows = pg_numrows($result);

if($numRows > 0){
	for($i=0; $i<$numRows; $i++){
	$e=pg_result($result,$i,0);
	echo "$e; "; 
	}
}
else{
echo "Brak materiałów. Dodaj jakieś przed działaniem na półproduktach.";
}
?>
