<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>

<p id="filariane">Vous êtes ici : <a href="<c:out value="${accueil}"/>">Accueil</a> >  <a href="<c:url value="/Consultation" />" >Gestions consultations</a> > Ajouter une visite </p>

<h2>Nouvelle visite</h2>

<form method="post">

 <div class="form-group">
    <label for="nom">Symptome:</label>
    <textarea class="form-control" id="nom" name="symptome" rows="5"></textarea>
 
  </div>
  
   <div class="form-group">
    <label for="nom">Ordonnance:</label>
    <textarea class="form-control" id="nom" name="ordonnance" rows="5"></textarea>
 
  </div>
  <div class="form-group">
  	<label>Statut</label>
  	<select name="statut" class="form-control">
  		<option value="ST">Suite du Traitement</option>
  		<option value="IT">Interruption du Traitement</option>
  		<option value="FT">Fin du Traitement</option>
  	</select>
  </div>
   <div class="form-group">
    <label for="nom">Date du prochain rendez vous:</label>
    <input type="date" class="form-control" id="nom" name="daterdv" >
 
  </div>
  <input value="${urlparam }" type="hidden" name="url">
  <button type="submit" class="btn btn-default">Submit</button>
</form>

<c:if test="${empty sessionScope.userDetails }">
	<c:redirect url="/Connexion" />
</c:if>
<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>