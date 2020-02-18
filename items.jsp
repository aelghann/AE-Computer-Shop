<%-- 
    Document   : items
    Created on : Feb 18, 2018, 1:15:38 PM
    Author     : Abdalla
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="ae.cs.inv.User"%>
<%@page import="ae.cs.inv.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page = "header.jsp" />
<link rel="stylesheet" href="styles/items.css" />
<!-- logo from http://automotivexcellence.com.au/product/ae-sticker-small-logo/ -->
<jsp:include page = "bigheader.jsp" />


<!-- Breadcrumb Nav-->
<jsp:include page = "user-navigation.jsp" />

<%--
  session.setAttribute("userID","U011"); 
 // Item item = (Item) request.getAttribute("item");
  session.setAttribute("selectedItem",item);
  request.setAttribute("itemName",item.getItemName());
--%>

<c:set var="item" value = "${requestScope.item}"/>
<c:set var="userID" value="U011" scope="session"/>
<c:set var="selectedItem" value= "${item}" scope="session"/>
<c:set var="itemName" value ="${item.itemName}" scope="request"/>



<main>
    <section>
        <h4>Category: <c:out value = "${item.catalogCategory}"/> <a class="back" href="categories.jsp"> <strong> &lt; Back</strong> </a></h4>
        <br>

        
        <img src ="<c:out value = "${item.imageUrl}" />" alt = "HHgpu"> <br>
        <p><strong><c:out value = "${item.itemName}" /> GPU</strong></p>
        <p><strong>Average Rating : <c:out value = "${item.rating}" escapeXml="false"/></strong></p> <!-- cant display proper html stars -->
        <a class = button id ="rate" href="#" > Rate it</a> <!-- to be fixed -->

        

        <form action="profile" >
            <input type = "hidden" value = "save" name = "action">
            <input type= "hidden" value="${item.itemCode}" name="itemCode">
            <c:choose>
                <c:when test="${sessionScope.LoggedIn == true}">
                    <input type="submit" value="Save to My Parts" class = "button"/>
                </c:when>
                <c:otherwise>
                    <br><a href="signin.jsp"><input class="button" type="button" value= "Please Sign In to add an Item"/></a>
                </c:otherwise>
            </c:choose>
            

  
        <article>
            <h5>Item Description</h5>
            <p> <c:out value = "${item.description}" /></p>
            <br><br>
        </article>
    </section>
</main>
<!-- Site navigation  -->
<jsp:include page= "site-navigation.jsp" />
<jsp:include page = "footer.jsp" />