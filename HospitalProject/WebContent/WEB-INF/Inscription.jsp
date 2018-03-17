
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<title>Insertion utilisateur</title>
</head>
<body>
<section class="container">
<c:if test="${ !empty erreur }"><div class="alertmessage"><c:out value="${ erreur }" /></div></c:if>
<c:if test="${ !empty form.resultat }"><div class="alertmessage" ><c:out value="${ form.resultat }" /></div></c:if>
      <h1>Inserer un utilisateur</h1>
        <form method="post" action="inscription">
                
                <label for="title">Titre </label>
                <select name="title">
    						<option>Mr</option>
    						<option>Mlle</option>
    						<option>Mme</option>
    						
    			</select><br>
                <label for="login">Login</label>
                <input type="text" id="login" name="login" <c:if test="${!empty users.login }"> value="<c:out value="${ users.login }" />" </c:if> />
              
                <br />
                <label for="nom">Nom d'utilisateur</label>
                <input type="text" id="nom" name="nom" <c:if test="${!empty users.nomUser }"> value="<c:out value="${ users.nomUser }" />" </c:if>  />
                <c:if test="${ !empty form.erreurs['nom'] }"><p class="erreur">${form.erreurs['nom']}</p></c:if>
                <br />
                
                <label for="firstnom">Prénom d'utilisateur</label>
                <input type="text" id="firstnom" name="firstnom"  <c:if test="${!empty users.prenomUser }"> value="<c:out value="${ users.prenomUser }" />" </c:if> />
                
                <br />
                
                 <label for="tel">Telephone</label>
                <input type="text" id="tel" name="tel" <c:if test="${!empty users.telUser }"> value="<c:out value="${ users.telUser }" />" </c:if>  />
                <c:if test="${ !empty form.erreurs['tel'] }"><p class="erreur">${form.erreurs['tel']}</p></c:if>
                <br />

                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="text" id="email" name="email"  <c:if test="${!empty users.emailUser}"> value="<c:out value="${ users.emailUser }" />" </c:if> />
                <c:if test="${ !empty form.erreurs['email'] }"><p class="erreur">${form.erreurs['email']}</p></c:if>
               
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" />
                <c:if test="${ !empty form.erreurs['motdepasse'] }"><p class="erreur">${form.erreurs['motdepasse']}</p></c:if>
                <br />

                <label for="adresse">Adresse <span class="requis">*</span></label>
                <input type="text" id="adresse" name="adresse" <c:if test="${!empty users.adresse }"> value="<c:out value="${ users.adresse }" />" </c:if>  />
                <br />
                                <label for="fonction">Fonction</label>
                <select name="fonctions">
                	<option <c:if test="${!empty users.idFonctions }"> value="<c:out value="${ users.idFonctions}" />" </c:if> ></option>
                	<c:forEach var="fonctions" items="${functions}"> 
                		<option value='${fonctions.idfonction }'> <c:out value="${fonctions.libelleFonction}"></c:out> </option>
                	</c:forEach>
                </select> <br />
                <input type="submit" value="Inscription" class="sansLabel" />
                <br />
                
            
        </form>
</section>

</body>
</html>