<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="include_metadata.jsp" flush="false"></jsp:include>
<title>Logga in</title>
</head>
<body>
	<div class="nav-box">
		<div class="page-head">
			<h1>Välkommen till ${config.name }</h1>
		</div>

		<ul>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/register/">Anmäl en
					patrull</a></li>
			<c:if test="${config.allowPublicResult}">
				<li class="nav-item"><a
					href="${pageContext.request.contextPath}/public/">Se den
						aktuella poängställningen</a></li>
			</c:if>
			<li class="nav-item"><a
				href="${pageContext.request.contextPath}/startmenu">Logga in och
					rapportera poäng</a></li>
		</ul>
	</div>

</body>
</html>