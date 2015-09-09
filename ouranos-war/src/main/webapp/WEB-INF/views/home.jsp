<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="cache-control" content="no-store" />
<meta http-equiv="expires" content="0" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Ouranos</title>

<jsp:include page="<%=\"layouts/components/css.jsp\"%>" />
</head>
<body>
	<jsp:include page="<%=\"layouts/components/header.jsp\"%>" />
	<div class="container">
		<jsp:include page="<%=\"layouts/components/menu.jsp\"%>" />
	
		<div class="jumbotron"></div>
		
		<jsp:include page="<%=\"layouts/components/footer.jsp\"%>" />
	</div>
</body>
</html>
