<%-- 
    Document   : signin
    Created on : Apr 25, 2018, 7:36:33 PM
    Author     : Jacob Marlowe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="styles/main.css" />
<!DOCTYPE html>
<jsp:include page= "header.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign In</title>
<body>
    <header class = "H">

        <img src = "images/AElogo.png" alt = "graphical logo of ae" >
        <h1> AE Computer Shop </h1>
    </header>
    <div class = "signInDiv">
        <h1 id="signInH1">Sign In</h1>

        <form action = "signin" method="post">
            <label class="signInLabel1">Email Address:</label>
            <input type="text" name="email" class="signInInput1"><br>
            <label class="signInLabel2">Password:</label>
            <input type="text" name="password" class="signInInput2"><br>			
            <input type="submit" value="Submit" class="submit"><br><br>
            <a class = "signInCreate" href="registration.jsp">Don't Have an account? Create one here!</a><br><br>
            <a class = "signInCreate" href="forgetPassword.jsp">Forgot password? Click here.</a>
        </form>
    </div>

</body>

<jsp:include page = "footer.jsp" />

