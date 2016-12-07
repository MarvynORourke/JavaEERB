<%-- 
    Document   : bonsDeCommandes
    Created on : 16 nov. 2016, 14:48:45
    Author     : rroch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bons de commandes</title>
        <link href="./style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Vous êtes authentifié. Voici vos bons de commandes :</h1>
        <div>
            <table border="1" class="table-fill">
                <tr><th>Numéro de commande</th>
                    <th>Quantité</th>
                    <th>Frais de port</th>
                    <th>ID de produit</th>
                    <th>Date de vente</th>
                    <th>Date de transport</th>
                    <th>Compagnie de fret</th>
                    <th>Modifier/Ajouter</th>
                    <th>Supprimer</th></tr>
                <c:forEach var="commande" items="${commandes}" varStatus = "status">
                    <tr>
                        <td>${commande.orderNum}</td>
                        <td>${commande.quantite}</td>
                        <td>${commande.shippingCost}</td>
                        <td>${commande.productID}</td>
                        <td>${commande.saleDate}</td>
                        <td>${commande.shippingDate}</td>
                        <td>${commande.freightCompagny}</td>
                    <form action = "mediumModification" method = "POST" id="formModify">
                        <input type="hidden" value='${commande.orderNum}' name="orderNum" >
                        <td><input type="submit" value="Modifier" form="formModify"></td>
                    </form>
                    <form action = "delete" method = "POST">
                        <input type="hidden" value='${commande.orderNum}' name="orderNum" >
                        <td><input type="submit" value="Supprimer"></td>
                    </form>
                    </tr>
                </c:forEach>
                <tr>
                <form action = "add" method = "POST">
                    <td><input type="text" name="orderNum"></td>
                    <td><input type="text" name="quantite"></td>
                    <td><input type="text" name="shippingCost"></td>
                    <td><input type="text" name="productID"></td>
                    <td><input type="text" name="saleDate"></td>
                    <td><input type="text" name="shippingDate"></td>
                    <td><input type="text" name="freightCompagny"></td>
                    <td><input type="submit" value="Ajouter"></td>
                </form>
                </tr>
            </table>
            <form action = "grapheData.jsp" method = "POST">
                <td><input type="submit" value="Graphe de données"></td>
            </form>
    </div>
</body>
</html>