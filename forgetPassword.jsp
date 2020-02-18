<%-- 
    Document   : categories
    Created on : Feb 18, 2018, 1:04:58 PM
    Author     : Abdalla
--%>

<%@page import="ae.cs.inv.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ae.cs.inv.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "header.jsp" />
<link rel="stylesheet" href="styles/categories.css" />
<!-- logo from http://automotivexcellence.com.au/product/ae-sticker-small-logo/ -->

<jsp:include page = "bigheader.jsp" />  
<!-- Breadcrumb Nav-->
<jsp:include page = "user-navigation.jsp" />
<main>
<br>
    <h3>Forget Password</h3>
    <br>
<form action="forgetPassword" method="post">
  Email:<br>
  <input type="email" name="email" value="Example@gmail.com">
  
  <br><br>
  Enter new password: <br>
  <input type="password" name="newPassword" value="">
  <br><br>
  Q: Where were you born? <br>
  <input type="text" name="question" value="Answer">
  <br><br>
  <input type="submit" value="Submit">
  
   <input type = "hidden" value = "forgetPassword" name="action"/>
  
</form> 

</main> 

<!-- Site navigation  -->
<jsp:include page = "site-navigation.jsp" />
<jsp:include page = "footer.jsp" />
