<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>

<p id="filariane">Vous êtes ici : <a href="<c:out value="${accueil}"/>">Accueil</a> > Gestions consultations</p>

<form method="get">
    <div class="input-group">
      <input type="text" class="form-control" placeholder="Search" name="search">
      <div class="input-group-btn">
        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
      </div>
    </div>
  </form>


<c:if test="${!empty res }">

<c:forEach var="liste" items="${res}"> 
	<c:url value="/Consultation" var="ajoutConsultation" scope="application"> 
		<c:param name="search" value="${liste.idPatientCons}"/> 
		<c:param name="q" value="${liste.idPatientCons}"/> 
	</c:url>
   			<c:out value="${liste.idPatientCons}"></c:out> <c:out value="${liste.namePatient }"></c:out><c:out value="${liste.prenomPatient }"></c:out>
   			<p><a href="<c:out value="${ajoutConsultation}"/>" class="btn btn-primary">Nouveau</a></p>
   			<table class="table table-striped">
    <thead>
      <tr>
        <th>Numero consultation</th>
        <th>Date debut consultation</th>
        <th>derniere visite</th>
        <th>nombre de visite</th>
        <th>Prochain rdv</th>
        <th>Etat</th>
        <th>Diagnostique</th>
        <th>Traitement</th>
        
      </tr>
    </thead>
    <c:choose>
    	<c:when test="${!empty resAll }">
    		<tbody>
		   <!-- Code pour afficher les utilisateur -->
		   		<c:forEach var="listAll" items="${resAll}">
		   			<tr>
		   			<c:url value="/VisiteMedical" var="ajoutVisite" scope="application"> 
						<c:param name="q" value="${listAll.idConsultation}"/> 
					</c:url>
		   				<td><a href="<c:out value="${ajoutVisite}" />"> <c:out value="${listAll.numConsultation}"/></a></td>
		   				<td><c:out value="${listAll.dateNow}"/></td>
		   				<td><c:out value="${listAll.dateLast}"/></td>
		   				<td><c:out value="${listAll.nombreVisite}"/></td>
		   				<td><c:out value="${listAll.dateRv}"/></td>
		   				<td><c:out value="${listAll.etatTraitement}"/></td>
		   				<td><c:out value="${listAll.diagnostique}"/></td>
		   				<td><c:out value="${listAll.traitement}"/></td>
		   				
		   			</tr>
		   		</c:forEach>
		   </tbody>
    	</c:when>
    	<c:otherwise>
    		<p>Aucune consultation pour ce patient </p>
    	</c:otherwise>
    </c:choose>
    
  </table>
</c:forEach>
</c:if>



<c:if test="${empty sessionScope.userDetails }">
	<c:redirect url="/Connexion" />
</c:if>
<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>