<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>Ouranos</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/media/css/bootstrap.min.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/media/css/bootstrap-datetimepicker.min.css" />" />
<script type="text/javascript"
	src="<c:url value="/resources/media/js/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/media/js/bootstrap.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/media/js/moment-2.10.6.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/media/js/bootstrap.datetimepicker-4.15.35.min.js" />"></script>
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
					<li><a href="display">Display</a></li>
					<li><a href="about">About</a></li>
				</ul>
			</div>
		</div>
		</nav>

		<div class="jumbotron">
			<form method="post" action="parameters/register">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h2 class="panel-title" align="center">Temporal and spatial
							parameters</h2>
					</div>
					<div class="panel-body">
						<div class="row">
							<h3>
								<label class="col-sm-2 label label-default" for="startDateTime">Start
									Time : </label>
							</h3>
							<div class="col-sm-4">
								<div class="form-group">
									<label class="sr-only" for="startDateTime">Start time
										of measurement</label>
									<div class="input-group date" id="startDateTime">
										<input type="text" class="form-control"
											placeholder="Start time of measurement" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"> </span>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<h3>
									<label class="col-sm-2 label label-default">Duration :
									</label>
								</h3>
								<div class="col-sm-2">
									<div class="form-group">
										<label class="sr-only" for="durationInHours">Duration
											of measure in hours</label> <select class="form-control"
											id="durationInHours">
											<option value="">Hours</option>
											<option value="0">0</option>
											<option value="1">1</option>
										</select>
									</div>
								</div>
								<div class="col-sm-2">
									<div class="form-group">
										<label class="sr-only" for="durationInMinutes">Duration
											of measure in minutes</label> <select class="form-control"
											id="durationInMinutes">
											<option value="">Minutes</option>
											<option value="0">0</option>
											<option value="1">1</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<h3>
									<label class="col-sm-2 label label-default" for="elevationMask">Elevation
										mask : </label>
								</h3>
								<div class="col-sm-4">
									<div class="input-group">
										<div class="input-group-addon">°</div>
										<input class="form-control" type="text" id="elevationMask" name="elevationMask"
											placeholder="Elevation mask in degrees" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h2 class="panel-title" align="center">Synthetic Measurement
							Generator</h2>
					</div>
					<div class="panel-body">
						<div class="row">
							<h3>
								<label class="col-sm-3 label label-default">Base
									position : LLA : </label>
							</h3>
							<div class="form-group">
								<div class="col-sm-3">
									<div class="input-group">
										<label class="sr-only" for="longitude">Base position,
											longitude in degrees</label>
										<div class="input-group-addon">°</div>
										<input class="form-control" type="text" id="longitude"
											placeholder="Longitude in degrees" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<div class="input-group">
										<label class="sr-only" for="latitude">Base position,
											latitude in degrees</label>
										<div class="input-group-addon">°</div>
										<input class="form-control" type="text" id="latitude"
											placeholder="Latitude in degrees" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<div class="input-group">
										<label class="sr-only" for="altitude">Base position,
											altitude in meters</label>
										<div class="input-group-addon">m</div>
										<input class="form-control" type="text" id="altitude"
											placeholder="Altitude in meters" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div>
					<button type="submit" class="btn btn-lg btn-primary">Execute</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$('#startDateTime').datetimepicker({
			viewMode : 'years'
		});
	});
</script>
