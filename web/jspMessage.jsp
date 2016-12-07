<%-- 
    Document   : jspMessage
    Created on : 16 nov. 2016, 14:48:45
    Author     : rroch & lmazeran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authentification réussie</title>
        <link href="./style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Vous êtes authentifié.</h1>
        <form action = "logout" method = "POST">
            <input type="submit" value="Se déconnecter">
        </form>
    </body>
</html>
