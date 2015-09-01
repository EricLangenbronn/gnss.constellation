<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="Struts" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="cache-control" content="no-store" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><decorator:title default="LAQME" /></title>
<link rel="icon" href="/laqme/media/images/favicon.ico" />
<jsp:include page="/jsp/layouts/components/css.jsp" />
<decorator:head />
</head>
<body>
	<jsp:include page="/jsp/layouts/components/header.jsp" />
	<div class="container-fluid" style="width:100%">
		<decorator:body />
	</div>
	<jsp:include page="/jsp/layouts/components/footer.jsp" />
	<decorator:getProperty property="page.javascriptPage"></decorator:getProperty>
</body>
</html>