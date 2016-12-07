<%-- 
    Document   : modificationBonDeCommande
    Created on : 6 déc. 2016, 10:05:26
    Author     : Romain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./style.css" rel="stylesheet" type="text/css">
        <title>Modification d'un bon de commande</title>
    </head>
    <body>
        <form class="ajout" action = "modification" method = "POST">
            Numéro de commande:<br>
            <input type="text" name="orderNum" value="${orderNum}" onfocus=""><br>
            Quantité :<br>
            <input type="text" name="quantite" value="${quantite}" onfocus=""><br>
            Fraits de port :<br>
            <input type="text" name="shippingCost" value="${shippingCost}" onfocus=""><br>
            ID du produit (anciennement ${productID}) :<br>
            <select name="productID" >
                
                <c:forEach var="produit" items="${produits}" varStatus = "status">
                    <option value="${produit}"> ${produit} </option>
                </c:forEach>
            </select><br>
            Date de la vente :<br>
            <input type="text" name="saleDate" value="${saleDate}" onfocus=""><br>
            Date de transport :<br>
            <input type="text" name="shippingDate" value="${shippingDate}" onfocus=""><br>
            Compagnie de transport :<br>
            <input type="text" name="freightCompagny" value="${freightCompagny}" onfocus=""><br>
            <input type="submit" value="Valider">
        </form>
            <form action = "logout" method = "POST">
            <input type="submit" value="Se déconnecter">
        </form>
    </body>
</html>
