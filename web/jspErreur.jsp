<%-- 
    Document   : jspErreur
    Created on : 16 nov. 2016, 14:49:35
    Author     : rroch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Erreur!</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./style.css" rel="stylesheet" type="text/css">

    </head>
    <body>
        <h1>Votre email ou votre mot de passe n'est pas bon. Veuillez, s'il vous plaît, réessayer.</h1>
        <form class="form" action = "authentificationController" method = "POST">
            <div>Veuillez entrer votre adresse électronique :</div>
            <input type="text" size="50" style="height:25px;" name="email">
            <div>Veuillez entrer votre mot de passe (identifiant de client) :</div>
            <input type="text" size="50" style="height:25px;" name="mdp">
            <input type="submit">
        </form>
    </body>
</html>
