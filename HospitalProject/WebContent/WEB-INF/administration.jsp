<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>


<h1>Administration</h1>

<c:url value="/Inscription" scope="application" var="ajout"/> 
<a class="btn btn-primary" href='<c:out value="${ajout}" />' onclick="window.open(this.href, 'exemple', 'height=300, width=400, top=100, left=100, toolbar=no, menubar=no, location=no, resizable=no, scrollbars=no, status=no'); return false" > Ajouter un utilisateur</a>
<c:url value="/Pathologie" scope="application" var="ajoutP"/>
<a class="btn btn-primary" href='<c:out value="${ajoutP}" />'> Ajouter des pathologies</a>

<table class="table table-striped">
<thead>
	<tr>
		<th>Nom et prénom</th>
		<th>Poste</th>
	
	</tr>
</thead>
<tbody>
<c:forEach var="liste" items="${users}"> 
   			
   			<tr>
   				<td><c:out value="${liste.nomUser }" /> <c:out value="${liste.prenomUser }" /></td>
   				<td><c:out value="${liste.libelleFonctions }" /></td>
   			
   			</tr>
        </c:forEach>
</tbody>

</table>
<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>