<%-- 
    Document   : modificationBonDeCommande
    Created on : 6 déc. 2016, 10:05:26
    Author     : Romain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modification d'un bon de commande</title>
    </head>
    <body>
        <form action = "modification" method = "POST">
            Numéro de commande:<br>
            <input type="text" name="orderNum" value=${orderNum}><br>
            Quantité :<br>
            <input type="text" name="quantite" value=${quantite}><br>
            Fraits de port :<br>
            <input type="text" name="shippingCost" value=${shippingCost}><br>
            ID du produit :<br>
            <input type="text" name="productID" value=${productID}><br>
            Date de la vente :<br>
            <input type="text" name="saleDate" value=${saleDate}><br>
            Date de transport :<br>
            <input type="text" name="shippingDate" value=${shippingDate}><br>
            Compagnie de transport :<br>
            <input type="text" name="freightCompagny" value=${freightCompagny}><br>
            <input type="submit" value="Valider">
        </form>
    </body>
</html>
