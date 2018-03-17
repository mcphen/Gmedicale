<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>

<h1>Liste des pathologie</h1>

<c:url value="/Pathologie" scope="application" var="ajoutP">
	<c:param name="q" value="ajoutPathologie"/> 
</c:url>
<a class="btn btn-primary" href='<c:out value="${ajoutP}" />'> Ajouter des pathologies</a>
<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>