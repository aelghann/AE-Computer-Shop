<%-- 
    Document   : edititem
    Created on : Apr 28, 2018, 7:36:56 PM
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
   
    
    <c:set var="placeHolderInfo" value ="${sessionScope.itemInfoPlaceHolder}" />
    
    <form action="admin" method="post">
        
        <br>
        Item Code:<br>
        <input type="text" name="itemCode" maxlength="6" placeholder="<c:out value = "${placeHolderInfo.itemCode}"/>" required><br><br>
        Item Name<br>
        <input type ="text" name="itemName" placeholder="${placeHolderInfo.itemName}" required><br><br>
        Catalog Category:<br>
        <input type ="text" name="catalogCategory" maxlength = "1" placeholder="${placeHolderInfo.catalogCategory}" required><br><br>
        Description:<br>
        <input type ="text" name="description" placeholder="${placeHolderInfo.description}" required><br><br>
        Rating:<br>
        <input type ="text" name="rating" maxlength = "1" placeholder="${placeHolderInfo.rating}" required><br><br> <%-- I wanted to use email as a type, but it is not supported in safari--%>
        Image Url:<br>
        <input class="" type ="text" name="imageUrl" placeholder="${placeHolderInfo.imageUrl}" required><br><br>
        
        <input type="hidden" name="itemCodeToBeUpdated" value="${sessionScope.itemInfoPlaceHolder.itemCode}">
        <input type = "hidden" value = "applyChangesItem" name = "action">
        
        <input type = "submit" value = "Apply Changes" class="r"><br><br>
     
    </form>
    
    
</main>
<!--Site navigation -->
<jsp:include page = "site-navigation.jsp" />
<jsp:include page = "footer.jsp" />
