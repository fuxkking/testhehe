<%-- 
    Document   : Login
    Created on : Jun 19, 2025, 8:46:08 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login form</h1>
        
        <h1 style='color:red'>${fail}</h1>
        <form action="Login" method="post">
            User: <input type="text" name="u" value="admin"/><br/><!-- comment -->
            Password: <input type="text" name="p" value="123"/> <br/>
            <input type ="submit" name ="btnLogin" value="Login"/>
        </form> 
    </body>
</html>
