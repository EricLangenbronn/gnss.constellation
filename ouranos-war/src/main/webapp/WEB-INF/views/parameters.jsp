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
	<jsp:include page="<%=\"layouts/components/header.jsp\"%>" />
	<div class="container">
		<jsp:include page="<%=\"layouts/components/menu.jsp\"%>" />

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
										<input type="text" class="form-control" name="startMeasure"
											placeholder="Start time of measurement" required /> <span
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
								<div class="col-sm-3">
									<div class="form-group">
										<label class="sr-only" for="durationInHours">Duration
											of measure in hours</label>
										<div class='input-group date' id='timeOfMeasure'>
											<input type='text' class="form-control" name="timeOfMeasure"
												placeholder="Time of measure" required /> <span
												class="input-group-addon"> <span
												class="glyphicon glyphicon-time"></span>
											</span>
										</div>
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
										<div class="input-group-addon">�</div>
										<input class="form-control" type="text" id="elevationMask"
											name="elevationMask" placeholder="Elevation mask in degrees"
											required />
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
										<div class="input-group-addon">�</div>
										<input class="form-control" type="text" id="longitude"
											name="longitude" placeholder="Longitude in degrees" required />
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-3">
									<div class="input-group">
										<label class="sr-only" for="latitude">Base position,
											latitude in degrees</label>
										<div class="input-group-addon">�</div>
										<input class="form-control" type="text" id="latitude"
											name="latitude" placeholder="Latitude in degrees" required />
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
											name="altitude" placeholder="Altitude in meters" required />
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
			<jsp:include page="<%=\"layouts/components/footer.jsp\"%>" />
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

	$(function() {
		$('#timeOfMeasure').datetimepicker({
			format : 'LT'
		});
	});
</script>
