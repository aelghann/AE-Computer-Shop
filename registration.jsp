<%-- 
    Document   : Registration
    Created on : Apr 25, 2018, 7:47:07 PM
    Author     : Abdalla
--%>

<%@page import="ae.cs.inv.User"%>
<jsp:include page = "header.jsp" />
 <link rel="stylesheet" href="styles/main.css" />
<jsp:include page = "bigheader.jsp" />  
    
<!-- Breadcrumb Nav-->
<jsp:include page = "user-navigation.jsp" />
<main>
    <section>
        <h1 class="H1">Create account</h1>
    </section>
    <form action="registration" method="post">
        <br>
        Email Address:<br>
        <input type ="text" name="emailAddress" required><br><br>
        Password:<br>
        <input type ="password" name="password" required><br><br>
        First Name:<br>
        <input type="text" name="firstName" required><br><br>
        Last Name:<br>
        <input type ="text" name="lastName" required><br><br>
        Address 1:<br>
        <input type ="text" name="address1" required><br><br>
        Address 2:<br>
        <input type ="text" name="address2" required><br><br>
        City:<br>
        <input type ="text" name="city" required><br><br>
        State:<br>
        <input type ="text" name="state" required><br><br>
        Zip Code:<br>
        <input type ="text" name="postCode" required><br><br>
        Country:<br>
        <input type ="text" name="country" required><br><br>
        
        <strong>Security Question: Where Were You Born?</strong><br><br>
        Your answer will be used for Account Recovery<br><br>
        <input type ="text" name="question" required><br><br>

        <input type = "hidden" value = "register" name = "action">
        
        <input type = "submit" value = "Create your account" class="r">'
    </form >
    <br>
</main>
<!-- Site navigation  -->
<jsp:include page = "site-navigation.jsp"/>
<jsp:include page = "footer.jsp"/>