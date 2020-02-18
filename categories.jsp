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
<section class="left">
<ul>
    <li>
        <h2>GPUs</h2>
    </li>
</ul>
<c:set var = "al" value = "${sessionScope.itemsDB}"/>
<%--<p><c:out value = "${al}"/></p>--%>
<c:forEach var = "i" begin = "0" end="${al.size()-1}" >
    <c:if test="${al.get(i).catalogCategory == 'G'}">
        <c:set var = "itemCode" value="${al.get(i).itemCode}"/>
        <c:set var = "imageUrl" value="${al.get(i).imageUrl}"/>
        <c:set var = "itemName" value="${al.get(i).itemName}"/>
        
        <form action = "categories" method = "get">
            <input type = "hidden" value = "<c:out value = "${itemCode}"/>" name = "itemCode">
            <input type="image" src="<c:out value="${imageUrl}"/>" alt="high scale gpu" width="100" height="100" class="imgPointer"><br><br>
            <input type="submit" value= "<c:out value= "${itemName}"/>" class = "transparent">
            <br><br>
        </form>
    </c:if>

</c:forEach>

</section>

<%--
<%
    ArrayList al = (ArrayList) session.getAttribute("itemsDB");

    for (int i = 0; i < al.size(); i++) {
        Item item = (Item) al.get(i);
        if (item.getCatalogCategory().equals("G")) {

%>
<form action = "categories" method = "get">
    <input type = "hidden" value = "<%=item.getItemCode()%>" name = "itemCode">
    <input type="image" src="<%=item.getImageUrl()%>" alt="high scale gpu" width="100" height="100" class="imgPointer"><br><br>
    <input type="submit" value= "<%=item.getItemName()%>" class = "transparent">
    <br><br>
</form>

<%--
<form action = "categories" method = "get">
    <input type = "hidden" value = "GPU2" name = "itemCode">
    <input type="image" src=" images/GPU2.png" alt="high scale gpu" width="100" height="100" class="imgPointer"><br><br>
    <input type="submit" value="High Scale"  class = "transparent">
    <br><br>
</form>
<form action = "categories" method = "get">
    <input type = "hidden" value = "GPU3" name = "itemCode">
    <input type="image" src=" images/GPU3.png" alt="high scale gpu" width="100" height="100" class="imgPointer"><br><br>
    <input type="submit" value="Hologram Capable" class = "transparent">
    <br><br>
</form>
--%>
<%--
        }
    }
--%>



<section class = "right">
    <ul>
        <li>
            <h2>CPUs</h2>
        </li>
    </ul>
    
    <c:forEach var = "i" begin = "0" end="${al.size()-1}">
    <c:if test="${al.get(i).catalogCategory == 'C'}">
        <c:set var = "itemCode" value="${al.get(i).itemCode}"/>
        <c:set var = "imageUrl" value="${al.get(i).imageUrl}"/>
        <c:set var = "itemName" value="${al.get(i).itemName}"/>
        
        <form action = "categories" method = "get">
            <input type = "hidden" value = "<c:out value = "${itemCode}" />" name = "itemCode">
            <input type="image" src="<c:out value = "${imageUrl}" />" alt="high scale gpu" width="100" height="100" class="imgPointer"><br><br>
            <input type="submit" value= "<c:out value= "${itemName}"/>" class = "transparent">
            <br><br>
        </form>
    </c:if>

</c:forEach>

    <%--
    <%
        for (int i = 0; i < al.size(); i++) {
            Item item = (Item) al.get(i);
            if (item.getCatalogCategory().equals("C")) {


    %>
    <form action = "categories" method = "get">
        <input type = "hidden" value = "<%=item.getItemCode()%>" name = "itemCode">
        <input type="image" src="<%=item.getImageUrl()%>" alt="high scale cpu" width="100" height="100" class="imgPointer"><br><br>
        <input type="submit" value="<%=item.getItemName()%>" class = "transparent">
        <br><br>
    </form>

    <%--
    <form action = "categories" method = "get">
        <input type = "hidden" value = "CPU2" name = "itemCode">
        <input type="image" src=" images/CPU2.png" alt="high scale cpu" width="100" height="100" class="imgPointer"><br><br>
        <input type="submit" value="Outstanding Performance" class = "transparent">
        <br><br>
    </form>
    <form action = "categories" method = "get">
        <input type = "hidden" value = "CPU3" name = "itemCode">
        <input type="image" src=" images/CPU3.png" alt="high scale cpu" width="100" height="100" class="imgPointer"><br><br>
        <input type="submit" value="Custom Made" class = "transparent">
        <br><br>
    </form>
    --%>
    <%--
            }
        }
    --%>
</section>
</main> 

<!-- Site navigation  -->
<jsp:include page = "site-navigation.jsp" />
<jsp:include page = "footer.jsp" />