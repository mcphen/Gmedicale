<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>

<form method="post">
<div class="form-group">
<label for="daterv">Date du prochain :</label>
<input type="date" name="daterv" id="daterv" class="form-control" value='<c:out value="${modif.dateRv }" />'>
 
                
</div>
 
 <div class="form-group">
    <label for="diagnos">Diagnostiques:</label>
    <select name="diagnos" id="diagnos" class="form-control" >
    	<option value='<c:out value="${modif.diagnostique}" />'><c:out value="${modif.diagnostique}" /></option>
    	<c:forEach var="fonctions" items="${functions}"> 
                <option value='${fonctions.libellePathologie }'> <c:out value="${fonctions.libellePathologie}"></c:out> </option>
        </c:forEach>
    </select></div>
  <div class="form-group">
    <label for="traitement">Traitements:</label>
    <textarea rows="5" class="form-control" id="traitement" name="traitement" ><c:out value="${modif.traitement}" /></textarea>
 </div>
  <div class="form-group">
    <label for="etat">Etat du traitement:</label>
    <select name="etat" id="etat" class="form-control">
       <option value='<c:out value="${modif.etatTraitement}" />'><c:out value="${modif.etatTraitement}" /></option>
    	<option value="EC">En cours</option>
    	<option value="PR">Prolongé</option>
    	<option value="TR">Terminé</option>
    </select>
 </div>

 <input type="hidden"  name="idusers" value='<c:out value="${modif.idConsultation}" />'>
  <button type="submit" class="btn btn-default">Submit</button>
</form>
<c:if test="${empty sessionScope.userDetails }">
	<c:redirect url="/Connexion" />
</c:if>
<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>