<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>
<c:url value="/Connexion" var="accueil" scope="application"> 
	<c:param name="q" value="home"/> 
</c:url>
<p id="filariane">Vous êtes ici : <a href="<c:out value="${accueil}"/>">Accueil</a> > <a href="<c:url value="/PatientServlet" />" >Gestion patients</a> > Ajout patients </p>
<h1>Ajouter un patient</h1>
<section class="container">
<c:if test="${ !empty erreur }"><div class="alertmessage"><c:out value="${ erreur }" /></div></c:if>
<c:if test="${ !empty form.resultat }"><div class="alertmessage" ><c:out value="${ form.resultat }" /></div></c:if>
 
<form method="post">
<div class="form-group">
    <label for="titre">Titre:</label>
    <select class="form-control" id="titre" name="titre">
    	<option></option>
    	<option>Mr</option>
    	<option>Mlle</option>
    	<option>Mme</option>
    </select>
    
  </div>
 <div class="form-group">
    <label for="nom">Nom:</label>
    <input type="text" class="form-control" id="nom" name="nom" <c:if test="${!empty users.nomPatient }"> value="<c:out value="${ users.nomPatient }" />" </c:if> >
 	<c:if test="${ !empty form.erreurs['nom'] }"><p class="erreur">${form.erreurs['nom']}</p></c:if>
  </div>
   <div class="form-group">
    <label for="prenom">Prénom:</label>
    <input type="text" class="form-control" id="prenom" name="prenom"  <c:if test="${!empty users.prenomPatient }"> value="<c:out value="${ users.prenomPatient }" />" </c:if> >
  	<c:if test="${ !empty form.erreurs['nom'] }"><p class="erreur">${form.erreurs['nom']}</p></c:if>
  </div>
   <div class="form-group">
    <label for="profession">Profession:</label>
    <input type="text" class="form-control" id="profession" name="profession"  <c:if test="${!empty users.professionPatient }"> value="<c:out value="${ users.professionPatient }" />" </c:if> >
 <c:if test="${ !empty form.erreurs['nom'] }"><p class="erreur">${form.erreurs['nom']}</p></c:if>
  </div>
   <div class="form-group">
    <label for="adresse">Adresse:</label>
    <input type="text" class="form-control" id="adresse" name="adresse"  <c:if test="${!empty users.adressePatient }"> value="<c:out value="${ users.adressePatient }" />" </c:if> >
 	<c:if test="${ !empty form.erreurs['adresse'] }"><p class="erreur">${form.erreurs['adresse']}</p></c:if>
  </div>
   <div class="form-group">
    <label for="tel">Telephone:</label>
    <input type="text" class="form-control" id="tel" name="tel"  <c:if test="${!empty users.telPatient }"> value="<c:out value="${ users.telPatient }" />" </c:if> >
 <c:if test="${ !empty form.erreurs['tel'] }"><p class="erreur">${form.erreurs['tel']}</p></c:if>
  </div>
  <div class="form-group">
    <label for="email">Email :</label>
    <input type="email" class="form-control" id="email" name="email"  <c:if test="${!empty users.emailPatient }"> value="<c:out value="${ users.emailPatient }" />" </c:if> >
 	<c:if test="${ !empty form.erreurs['email'] }"><p class="erreur">${form.erreurs['email']}</p></c:if>
  </div>
     <div class="form-group">
    <label for="date">Date naissance:</label>
    <input type="date" class="form-control" id="date" name="dateN"  <c:if test="${!empty users.dateNaissance }"> value="<c:out value="${ users.dateNaissance }" />" </c:if> >
  </div>
 
  <button type="submit" class="btn btn-default">Submit</button>
</form>
</section>
<c:if test="${empty sessionScope.userDetails }">
	<c:redirect url="/Connexion" />
</c:if>
<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>