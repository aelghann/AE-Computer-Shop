<%-- 
    Document   : items
    Created on : Feb 18, 2018, 1:15:38 PM
    Author     : Abdalla
--%>

<%@page import="ae.cs.inv.Item"%>
<%@page import="ae.cs.inv.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "header.jsp" />
<link rel="stylesheet" href="styles/items.css" />
<link rel="stylesheet" href="styles/feedback.css" />
<!-- logo from http://automotivexcellence.com.au/product/ae-sticker-small-logo/ -->

<jsp:include page = "bigheader.jsp" />  

<!-- Breadcrumb Nav-->
<jsp:include page = "user-navigation.jsp" />

<c:set var ="displayItem" value = "${requestScope.theItem}" />
<main>
    <section>
        <h4>Category: <c:out value = "${displayItem.catalogCategory}"/><a class="back" href="profile.jsp"> <strong> &lt; Back</strong> </a></h4>
        <br>
        <img src ="<c:out value = "${displayItem.imageUrl}" />" alt = "displayItemImg"> <br>
        <p><strong><c:out value = "${displayItem.itemName}" /></strong></p>
        <p><strong>Average Rating : <c:out value = "${displayItem.rating}" escapeXml="false"/></strong></p>
        <br>
        
        <form action = "profile" method = "post">
            <p><strong id="BI"> Bought It Before ? </strong></p>
            <input type="radio" name="<c:out value= "${displayItem.itemCode}" />" value="yes"><strong>Yes</strong>
            <input type="radio" name="<c:out value = "${displayItem.itemCode}" />" value="no"><strong>No</strong><br>
            <input type = "hidden" value = "<c:out value = "${displayItem.itemCode}" />" name = "itemCodeUpdate">
            <input type="hidden" value="updateFlag" name="action"/>
            <input type="submit" value="Submit Answer" class = "button"/><br><br>
        </form>

        <form action="profile" method = "post">
            <select name = "rating">
                <option value="0" > No Rating</option>
                <option value="1" > &#9733;</option>
                <option value="2"> &#9733;&#9733;</option>
                <option value="3"> &#9733;&#9733;&#9733;</option>
                <option value="4"> &#9733;&#9733;&#9733;&#9733;</option>
                <option value="5"> &#9733;&#9733;&#9733;&#9733;&#9733;</option>
            </select>

            <input type = "hidden" value = "updateRating" name="action"/>
            <input type = "hidden" value = "<c:out value = "${displayItem.itemCode}" />" name = "itemCodeUpdate">
            <input type="submit" value="Submit Rating" class = "button"/>
        </form>


        <article>
            <h5>Item Description</h5>
            <p><c:out value = "${displayItem.description}" /></p>
        </article>
    </section>
</main>
<!-- Site navigation  -->
<jsp:include page= "site-navigation.jsp" />
<jsp:include page = "footer.jsp" />