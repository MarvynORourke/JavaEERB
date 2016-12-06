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
                        <th>Compagnie de fret</th></tr>
                    <c:forEach var="commande" items="${commandes}" varStatus = "status">
                        <tr>
                            <form action = "modificationBonDeCommande.jsp" method = "POST">
                                <td name = 'orderNum'>${commande.orderNum}</td>
                                <td name = 'quantite'>${commande.quantite}</td>
                                <td name = 'shippingCost'>${commande.shippingCost}</td>
                                <td name = 'productID'>${commande.productID}</td>
                                <td name = 'saleDate'>${commande.saleDate}</td>
                                <td name = 'shippingDate'>${commande.shippingDate}</td>
                                <td name = 'freightCompagny'>${commande.freightCompagny}</td>
                                <%--<td><a href="?action=DELETE&code=${commande}">Supprimer</a></td>--%>
                                <td><input type="submit" value="Modifier"></td>
                                </form>
                        <form action = "delete" method = "POST">
                            <td><input type="submit" value="Supprimer"></td>
                        </form>
                        </tr>
                    </c:forEach>
                    <form action = "add" method = "POST">
                    <input type="submit" value="Ajouter">
                    </form>
                </table>
            </form>
        </div>
    </body>
</html>