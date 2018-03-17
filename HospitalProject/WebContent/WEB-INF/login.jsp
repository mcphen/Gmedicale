<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<title>Page de connexion</title>
</head>
<body id="pageconnexion">

	<div id="entete">
		<h2>Bienvenue dans votre application Java EE</h2>
	</div>
	<c:if test="${ !empty erreur }"><div class="alertmessage"><c:out value="${ erreur }" /></div></c:if>
	
	<section class="row" >
	    <section class="col-lg-6 col-md-6"  >
	    	<div id="divForm" action="/Connexion">
		<form method="post">
		<div class="form-group">
			<label for="login">Login</label>
			<input class="form-control" name="login" id="login"  type="text"> 
		</div>
		<div class="form-group">
				<label for="mdp">Mot de passe</label>
			<input class="form-control" name="mdp" id="mdp" type="password"> 
		</div>
			<input type="submit" value="enregistrer" id="validate" class="btn btn-default">
		</form>
	</div>
	    </section>
	    <section class="col-lg-6 col-md-6"  >
	    	<img alt="photo" src="${pageContext.request.contextPath}/img/online-doctor-consultation-in-kerala-5-638.jpg" width="80%" />
	    </section>
    </section>
		

</body>
</html>