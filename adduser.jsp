<%-- 
    Document   : adduser
    Created on : Apr 26, 2018, 8:09:07 PM
    Author     : Abdalla
--%>

<%@page import="ae.cs.inv.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page= "header.jsp" />

<link rel="stylesheet" href="styles/main.css" />
<!-- logo from http://automotivexcellence.com.au/product/ae-sticker-small-logo/ -->

<jsp:include page = "bigheader.jsp" />  

<!-- Breadcrumb Nav-->
<jsp:include page = "user-navigation.jsp" />


<main>
   
    <form action="admin">
        
        <br>
        User ID:<br>
        <input type="number" name="userID" required><br><br> <%-- The prefix if generated inside the servlet --%>
        Password:<br>
        <input type ="text" name="password" required><br><br>
        First Name:<br>
        <input type ="text" name="firstName" required><br><br>
        Last Name:<br>
        <input type ="text" name="lastName" required><br><br>
        Email Address:<br>
        <input type ="text" name="emailAddress" required><br><br> 
        Address 1:<br>
        <input class="" type ="text" name="address1" required><br><br>
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
        Recovery Question:<br>
        <input type ="text" name="question" required><br><br>
        <input type = "hidden" value = "addUser" name = "action">
        
        <input type = "submit" value = "Add User Account" class="r"><br><br>
     
    </form>
    
    
</main>
<!--Site navigation -->
<jsp:include page = "site-navigation.jsp" />
<jsp:include page = "footer.jsp" />


<!--

    to be used to indicate the flag &#9745;
    tick if the sure bought it 
    if the user did not buy the item &#9744;
    -->