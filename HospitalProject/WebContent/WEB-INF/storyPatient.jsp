<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<title>Historiques</title>
</head>
<body>
<c:choose>
	<c:when test="${!empty story }">
		
 <table class="table table-striped">
    <thead>
      <tr>
        <th>Numero visite</th>
        <th>Numéro consultation</th>
        <th>Date consultation</th>
        <th>Date visite</th>
        <th>Ordre de visite</th>
        <th>Statut</th>
        
      </tr>
    </thead>
    <tbody>
		<c:forEach var="view" items="${story }">
			<tr>
				<td><c:out value="${view.numConsultation }" /></td>
				<td><c:out value="${view.numVisite }" /></td>
				<td>
					<c:if test="${view.ordre==0}">
						<c:out value="${view.dateDebut }" />
					</c:if>		
				</td>
				<td><c:out value="${view.dateVisite }" /></td>
				<td><c:out value="${view.ordre}" /></td>
				<td><c:out value="${view.status }" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	</c:when>
	<c:otherwise>
		<h1>Pas d'historique pour ce patient ! </h1>
	</c:otherwise>
</c:choose>
</body>
</html>