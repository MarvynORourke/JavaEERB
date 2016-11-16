<%-- 
    Document   : pageAccueil
    Created on : 16 nov. 2016, 14:42:48
    Author     : rroch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Page d'acceuil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Bonjour !</h1>
        <form action = "authentificationController" method = "POST">
            <div>Veuillez entrer votre adresse électronique :</div>
            <textarea rows="1" cols="50" name = "email"></textarea>
            <div>Veuillez entrer votre mot de passe (identifiant de client) :</div>
            <textarea rows="1" cols="50" name = "mdp"></textarea>
            <input type="submit">
        </form>
    </body>
</html>