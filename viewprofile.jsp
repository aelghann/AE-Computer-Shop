<%-- 
    Document   : viewprofile
    Created on : Apr 29, 2018, 7:02:37 PM
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
   
    
    <c:set var="placeHolderInfo" value ="${sessionScope.account}" />
    
    <form action="profile" method="post">
        
        <br>
        <p> If you want to change your password please click here ----  a link to forget password</p>
        First Name:<br>
        <input type ="text" name="firstName" value="<c:out value="${placeHolderInfo.firstName}"/>" required><br><br>
        Last Name:<br>
        <input type ="text" name="lastName" value="<c:out value="${placeHolderInfo.lastName}"/>" required><br><br>
        Email Address:<br>
        <input readonly type ="text" name="emailAddress" value="<c:out value="${placeHolderInfo.emailAddress}"/>" required>  (read only) <br><br> <%-- I wanted to use email as a type, but it is not supported in safari--%>
        Address 1:<br>
        <input class="" type ="text" name="address1" value="<c:out value="${placeHolderInfo.address1}"/>" required><br><br>
        Address 2:<br>
        <input type ="text" name="address2" value="<c:out value="${placeHolderInfo.address2}"/>" required><br><br>
        City:<br>
        <input type ="text" name="city" value="<c:out value="${placeHolderInfo.city}"/>" required><br><br>
        State:<br>
        <input type ="text" name="state" value="<c:out value="${placeHolderInfo.state}"/>" required><br><br>
        Zip Code:<br>
        <input type ="text" name="postCode" value="<c:out value="${placeHolderInfo.postCode}"/>" required><br><br>
        Country:<br>
        <input type ="text" name="country" value="<c:out value="${placeHolderInfo.country}" />" required ><br><br>
        Security Answer:<br>
        <input type ="text" name="question" value="<c:out value="${placeHolderInfo.question}"/>" required ><br><br>
        
        <input type="hidden" name="uID" value="<c:out value = "${sessionScope.account.userID}" /> ">
        <input type = "hidden" value = "applyChanges" name = "action">
        
        <input type = "submit" value = "Apply Changes" class="r">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="profile.jsp" class="r">Back</a><br><br>
    </form>
        
    
</main>
<!--Site navigation -->
<jsp:include page = "site-navigation.jsp" />
<jsp:include page = "footer.jsp" />
