<%-- 
    Document   : grapheData
    Created on : 6 déc. 2016, 18:57:15
    Author     : Marv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="./style.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BIG BROTHER IS WATCHING YOU</title>
        <!-- On charge JQuery -->
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<!-- On charge l'API Google -->
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		google.load("visualization", "1", {packages: ["corechart"]});
                google.charts.load("current", {packages:['corechart']});

		// Après le chargement de la page, on fait l'appel AJAX
		google.setOnLoadCallback(doAjax);
		
		function drawChart(dataArray) {
			var data = google.visualization.arrayToDataTable(dataArray);
			var options = {
				title: 'Produit : Cout total du produit',
				is3D: true
			};
			var chart = new google.visualization.PieChart(document.getElementById('piechart'));
			chart.draw(data, options);
		}
                function drawChart2(dataArray) {
			var data = google.visualization.arrayToDataTable(dataArray);
			var options = {
				title: 'Quantite achete par periode',
				is3D: true
			};
			var chart = new google.visualization.ColumnChart(document.getElementById('piechart2'));
			chart.draw(data, options);
		}

		// Afficher les ventes par client
		function doAjax() {
			$.ajax({
				url: "showInformation",
				dataType: "json",
				success: // La fonction qui traite les résultats
					function (result) {
						// On reformate le résultat comme un tableau
						var chartData = [];
						// On met le descriptif des données
						chartData.push(["nom", "total"]);
						for(var product in result) {
							chartData.push([product, result[product]]);
						}
						// On dessine le graphique
						drawChart(chartData);
					},
				error: showError
			});
                        $.ajax({
				url: "showInformation2",
				dataType: "json",
				success: // La fonction qui traite les résultats
					function (result) {
						// On reformate le résultat comme un tableau
						var chartData2 = [];
						// On met le descriptif des données
						chartData2.push(["date", "nbAchat"]);
						for(var product in result) {
							chartData2.push([product, result[product]]);
						}
						// On dessine le graphique
						drawChart2(chartData2);
					},
				error: showError
			});
		}

		// Fonction qui traite les erreurs de la requête
		function showError(xhr, status, message) {
			alert("Erreur: " + status + " : " + message);
		}

	</script>
    </head>
    <body>
        <div class="voila" id="piechart" style="width: 700px; height: 400px;"></div>
        <div  id="piechart2" style="width: 700px; height: 400px;"></div>
        <form action = "retourBonsDeCommandes" method = "POST">
            <input type="submit" value="Retour au bons de commandes">
        </form>
        <form action = "logout" method = "POST">
            <input type="submit" value="Se déconnecter">
        </form>
    </body>
</html>
