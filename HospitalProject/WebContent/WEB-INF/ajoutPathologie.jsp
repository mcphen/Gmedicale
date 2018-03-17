<c:import url="/include/header.jsp"  var="Contenuheader" scope= "request" /> 
<c:out value="${ Contenuheader }" escapeXml="false"/>
<div style="margin-right : 5px">
	<h1>Ajouter une pathologie</h1>
	<form method="post">
	 <div class="form-group">
	    <label for="nom">Libelle pathologie:</label>
	    <input type="text" class="form-control" id="nom" name="nom" >
	 	
	 </div>
	    <button type="submit" class="btn btn-default" name="ajoutPathologie">Submit</button>
	</form>
</div>

<c:import url="/include/footer.jsp"  var="Contenufooter" scope= "request" /> 
<c:out value="${ Contenufooter }" escapeXml="false"/>