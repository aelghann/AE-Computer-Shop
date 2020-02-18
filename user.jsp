<%-- 
    Document   : profile
    Created on : Feb 18, 2018, 1:24:59 PM
    Author     : Abdalla


This used to be myParts.jsp and it was changed to profile.jsp to match the wording in ITCS 4166 assignment 3.
--%>

<%@page import="ae.cs.inv.Item"%>
<%@page import="ae.cs.inv.ItemRating"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ae.cs.inv.UserProfile"%>
<%@page import="ae.cs.inv.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page= "header.jsp" />

<link rel="stylesheet" href="styles/profile.css" />
<!-- logo from http://automotivexcellence.com.au/product/ae-sticker-small-logo/ -->

<jsp:include page = "bigheader.jsp" />  

<!-- Breadcrumb Nav-->
<jsp:include page = "user-navigation.jsp" />


<c:set var="usersToView" value="${sessionScope.usersToView}"/>
<main>
    <table>
        <tr>
            <th>User ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email Address</th>
        </tr>
        
        <c:forEach items="${usersToView}" var="user">
        <tr>
            <th>${user.userID}</th>
            <th>${user.firstName}</th>
            <th>${user.lastName}</th>
            <th>${user.emailAddress}</th>
            <th><form action = "admin" method="post"> 
                    <input type="hidden" value ="editUser" name="action"/>
                    <input type="hidden" value="${user.userID}" name="userIDToBeEdited"/>
                    <input type="submit" value="Edit" class = "button"/>
                </form></th>
            <th><form action = "admin" method="post"> 
                    <input type="submit" value="Delete" class = "button"/>
                    <input type="hidden" value ="deleteUser" name="action"/>
                    <input type="hidden" value="${user.userID}" name="userIDToBeDeleted"/>
                </form></th>
        </tr>
    </c:forEach>
    </table>
    
    <a href="adduser.jsp" class="r">Add a User</a><br><br>
</main>
<!--Site navigation -->
<jsp:include page = "site-navigation.jsp" />
<jsp:include page = "footer.jsp" />


<!--

    to be used to indicate the flag &#9745;
    tick if the sure bought it 
    if the user did not buy the item &#9744;
    -->