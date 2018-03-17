<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>
<c:url value="/Connexion" var="accueil" scope="application"> 
	<c:param name="q" value="home"/> 
</c:url>
<p id="filariane">Vous êtes ici : <a href="<c:out value="${accueil}"/>">Accueil</a> > Gestion patients</p>

<form method="get">
    <div class="input-group">
      <input type="text" class="form-control" placeholder="Search" name="search">
      <div class="input-group-btn">
        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
      </div>
    </div>
  </form>

<c:url value="/PatientServlet" var="addPatient" scope="application"> 
	<c:param name="q" value="ajoutPatient"/> 
</c:url>
<p><a href="<c:out value="${addPatient}"/>" class="btn btn-primary" id="lienajout">Ajouter un patient</a></p>
<c:if test="${!empty listePatient}">
<table class="table table-striped">
    <thead>
      <tr>
        <th>Code Patient</th>
        <th>Titre</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Date naissance</th>
        <th>Date enregistrement</th>
        <th>Nombre consultation</th>
        <th>Dernière visite</th>
        <th>Adresse</th>
      </tr>
    </thead>
    <tbody>
   <!-- Code pour afficher les utilisateur -->
   		<c:forEach var="liste" items="${listePatient}"> 
   			<c:url value="/PatientServlet" var="viewPatient"> 
				<c:param name="p" value="${liste.codePatient}"/> 
		    </c:url>
   			<tr>
   				<td><a href="<c:out value="${viewPatient}"/>" onclick="window.open(this.href, 'exemple', 'height=300, width=400, top=100, left=100, toolbar=no, menubar=no, location=no, resizable=no, scrollbars=no, status=no'); return false" > <c:out value="${liste.codePatient }" /> </a></td>
   				<td><c:out value="${liste.titrePatient }" /></td>
   				<td><c:out value="${liste.nomPatient }" /></td>
   				<td><c:out value="${liste.prenomPatient }" /></td>
   				<td><c:out value="${liste.dateNaissance }" /></td>
   				<td><c:out value="${liste.dateEnregist }" /></td>
   				<td><c:out value="${liste.nombreCons }" /></td>
   				<td><c:out value="${liste.datev }" /></td>
   				<td><c:out value="${liste.adressePatient }" /></td>
   			</tr>
        </c:forEach>
    </tbody>
  </table>
</c:if>
 
  <c:if test="${empty sessionScope.userDetails }">
	<c:redirect url="/Connexion" />
</c:if>
<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>