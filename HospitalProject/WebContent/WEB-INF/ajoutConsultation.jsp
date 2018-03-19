<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>
<c:if test="${ !empty erreur }"><div class="alertmessage"><c:out value="${ erreur }" /></div></c:if>
<c:if test="${ !empty form.resultat }"><div class="alertmessage" ><c:out value="${ form.resultat }" /></div></c:if>
 
<form method="post">
<div class="form-group">
<label for="daterv">Date du prochain :</label>
<input type="date" name="daterv" id="daterv" class="form-control">
 <c:if test="${ !empty form.erreurs['daterv'] }"><p class="erreur">${form.erreurs['daterv']}</p></c:if>
                
</div>
  <div class="form-group">
    <label for="symptome">Symptômes:</label>
    <textarea rows="5" class="form-control" id="symptome" name="symptome" ></textarea>
 </div>
   <div class="form-group">
    <label for="diagnos">Diagnostiques:</label>
    <select name="diagnos" id="diagnos" class="form-control" >
    	<c:forEach var="fonctions" items="${functions}"> 
                <option value='${fonctions.libellePathologie }'> <c:out value="${fonctions.libellePathologie}"></c:out> </option>
        </c:forEach>
    </select></div>
  <div class="form-group">
    <label for="traitement">Traitements:</label>
    <textarea rows="5" class="form-control" id="traitement" name="traitement" ></textarea>
 </div>
  <div class="form-group">
    <label for="etat">Etat du traitement:</label>
    <select name="etat" id="etat" class="form-control">
    	<option value="EC">En cours</option>
    	<option value="PR">Prolongé</option>
    	<option value="TR">Terminé</option>
    </select>
 </div>
  <div class="form-group">
    <label for="symptome">Ordonnances:</label>
    <textarea rows="5" class="form-control" id="symptome" name="ordonnance" ></textarea>
 </div>
 <input type="hidden" value="${idpatient }" name="idpatient">
 <input type="hidden" value="<c:out value="${userDetails.identifantUsers}" />" name="idusers">
  <button type="submit" class="btn btn-default">Submit</button>
</form>
<c:if test="${empty sessionScope.userDetails }">
	<c:redirect url="/Connexion" />
</c:if>
<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>