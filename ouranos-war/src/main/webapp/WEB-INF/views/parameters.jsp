<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>Ouranos</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/media/css/bootstrap.min.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/media/css/bootstrap-datetimepicker.min.css" />">
<script type="text/javascript"
	src="<c:url value="/resources/media/js/jquery-1.11.3.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/media/js/moment-min.js" /> /"></script>
<script type="text/javascript"
	src="<c:url value="/resources/media/js/bootstrap.min.js" /> /"></script>
<script type="text/javascript"
	src="<c:url value="/resources/media/js/bootstrap-datetimepicker-min.js" /> /"></script>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class=" icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Ouranos</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="homepage">Home</a></li>
					<li class="active"><a href="#">Parameters</a></li>
					<li><a href="about">About</a></li>
				</ul>
			</div>
		</div>
		</nav>

		<div class="jumbotron">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title" align="center">Temporal and spatial
						parameters</h3>
				</div>

				<div class="panel-body">
					<div class="col-sm-12">
						<label class="label label-default col-sm-1">Start Time : </label>

						<div class="col-sm-4">
							<div class="form-group">
								<div class='input-group date' id='datetimepicker9'>
									<input type='text' class="form-control" /> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"> </span>
									</span>
								</div>
							</div>
						</div>

					</div>
					<div class="col-sm-12">
						<label class="label label-default col-sm-1">Duration : </label>
					</div>
					<div class="col-sm-12">
						<label class="label label-default col-sm-2">Elevation mask
							: </label>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title" align="center">Synthetic Measurement
						Generator</h3>
				</div>
				<div class="panel-body">Panel content</div>
			</div>
		</div>

	</div>
</body>
</html>
