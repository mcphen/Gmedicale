<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>

<c:if test="${!empty infosrdv }">
	
	<h1>Liste de vos prochains rendez vous</h1>
	
	<c:forEach var="p" items="${infosrdv}">
		<c:url value="/PatientServlet" var="viewPatient"> 
				<c:param name="p" value="${p.codePatient}"/> 
		    </c:url>
		<p>Vous aurez rendez-vous le <c:out value="${p.datev}" /> avec <a href="<c:out value="${viewPatient}"/>" onclick="window.open(this.href, 'exemple', 'height=300, width=400, top=100, left=100, toolbar=no, menubar=no, location=no, resizable=no, scrollbars=no, status=no'); return false"><c:out value="${p.titrePatient}" />  <c:out value="${p.nomPatient}" />  </a></p>
	</c:forEach>
</c:if>
<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>