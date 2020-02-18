<%-- 
    Document   : additem
    Created on : Apr 27, 2018, 9:56:51 PM
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
        Item Code:<br>
        <input type="text" name="itemCode" maxlength = "6" required><br><br>
        Item Name:<br>
        <input type ="text" name="itemName" required><br><br>
        Catalog Category:<br>
        <input type ="text" name="catalogCategory"  maxlength="1" required><br><br>
        Description:<br>
        <input type ="text" name="description" required><br><br>
        Rating:<br>
            <select name = "rating" size="6" required>
                <option value="0" > No Rating</option>
                <option value="1" > &#9733;</option>
                <option value="2"> &#9733;&#9733;</option>
                <option value="3"> &#9733;&#9733;&#9733;</option>
                <option value="4"> &#9733;&#9733;&#9733;&#9733;</option>
                <option value="5"> &#9733;&#9733;&#9733;&#9733;&#9733;</option>
            </select>
        <br><br>
        Image Url:<br>
        <input class="" type ="text" name="imageUrl" required><br><br>
     
        <input type = "hidden" value = "addItem" name = "action">
        
        <input type = "submit" value = "Add Item" class="r"><br><br>
     
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