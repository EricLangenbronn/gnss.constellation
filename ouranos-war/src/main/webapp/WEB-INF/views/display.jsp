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
	src="<c:url value="/resources/media/js/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/media/js/highcharts/modules/exporting.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/media/js/highcharts/highcharts.js" />"></script>
</head>
<body>
	<jsp:include page="<%=\"layouts/components/header.jsp\"%>" />
	<div class="container">
		<jsp:include page="<%=\"layouts/components/menu.jsp\"%>" />
	
		<div class="jumbotron">
			<div id="graph"
				style="min-width: 310px; height: 400px; margin: 0 auto"></div>
				
			<jsp:include page="<%=\"layouts/components/footer.jsp\"%>" />
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
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
								categories : [ 'Jan', 'Feb', 'Mar', 'Apr',
										'May', 'Jun', 'Jul', 'Aug', 'Sep',
										'Oct', 'Nov', 'Dec' ],
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
										+ '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
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
								name : 'Times',
								data : [ 49.9, 71.5, 106.4, 129.2, 144.0,
										176.0, 135.6, 148.5, 216.4, 194.1,
										95.6, 54.4 ]

							} ]
						});
	});
</script>