<%-- 
    Document   : reAcceuil
    Created on : 6 déc. 2016, 11:53:55
    Author     : Romain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Erreur!</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>$(reAuthentificationMessage)</h1>
        <form action = "authentificationController" method = "POST">
            <div>Veuillez entrer votre adresse électronique :</div>
            <textarea rows="1" cols="50" name = "email"></textarea>
            <div>Veuillez entrer votre mot de passe (identifiant de client) :</div>
            <textarea rows="1" cols="50" name = "mdp"></textarea>
            <input type="submit">
        </form>
    </body>
</html>
