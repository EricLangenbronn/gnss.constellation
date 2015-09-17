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
			<div id="graph"
				style="min-width: 310px; height: 400px; margin: 0 auto"></div>

			<div id="cand"></div>

			<jsp:include page="<%=\"layouts/components/footer.jsp\"%>" />
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	ouranosCategories = [];
	ouranosSeries = [];
	Jsondata = {};

	function afficherGraphe(categorieValues, serieValues) {
		$('#graph')
				.highcharts(
						{
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
							tooltip : {
								headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
								pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
										+ '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
								footerFormat : '</table>',
								shared : true,
								useHTML : true
							},
							plotOptions : {
								column : {
									pointPadding : 0.2,
									borderWidth : 0
								}
							},
							series : [ {
								name : 'Nb Sat',
								data : serieValues
							} ]
						});
	}

	function loadData(data) {
		Jsondata = data
		lstObj = Jsondata["visibleSats"];
		for (var i = 0; i < lstObj.length; i++) {
			var tuple = lstObj[i];

			ouranosCategories.push(tuple["key"]);
			ouranosSeries.push(tuple["value"]);
		}

		afficherGraphe(ouranosCategories, ouranosSeries);
	}

	$(document).ready(
			function() {
				$.getJSON("http://127.0.0.1:8080/ouranos/display/listDateTime",
						function(data) {
							loadData(data);
						});
			});

	afficherGraphe(ouranosCategories, ouranosSeries);
</script>


