<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>
<c:url value="/VisiteMedical" var="ajoutConsultation" scope="application"> 
		<c:param name="q" value="${urlparam}"/> 
		<c:param name="query" value="ajoutVisite"/> 
</c:url>
<p id="filariane">Vous êtes ici : <a href="<c:out value="${accueil}"/>">Accueil</a> >  <a href="<c:url value="/Consultation" />" >Gestions consultations</a> </p>

<p><a href="<c:out value="${ajoutConsultation}"/>" class="btn btn-primary">Nouveau</a> 
<c:url value="/EditerVisite" var="updateVisite" scope="application"> 
						<c:param name="q" value="${editer}"/> 
					</c:url>
   					<a href='<c:out value="${updateVisite }"/>' class="btn btn-primary">Editer sa consultation</a>
</p>
<table class="table table-striped">
    <thead>
      <tr>
        <th>Numero visite</th>
        <th>Date visite</th>
        
        <th>Prochain rdv</th>
        <th>Ordre visite</th>
        <th>Statut</th>
        <th>Nouveau symptome</th>
        <th>Ordonnance</th>
    
        
      </tr>
    </thead>
    <tbody>
    	<c:forEach var="list" items="${vs}">
    		<tr>
    			<td><c:out value="${list.numVisites }" /></td>
    			<td><c:out value="${list.dateVisite }" /></td>
    			<td><c:out value="${list.dateRv }" /></td>
    			<td><c:out value="${list.ordre }" /></td>
    			<td><c:out value="${list.statut }" /></td>
    			<td><c:out value="${list.symptome }" /></td>
    			<td><c:out value="${list.ordonnance}" /></td>
    			<td>
    			
   				</td>
    		</tr>
    	</c:forEach>
    </tbody>
</table>
  <c:if test="${empty sessionScope.userDetails }">
	<c:redirect url="/Connexion" />
</c:if>
<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>