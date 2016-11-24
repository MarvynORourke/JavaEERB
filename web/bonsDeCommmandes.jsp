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
    </head>
    <body>
        <h1>Vous êtes authentifié. Voici vos bons de commandes :</h1>
        <div>
                <table border="1">
                    <tr><th>Numéro de commande</th>
                        <th>Quantité</th>
                        <th>Frais de port</th>
                        <th>ID de produit</th>
                        <th>Date de vente</th>
                        <th>Date de transport</th>
                        <th>Compagnie de fret</th></tr>
                    <c:forEach var="commande" items="${commandes}" varStatus = "status">
                        <tr>
                            <form action = "authentificationController" method = "POST">
                                <td>${commande.orderNum}</td>
                                <td>${commande.quantite}</td>
                                <td>${commande.shippingCost}</td>
                                <td>${commande.productID}</td>
                                <td>${commande.saleDate}</td>
                                <td>${commande.shippingDate}</td>
                                <td>${commande.freightCompagny}</td>
                                <%--<td><a href="?action=DELETE&code=${commande}">Supprimer</a></td>--%>
                                <td><input type="submit" value="Modifier"></td>
                                <td><input type="submit" value="Supprimer"></td>
                            </form>
                        </tr>
                    </c:forEach>
                    <input type="submit" value="Ajouter">
                </table>
            </form>
        </div>
    </body>
</html>