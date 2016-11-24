<%-- 
    Document   : bonsDeCommandes
    Created on : 16 nov. 2016, 14:48:45
    Author     : rroch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bons de commandes</title>
    </head>
    <body>
        <h1>Vous êtes authentifié. Voici vos bons de commandes :</h1>
        <div>
            <table border="1">
                <tr><th>Numéro de commande</th><th>Taux</th><th>Action</th></tr>
                <c:forEach var="commande" varStatus = "status" items="${commandes}">
                    <tr>
                        <td>${commande.orderNum}</td><td>Lol</td><td>Mdr</td>
                        <%--<td><a href="?action=DELETE&code=${commandes.}">delete</a></td>--%>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
