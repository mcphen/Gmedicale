<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<title>test</title>
</head>
<body>
<section class="infos_en_tete">
<c:url value="/Connexion" var="lienDeconnexion" scope="application"> 
	<c:param name="auth" value="deconnexion"/> 
</c:url>

    <span>Bonjour <strong><c:out value="${sessionScope.userDetails.nomUser}"  /> </strong>  </span>
   <span id="connexion">
            <a href="<c:out value="${lienDeconnexion}"/>""><span class="glyphicon glyphicon-log-out">Deconnexion</span></a>
 </span>
</section>
<c:url value="/Connexion" var="accueil" scope="application"> 
	<c:param name="q" value="home"/> 
</c:url>
<section class="row" id="bodypage">
    <section class="col-lg-2 col-md-2"  id="navigation">
      	<div>
      		<img alt="avatar" src="${pageContext.request.contextPath}/img/doc.png" width="150px">
      		<div id="infoUser">
      			<p><c:out value="${sessionScope.userDetails.titleUser}"  /> <c:out value="${sessionScope.userDetails.nomUser}"  /> <c:out value="${sessionScope.userDetails.prenomUser}"  /></p>
      			<p> Fonction : <c:out value="${sessionScope.userDetails.libelleFonctions}"  /></p>
      		</div>
      	</div>
           
                <ul class="nav flex-column" style="margin-top: 10%;">
                	<li class="nav-item"><a class="nav-link" href="<c:out value="${accueil}"/>"><span><img src="${pageContext.request.contextPath}/img/a.png" width="25px"/> Accueil</span></a></li>
                 	<li class="nav-item"><a  class="nav-link" href="<c:url value="/PatientServlet" />"><span><img src="${pageContext.request.contextPath}/img/c.png" width="25px"/>Patients</span></a></li>
                 	<li class="nav-item"><a  class="nav-link" href="<c:url value="/Consultation" />"><span><img src="${pageContext.request.contextPath}/img/b.png" width="25px"/> Consultation</span></a></li>
                 	<li class="nav-item">
                 	<c:if test="${sessionScope.userDetails.idFonctions==1 || sessionScope.userDetails.idFonctions==4 }">
                 		<a  class="nav-link" href="<c:url value="/Administration" />"><span><img src="${pageContext.request.contextPath}/img/d.png" width="25px"/> Administration </span></a>
                 	</c:if>
                 	</li>
                </ul>
           
        
    </section>
    <section class="col-lg-10 col-md-10">
        <section id="corps">
         
	


