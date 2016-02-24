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
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/media/css/display.css" />">
<script type="text/javascript"
	src="<c:url value="/resources/media/js/libs/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/media/js/libs/highcharts/modules/exporting.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/media/js/libs/highcharts/highcharts.js" />"></script>
</head>
<body>
	<jsp:include page="<%=\"layouts/components/header.jsp\"%>" />
	<div class="container">
		<jsp:include page="<%=\"layouts/components/menu.jsp\"%>" />

		<div class="jumbotron">
			<div id="errorMessage" class="alert alert-danger" style="display: none" role="alert"></div>

			<div id="iconLoading" class="text-center">
				<span class="glyphicon glyphicon-refresh glyphicon-spin"></span>
			</div>

			<div id="graph" style="display: none"
				style="min-width: 310px; height: 400px; margin: 0 auto"></div>

			<div id="cand"></div>

			<jsp:include page="<%=\"layouts/components/footer.jsp\"%>" />
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
var ouranosCategories = [];
var ouranosSeries = [];
var Jsondata = {};

var dureeTimer = parseInt('1000');
var timerVisibilty = setInterval(appelJson, dureeTimer);

function afficherGraphe(categorieValues, serieValues) {
	$('#iconLoading').hide();
	$('#graph').show();

	$('#graph').highcharts({
		chart : {
			type : 'column'
		},
		title : {
			text : 'Visibility'
		},
		subtitle : {
			text : ''
		},
		xAxis : {
			categories : categorieValues, // Times
			crosshair : true
		},
		yAxis : {
			min : 0,
			title : {
				text : 'Number of satelites'
			}
		},
		series : [ {
			name : 'Satelite(s)',
			data : serieValues
		} ]
	});
}

function loadData(data) {
	Jsondata = data
	lstObj = Jsondata["visibleSats"];
	if (lstObj.length > 0) {
		for (var i = 0; i < lstObj.length; i++) {
			var tuple = lstObj[i];

			ouranosCategories.push(tuple["key"]);
			ouranosSeries.push(tuple["value"]);
		}

		afficherGraphe(ouranosCategories, ouranosSeries);
		window.clearInterval(timerVisibilty);
	}
	else
	{
		window.clearInterval(timerVisibilty);
		$('#iconLoading').hide();
		$('#errorMessage').show();
		$('#errorMessage').text("Oups !, pas de satelite visble pour ces paramètres.");
	}
}

function appelJson() {
	$.getJSON("<c:url value="/display/visibility" />", function(data) {
		loadData(data);
	});
}
</script>


