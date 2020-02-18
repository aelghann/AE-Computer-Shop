<%-- 
    Document   : index
    Created on : Feb 18, 2018, 12:26:19 PM
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
        <h2>Welcome to AE's. Your home for next gen Computer Parts.</h2>
        <article>
            <h3>What we offer: </h3>
            <br>
            <p>Everything you need from full scale hologram Capable GPUs to state of the art engineered CPUs.</p>
            <br>
            <p>We are able to design Server Hardware architecture and outstanding SonicWalls/FireWalls.</p>
            <br>
            <p>
                For any other parts please <a href = "contact.jsp">contact us.</a> <!-- shoudl hyperlink that to contact us page--> 
            </p>
            <br>
        </article>
    </section>
</main>
<!-- Site navigation  -->
<jsp:include page = "site-navigation.jsp" />
<jsp:include page = "footer.jsp" />